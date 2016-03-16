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

import javax.validation.constraints.NotNull;
import java.util.Collections;
import java.util.Map;

/**
 * Created by Frank on 10.12.2015.
 */
public class SensorMonitor extends Monitor {

    @NotNull private String sensorClassName;
    @NotNull @JsonSerialize(as = Interval.class) @JsonDeserialize(as = Interval.class)
    private Interval interval;
    private Map<String, String> sensorConfiguration;

    @SuppressWarnings("UnusedDeclaration")
    SensorMonitor() {
    }

    SensorMonitor(String uuid, String metricName,
            String componentId, Map<String, String> monitorContext,
            String sensorClassName, Interval interval,
            Map<String, String> sensorConfiguration) {
        super(uuid, metricName, componentId, monitorContext);
        this.sensorClassName = sensorClassName;
        this.interval = interval;
        this.sensorConfiguration = sensorConfiguration;
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

    public Map<String, String> getSensorConfiguration() {
        if (sensorConfiguration == null) {
            return Collections.emptyMap();
        }
        return sensorConfiguration;
    }

    public void setSensorConfiguration(Map<String, String> sensorConfiguration) {
        this.sensorConfiguration = sensorConfiguration;
    }
}
