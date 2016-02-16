package de.uniulm.omi.cloudiator.visor.client.entities;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Frank on 16.02.2016.
 */
public class PushMonitorBuilder {

    private String uuid;
    private String metricName;
    private String componentId;
    private Map<String, String> monitorContext = new HashMap<>();
    private Integer port;

    public PushMonitorBuilder() {
        //
    }

    public PushMonitorBuilder uuid(String uuid) {
        this.uuid = uuid;
        return this;
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
        return new PushMonitor(uuid, metricName, componentId, monitorContext, port);
    }

}
