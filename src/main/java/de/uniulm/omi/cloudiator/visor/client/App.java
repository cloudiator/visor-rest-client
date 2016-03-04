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
import de.uniulm.omi.cloudiator.visor.client.entities.Server;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

/**
 * Created by frank on 10.02.15.
 */
public class App 
{
    public static void main( String[] args ) throws InterruptedException {
        //An example for a simple Memory Usage Sensor

        //get the controller for the monitor entity
        final ClientController<Monitor> monitorController =
                ClientBuilder.getNew()
                        // the base url
                        .url("http://127.0.0.1:31415")
                                // the entity to get the controller for.
                        .build(Monitor.class);

        Monitor coolMonitor = Monitor.builder().sensorClassName("de.uniulm.omi.cloudiator.visor.sensors.MemoryUsageSensor").metricName("memory_usage").interval(1, TimeUnit.SECONDS).build();


        //create a new Monitor
        coolMonitor = monitorController.create(coolMonitor);


        // update a monitor
        coolMonitor.getInterval().setPeriod(22);
        monitorController.update(coolMonitor);

        Thread.sleep(20000);

        //get all monitor
        for (Monitor m : monitorController.getList()){
            //delete a Monitor
            monitorController.delete(m);
        }





        //An example for a simple Telnet server listening to port 12366
        int port = 12366;


        //get the controller for the (telnet) server entity
        final ClientController<Server> serverController =
                ClientBuilder.getNew()
                        // the base url
                        .url("http://127.0.0.1:31415")
                                // the entity to get the controller for.
                        .build(Server.class);

        HashMap<String, String> serverContext = new HashMap<>();
        serverContext.put("key1", "value1");

        Server coolServer = Server.builder().port(port).monitorContext(serverContext).build();

        // create the server
        coolServer = serverController.create(coolServer);
    }
}
