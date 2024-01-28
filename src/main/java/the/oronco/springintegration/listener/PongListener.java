package the.oronco.springintegration.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import the.oronco.springintegration.event.PongEvent;

@Component
@Slf4j
public class PongListener {
    @EventListener
    public void onPingEvent(PongEvent pongEvent) {
        log.info("got pong from {} at {}", pongEvent.getSource(), pongEvent.getEventTime());
    }
}
