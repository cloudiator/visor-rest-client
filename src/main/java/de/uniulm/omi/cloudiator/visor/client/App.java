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
import de.uniulm.omi.cloudiator.visor.client.entities.SensorMonitor;
import de.uniulm.omi.cloudiator.visor.client.entities.SensorMonitorBuilder;

import java.util.HashMap;
import java.util.Map;
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
                        .url("http://localhost:31415")
                                // the entity to get the controller for.
                        .build(Monitor.class);

        SensorMonitor coolMonitor = (new SensorMonitorBuilder())
                .sensorClassName("de.uniulm.omi.cloudiator.visor.sensors.SystemMemoryUsageSensor")
                .metricName("memory_usage")
                .componentId("5")
                .addMonitorContext("test", "testvalu")
                .interval(1, TimeUnit.SECONDS).build();

        //get all monitor
        for (Monitor m : controller.getList()){
            System.out.println(m.getMetricName());
        }

        //create a new Monitor
        coolMonitor = (SensorMonitor)controller.create(coolMonitor);


        // update a monitor
        coolMonitor.getInterval().setPeriod(22);
        controller.update(coolMonitor);

        Thread.sleep(20000);

        //get all monitor
        for (Monitor m : controller.getList()){
            //delete a Monitor
            controller.delete(m);
        }

    }
}
