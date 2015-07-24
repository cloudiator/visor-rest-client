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

import java.util.concurrent.TimeUnit;

/**
 * Created by frank on 10.02.15.
 */
public class App 
{
    public static void main( String[] args ) throws InterruptedException {
        //An example

        //get the controller for the cloud entity
        final ClientController<Monitor> controller =
                ClientBuilder.getNew()
                        // the base url
                        .url("http://134.60.30.109:9002")
                                // the entity to get the controller for.
                        .build(Monitor.class);

        Monitor coolMonitor = Monitor.builder().sensorClassName("de.uniulm.omi.executionware.agent.monitoring.sensors.MemoryUsageSensor").metricName("memory_usage").interval(1, TimeUnit.SECONDS).build();


        //create a new Monitor
        controller.delete(coolMonitor);

        Thread.sleep(20000);

        //create a new Monitor
        controller.create(coolMonitor);
    }
}
