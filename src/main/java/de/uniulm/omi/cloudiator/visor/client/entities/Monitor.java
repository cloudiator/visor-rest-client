/*
 * Copyright (c) 2014-2015 University of Ulm
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

package de.uniulm.omi.cloudiator.visor.client.entities;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import de.uniulm.omi.cloudiator.visor.client.entities.internal.AbstractEntity;
import de.uniulm.omi.cloudiator.visor.client.entities.internal.Link;
import de.uniulm.omi.cloudiator.visor.client.entities.internal.Path;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Map;

/**
 * Created by frank on 10.02.15.
 */
@Path("monitors")
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY)
@JsonSubTypes({@JsonSubTypes.Type(value = PushMonitor.class, name = "push"),
        @JsonSubTypes.Type(value = SensorMonitor.class, name = "sensor")})
public abstract class Monitor extends AbstractEntity {

    private String metricName;
    private String componentId;
    private Map<String, String> monitorContext;

    @SuppressWarnings("UnusedDeclaration")
    Monitor() {
    }

    @SuppressWarnings("UnusedDeclaration")
    Monitor(@Nullable List<Link> links) {
        super(links);
    }

    Monitor(@Nullable List<Link> links, String metricName, String componentId, Map<String, String> monitorContext) {
        super(links);
        this.metricName = metricName;
        this.componentId = componentId;
        this.monitorContext = monitorContext;
    }

    Monitor(String metricName, String componentId, Map<String, String> monitorContext) {
        this(null, metricName, componentId, monitorContext);
    }

    public String getMetricName() {
        return metricName;
    }

    public void setMetricName(String metricName) {
        this.metricName = metricName;
    }

    public Map<String, String> getMonitorContext() {
        return monitorContext;
    }

    public void setMonitorContext(Map<String, String> monitorContext) {
        this.monitorContext = monitorContext;
    }

    public String getComponentId() {
        return componentId;
    }

    public void setComponentId(String componentId) {
        this.componentId = componentId;
    }
}
