package the.oronco.springintegration.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import the.oronco.springintegration.event.PingEvent;

@Component
@Slf4j
public class PingListener {
    @EventListener
    public void onPingEvent(PingEvent pingEvent) {
        log.info("got ping from {} at {}",
                 pingEvent.getSource()
                          .getClass()
                          .getSimpleName(),
                 pingEvent.getEventTime());
    }
}
