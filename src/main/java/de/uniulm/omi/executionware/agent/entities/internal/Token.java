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

package de.uniulm.omi.executionware.agent.entities.internal;

import org.codehaus.jackson.annotate.JsonIgnore;

/**
 * Created by daniel on 21.01.15.
 */
public class Token extends AbstractEntity {

    private String createdOn;
    private String expiresAt;
    private String token;
    private String userId;

    public Token(String createdOn, String expiresAt, String token, String userId) {
        this.createdOn = createdOn;
        this.expiresAt = expiresAt;
        this.token = token;
        this.userId = userId;
    }

    protected Token() {
        
    }

    public String getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(String createdOn) {
        this.createdOn = createdOn;
    }

    public String getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(String expiresAt) {
        this.expiresAt = expiresAt;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @JsonIgnore
    boolean isExpired() {
        return System.currentTimeMillis() > Long.valueOf(this.expiresAt);
    }

    @Override
    public String getId() {
        return this.userId;
    }
}
