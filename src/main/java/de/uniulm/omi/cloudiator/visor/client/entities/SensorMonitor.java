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

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import de.uniulm.omi.cloudiator.visor.client.entities.internal.Link;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created by Frank on 10.12.2015.
 */
public class SensorMonitor extends Monitor {

    private String sensorClassName;
    //TODO: @JsonSerialize(as = DefaultInterval.class) @JsonDeserialize(as = DefaultInterval.class)
    private Interval interval;

    @SuppressWarnings("UnusedDeclaration")
    SensorMonitor() {
    }

    SensorMonitor(@Nullable List<Link> links, String metricName,
            String componentId, Map<String, String> monitorContext,
            String sensorClassName, Interval interval) {
        super(links, metricName, componentId, monitorContext);
        this.sensorClassName = sensorClassName;
        this.interval = interval;
    }

    SensorMonitor(String metricName, String componentId, Map<String, String> monitorContext,
                  String sensorClassName, Interval interval) {
        this(null, metricName, componentId, monitorContext, sensorClassName, interval);
    }

    public String getSensorClassName() {
        return sensorClassName;
    }

    @SuppressWarnings("UnusedDeclaration")
    public void setSensorClassName(String sensorClassName) {
        this.sensorClassName = sensorClassName;
    }

    public Interval getInterval() {
        return interval;
    }

    public void setInterval(Interval interval) {
        this.interval = interval;
    }

    public static SensorMonitorBuilder builder() {
        return new SensorMonitorBuilder();
    }

    public static class SensorMonitorBuilder {

        private String metricName;
        private String componentId;
        private Map<String, String> monitorContext = new HashMap<>();
        private String sensorClassName;
        private long period;
        private String timeUnit;

        public SensorMonitorBuilder() {
            //
        }

        public SensorMonitorBuilder metricName(final String metricName) {
            this.metricName = metricName;
            return this;
        }

        public SensorMonitorBuilder sensorClassName(final String sensorClassName) {
            this.sensorClassName = sensorClassName;
            return this;
        }

        public SensorMonitorBuilder interval(final long period, final TimeUnit timeUnit) {
            this.period = period;
            this.timeUnit = timeUnit.toString();
            return this;
        }

        public SensorMonitorBuilder addMonitorContext(final String key, final String value) {
            //noinspection Convert2streamapi
            this.monitorContext.put(key, value);
            return this;
        }

        public SensorMonitorBuilder monitorContext(Map<String, String> monitorContext) {
            //noinspection Convert2streamapi
            this.monitorContext = monitorContext;
            return this;
        }

        public SensorMonitorBuilder componentId(String componentId) {
            //noinspection Convert2streamapi
            this.componentId = componentId;
            return this;
        }

        public SensorMonitor build() {
            return new SensorMonitor(metricName, componentId, monitorContext, sensorClassName, new Interval(period, timeUnit));
        }

    }
}
