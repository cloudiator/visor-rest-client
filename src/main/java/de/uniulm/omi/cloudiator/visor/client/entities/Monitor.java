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

import de.uniulm.omi.cloudiator.visor.client.entities.internal.AbstractEntity;
import de.uniulm.omi.cloudiator.visor.client.entities.internal.Link;
import de.uniulm.omi.cloudiator.visor.client.entities.internal.Path;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by frank on 10.02.15.
 */
@Path("monitors")
public class Monitor extends AbstractEntity {

    private String metricName;

    private String sensorClassName;

    private Interval interval;

    private List<Context> contexts;

    @SuppressWarnings("UnusedDeclaration")
    Monitor() {
    }

    @SuppressWarnings("UnusedDeclaration")
    Monitor(@Nullable List<Link> links) {
        super(links);
    }

    Monitor(String metricName, String sensorClassName, Interval interval, List<Context> contexts) {
        //super(link);
        this.metricName = metricName;
        this.sensorClassName = sensorClassName;
        this.interval = interval;
        this.contexts = contexts;
    }

    Monitor(@Nullable List<Link> links, String metricName, String sensorClassName, Interval interval, List<Context> contexts) {
        super(links);
        this.metricName = metricName;
        this.sensorClassName = sensorClassName;
        this.interval = interval;
        this.contexts = contexts;
    }

    public String getMetricName() {
        return metricName;
    }

    public void setMetricName(String metricName) {
        this.metricName = metricName;
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

    public List<Context> getContexts() {
        return contexts;
    }

    @SuppressWarnings("UnusedDeclaration")
    public void setContexts(List<Context> contexts) {
        this.contexts = contexts;
    }

    public static MonitorBuilder builder() {
        return new MonitorBuilder();
    }

    public static class MonitorBuilder {

        private String metricName;
        private String sensorClassName;
        private long period;
        private String timeUnit;
        private List<Context> contexts;

        public MonitorBuilder() {
            this.contexts = new ArrayList<>();
        }

        public MonitorBuilder metricName(final String metricName) {
            this.metricName = metricName;
            return this;
        }

        public MonitorBuilder sensorClassName(final String sensorClassName) {
            this.sensorClassName = sensorClassName;
            return this;
        }

        public MonitorBuilder interval(final long period, final TimeUnit timeUnit) {
            this.period = period;
            this.timeUnit = timeUnit.toString();
            return this;
        }

        public MonitorBuilder addContext(final String key, final String value) {
            //noinspection Convert2streamapi
            this.contexts.add(new Context(key, value));
            return this;
        }

        public MonitorBuilder addContexts(final List<Context> contexts) {
            //noinspection Convert2streamapi
            this.contexts = contexts;
            return this;
        }

        public Monitor build() {
            return new Monitor(metricName, sensorClassName, new Interval(period, timeUnit), contexts);
        }

    }

}
