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

import de.uniulm.omi.cloudiator.visor.client.entities.internal.Link;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Frank on 10.12.2015.
 */
public class PushMonitor extends Monitor {
    private Integer port;

    @SuppressWarnings("UnusedDeclaration")
    PushMonitor() {
    }

    PushMonitor(@Nullable List<Link> links, String metricName,
                  String componentId, Map<String, String> monitorContext,
                  Integer port) {
        super(links, metricName, componentId, monitorContext);
        this.port = port;
    }

    PushMonitor(String metricName, String componentId, Map<String, String> monitorContext,
                Integer port) {
        this(null, metricName, componentId, monitorContext, port);
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public static PushMonitorBuilder builder() {
        return new PushMonitorBuilder();
    }

    public static class PushMonitorBuilder {

        private String metricName;
        private String componentId;
        private Map<String, String> monitorContext = new HashMap<>();
        private Integer port;

        public PushMonitorBuilder() {
            //
        }

        public PushMonitorBuilder metricName(final String metricName) {
            this.metricName = metricName;
            return this;
        }

        public PushMonitorBuilder port(final Integer port) {
            this.port = port;
            return this;
        }

        public PushMonitorBuilder addMonitorContext(final String key, final String value) {
            //noinspection Convert2streamapi
            this.monitorContext.put(key, value);
            return this;
        }

        public PushMonitorBuilder monitorContext(Map<String, String> monitorContext) {
            //noinspection Convert2streamapi
            this.monitorContext = monitorContext;
            return this;
        }

        public PushMonitorBuilder componentId(String componentId) {
            //noinspection Convert2streamapi
            this.componentId = componentId;
            return this;
        }

        public PushMonitor build() {
            return new PushMonitor(metricName, componentId, monitorContext, port);
        }

    }
}
