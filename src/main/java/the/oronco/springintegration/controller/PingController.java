package the.oronco.springintegration.controller;

import io.micrometer.core.annotation.Timed;
import java.time.ZonedDateTime;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import the.oronco.springintegration.config.CustomProps;
import the.oronco.springintegration.event.PingEvent;

@RestController
@AllArgsConstructor
@Timed
public class PingController {

    private final ApplicationEventPublisher eventPublisher;
    private final CustomProps customProps;

    @GetMapping("/ping")
    @Timed(extraTags = { "auf-dem", "baum"})
    public String pong() {
        eventPublisher.publishEvent(new PingEvent(this, ZonedDateTime.now()));
        return customProps.pongMessage();
    }
}
