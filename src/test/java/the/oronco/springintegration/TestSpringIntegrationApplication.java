package the.oronco.springintegration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.TestConfiguration;

@TestConfiguration(proxyBeanMethods = false)
public class TestSpringIntegrationApplication {

    public static void main(String[] args) {
        SpringApplication.from(SpringIntegrationApplication::main)
                         .with(TestSpringIntegrationApplication.class)
                         .run(args);
    }

}
