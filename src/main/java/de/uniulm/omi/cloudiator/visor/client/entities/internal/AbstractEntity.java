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


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.annotation.Nullable;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by frank on 10.02.15.
 */
public abstract class AbstractEntity implements Entity {
    @JsonIgnore @Nullable private List<Link> links;

    public AbstractEntity() {
    }

    public AbstractEntity(@Nullable List<Link> links) {
        this.links = links;
    }

    @JsonIgnore @Nullable public List<Link> getLinks() {
        return links;
    }

    @JsonProperty public void setLinks(@Nullable List<Link> links) {
        this.links = links;
    }

    @Override public String getSelfLink() {
        checkNotNull(this.links);
        for (Link link : this.links) {
            if (link.getRel().equals("self")) {
                return link.getHref();
            }
        }
        throw new IllegalStateException("self link not present in entity");
    }

    @Override public String getId() {
        String selfLink = this.getSelfLink();
        return selfLink.substring(selfLink.lastIndexOf('/') + 1);
    }
}
