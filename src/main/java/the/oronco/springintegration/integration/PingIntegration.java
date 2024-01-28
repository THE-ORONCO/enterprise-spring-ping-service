package the.oronco.springintegration.integration;

import java.time.ZonedDateTime;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.event.inbound.ApplicationEventListeningMessageProducer;
import org.springframework.integration.event.outbound.ApplicationEventPublishingMessageHandler;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHeaders;
import the.oronco.springintegration.event.PingEvent;
import the.oronco.springintegration.event.PongEvent;

@Configuration
@EnableIntegration
@Slf4j
public class PingIntegration {
    @Bean
    public DirectChannel eventChannel(){
        return new DirectChannel();
    }
    @Bean
    public DirectChannel eventErrorChannel(){
        return new DirectChannel();
    }

    @Bean
    public ApplicationEventListeningMessageProducer eventsAdapter() {

        ApplicationEventListeningMessageProducer producer =
                new ApplicationEventListeningMessageProducer();
        producer.setEventTypes(PingEvent.class, java.util.Date.class);
        return producer;
    }



    @Bean
    public IntegrationFlow eventFlow(ApplicationEventListeningMessageProducer eventsAdapter,
                                             ApplicationEventPublishingMessageHandler eventHandler,
                                     IntegrationFlow eventsOutFlow) {

        return IntegrationFlow.from(eventsAdapter)
                .handle(message -> {log.info("message of type {} with headers {} accepted by integrationflow!",
                                             message.getPayload().getClass().getSimpleName(), message.getHeaders());
                eventsOutFlow.getInputChannel().send(new Message<PongEvent>() {
                    @Override
                    public PongEvent getPayload() {
                        return new PongEvent(PingIntegration.class.getSimpleName(), ZonedDateTime.now());
                    }

                    @Override
                    public MessageHeaders getHeaders() {
                        return message.getHeaders();
                    }
                });})
                .get();


    }

    @Bean
    public ApplicationEventPublishingMessageHandler eventHandler() {
        ApplicationEventPublishingMessageHandler handler =
                new ApplicationEventPublishingMessageHandler();
        handler.setPublishPayload(true);
        return handler;
    }

    @Bean
    public IntegrationFlow eventsOutFlow(MessageChannel eventChannel, ApplicationEventPublishingMessageHandler eventHandler) {
        return IntegrationFlow.from(eventChannel)
                              .handle(eventHandler)
                              .get();
    }
}
