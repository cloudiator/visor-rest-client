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

package de.uniulm.omi.cloudiator.visor.client.entities.internal;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;

import javax.annotation.Nullable;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientRequestFilter;
import javax.ws.rs.core.MediaType;
import java.io.IOException;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Preconditions.checkState;

/**
 * Created by daniel on 21.01.15.
 */
public class AuthenticationFilter implements ClientRequestFilter {

    @Nullable
    private Token token;
    private final Credential credential;
    private final String baseUrl;

    public AuthenticationFilter(final Credential credential, final String baseUrl) {
        this.credential = credential;
        this.baseUrl = baseUrl;
    }

    protected Token getToken() {
        if (this.token == null || this.token.isExpired()) {
            this.authenticate();
        }
        return this.token;
    }

    private void authenticate() {
        Token token = ClientBuilder.newBuilder().register(JacksonJsonProvider.class).build().target(this.baseUrl + "/login").request(MediaType.APPLICATION_JSON_TYPE).post(javax.ws.rs.client.Entity.entity(this.credential, MediaType.APPLICATION_JSON_TYPE)).readEntity(Token.class);
        checkState(!checkNotNull(token).isExpired());
        this.token = token;
    }

    @Override
    public void filter(ClientRequestContext requestContext) throws IOException {
        requestContext.getHeaders().add("X-Auth-Token", this.getToken().getToken());
        requestContext.getHeaders().add("X-Auth-UserId", this.getToken().getUserId());
    }
}
