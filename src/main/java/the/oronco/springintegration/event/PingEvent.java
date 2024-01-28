package the.oronco.springintegration.event;

import java.time.ZonedDateTime;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;
import the.oronco.springintegration.controller.PingController;

@Getter
public class PingEvent extends ApplicationEvent {
    private final ZonedDateTime eventTime;

    public PingEvent(PingController pingController, ZonedDateTime zonedDateTime) {
        super(pingController);
        this.eventTime = zonedDateTime;
    }
}
