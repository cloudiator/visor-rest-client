package de.uniulm.omi.cloudiator.visor.client.entities;

import de.uniulm.omi.cloudiator.visor.client.entities.internal.AbstractEntity;
import de.uniulm.omi.cloudiator.visor.client.entities.internal.Link;
import de.uniulm.omi.cloudiator.visor.client.entities.internal.Path;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created by Frank on 28.10.2015.
 */
@Path("servers")
public class Server extends AbstractEntity {

    private Map<String, String> monitorContext;

    private Integer port;

    @SuppressWarnings("UnusedDeclaration")
    Server() {
    }

    @SuppressWarnings("UnusedDeclaration")
    Server(@Nullable List<Link> links) {
        super(links);
    }

    Server(Map<String, String> monitorContext, Integer port) {
        this(null, monitorContext, port);
    }

    Server(@Nullable List<Link> links, Map<String, String> monitorContext, Integer port) {
        super(links);
        this.monitorContext = monitorContext;
        this.port = port;
    }

    public Map<String, String> getMonitorContext() {
        return monitorContext;
    }

    public void setMonitorContext(Map<String, String> monitorContext) {
        this.monitorContext = monitorContext;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public static ServerBuilder builder() {
        return new ServerBuilder();
    }

    public static class ServerBuilder {

        private Map<String, String> monitorContext;

        private Integer port;

        public ServerBuilder() {
            this.monitorContext = new HashMap<>();
        }


        public ServerBuilder monitorContext(Map<String, String> monitorContext) {
            this.monitorContext = monitorContext;
            return this;
        }

        public ServerBuilder port(Integer port) {
            this.port = port;
            return this;
        }

        public ServerBuilder addMonitorContext(final String key, final String value) {
            //noinspection Convert2streamapi
            this.monitorContext.put(key, value);
            return this;
        }

        public ServerBuilder addMonitorContext(final Map<String, String> monitorContext) {
            //noinspection Convert2streamapi
            this.monitorContext = monitorContext;
            return this;
        }

        public Server build() {
            return new Server(monitorContext, port);
        }
    }
}