package the.oronco.springintegration.info;

import java.time.ZonedDateTime;
import java.util.Map;
import org.springframework.boot.actuate.info.Info.Builder;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.stereotype.Component;

@Component
public class CustomInfo implements InfoContributor {
    @Override
    public void contribute(Builder builder) {
        builder.withDetail("added-by-custom-class", Map.of("current date time", ZonedDateTime.now()));
    }
}
