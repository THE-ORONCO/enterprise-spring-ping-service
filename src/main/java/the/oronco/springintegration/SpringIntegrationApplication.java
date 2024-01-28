package the.oronco.springintegration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.integration.http.config.EnableIntegrationGraphController;

@SpringBootApplication
@EnableIntegrationGraphController
@ConfigurationPropertiesScan({"the.oronco.springintegration.config"})
@Slf4j
public class SpringIntegrationApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringIntegrationApplication.class, args);
    }
}
