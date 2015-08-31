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

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import de.uniulm.omi.cloudiator.visor.client.entities.internal.Credential;
import de.uniulm.omi.cloudiator.visor.client.entities.internal.Entity;

import org.glassfish.jersey.filter.LoggingFilter;

import javax.ws.rs.client.Client;
import java.util.logging.Logger;

/**
 * Created by frank on 10.02.15.
 */
public class ClientBuilder {

    private String url;
    private Credential credentials;

    private ClientBuilder() {
    }

    public static ClientBuilder getNew() {
        return new ClientBuilder();
    }

    public ClientBuilder url(String url) {
        this.url = url;
        return this;
    }

    public ClientBuilder credentials(String email, String password) {
        this.credentials = new Credential(email, password);
        return this;
    }

    public <T extends Entity> ClientController<T> build(Class<T> clazz) {
        final Client client = javax.ws.rs.client.ClientBuilder.newBuilder().register(
            JacksonJsonProvider.class).register(new LoggingFilter(Logger.getGlobal(),true))/*.register(new AuthenticationFilter(this.credentials, this.url))*/.build();
        return new ClientController<>(client, this.url, clazz);
    }

}
