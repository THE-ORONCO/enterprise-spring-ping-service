package the.oronco.springintegration.config;

import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.boot.actuate.health.HealthEndpoint;
import org.springframework.boot.actuate.health.Status;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
public class MyHealthMetricsExportConfiguration {

    public MyHealthMetricsExportConfiguration(MeterRegistry registry, HealthEndpoint healthEndpoint) {
        // This example presumes common tags (such as the app) are applied elsewhere
        Gauge.builder("application.health", healthEndpoint, this::getStatusCode)
             .tag("application", "health")
             .strongReference(true)
             .register(registry);
    }

    private int getStatusCode(HealthEndpoint health) {
        Status status = health.health()
                              .getStatus();

        if (Status.UP.equals(status)) {
            return 3;
        }
        if (Status.OUT_OF_SERVICE.equals(status)) {
            return 2;
        }
        if (Status.DOWN.equals(status)) {
            return 1;
        }
        return 0;
    }

}