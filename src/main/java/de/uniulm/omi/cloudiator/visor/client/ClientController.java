/*
 * Copyright (c) 2015 University of Ulm
 *
 * See the NOTICE file distributed with this work for additional information
 * regarding copyright ownership.  Licensed under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package de.uniulm.omi.cloudiator.visor.client;

import de.uniulm.omi.cloudiator.visor.client.entities.Monitor;
import de.uniulm.omi.cloudiator.visor.client.entities.internal.Entity;
import de.uniulm.omi.cloudiator.visor.client.entities.internal.Path;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import static com.google.common.base.Preconditions.*;

/**
 * Created by frank on 10.02.15.
 */
public class ClientController<T extends Monitor> {

    private final Class<T> type;
    private final Client client;
    private final String baseUrl;

    ClientController(Client client, String baseUrl, Class<T> clazz) {
        checkNotNull(client);
        checkNotNull(baseUrl);
        checkArgument(!baseUrl.isEmpty());
        checkNotNull(clazz);
        checkState(clazz.isAnnotationPresent(Path.class));
        this.type = clazz;

        this.baseUrl = baseUrl;
        this.client = client;
    }

    protected Invocation.Builder getRequest(String entityLink) {
        return this.client.target(entityLink).request(MediaType.APPLICATION_JSON);
    }

    public T get(long id) {
        return this.getRequest(this.baseUrl + "/" + this.type.getAnnotation(Path.class).value() + "/" + id).get(this.type);
    }

    public List<T> getList() {
        ParameterizedType parameterizedGenericType = new ParameterizedType() {
            public Type[] getActualTypeArguments() {
                return new Type[]{type};
            }

            public Type getRawType() {
                return List.class;
            }

            public Type getOwnerType() {
                return List.class;
            }
        };
        return this.getRequest(this.baseUrl + "/" + this.type.getAnnotation(Path.class).value()).get(new GenericType<List<T>>(parameterizedGenericType) {
        });
    }

    public T create(T t) {
        return this.getRequest(this.baseUrl + "/" + this.type.getAnnotation(Path.class).value()).post(javax.ws.rs.client.Entity.entity(t, MediaType.APPLICATION_JSON_TYPE)).readEntity(type);
    }

    public T update(T t) {
        return this.getRequest(this.baseUrl + "/" + this.type.getAnnotation(Path.class).value() + "/" + t.getUuid()).put(javax.ws.rs.client.Entity.entity(t, MediaType.APPLICATION_JSON_TYPE)).readEntity(type);
    }

    public void delete(T t) {
        this.getRequest(this.baseUrl + "/" + this.type.getAnnotation(Path.class).value() + "/" + t.getUuid()).delete();
    }


}
