package de.uniulm.omi.cloudiator.visor.client.entities;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created by Frank on 16.02.2016.
 */
public class SensorMonitorBuilder {

    private String uuid;
    private String metricName;
    private String componentId;
    private Map<String, String> monitorContext = new HashMap<>();
    private String sensorClassName;
    private long period;
    private String timeUnit;

    public SensorMonitorBuilder() {
        //
    }

    public SensorMonitorBuilder uuid(final String uuid) {
        this.uuid = uuid;
        return this;
    }

    public SensorMonitorBuilder metricName(final String metricName) {
        this.metricName = metricName;
        return this;
    }

    public SensorMonitorBuilder componentId(final String componentId) {
        this.componentId = componentId;
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

    public SensorMonitor build() {
        return new SensorMonitor(uuid, metricName, componentId, monitorContext, sensorClassName, new Interval(period, timeUnit));
    }
}
