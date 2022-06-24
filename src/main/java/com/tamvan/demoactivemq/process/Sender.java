package com.tamvan.demoactivemq.process;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import javax.jms.Destination;
import javax.jms.Message;
import java.util.Calendar;

@Service
@RequiredArgsConstructor
@Slf4j
public class Sender {

    private final JmsTemplate jmsTemplate;

    public void kirimPesan(String message, Long scheduled) {
        setLongDelayedMessage(scheduled);
        setExpiry();
        sendData(constructMessageCreator(message));
    }

    private void sendData(MessageCreator messageCreator) {
        jmsTemplate.send("tamvan", messageCreator);
    }

    private void setExpiry() {
        jmsTemplate.setExplicitQosEnabled(true);
        jmsTemplate.setTimeToLive(120000);
    }

    private void setLongDelayedMessage(Long scheduled) {
        jmsTemplate.setDeliveryDelay(scheduled);
    }

    private MessageCreator constructMessageCreator(String message) {
        MessageCreator messageCreator = session -> {
            Message messageCreated = session.createTextMessage(message);
            return messageCreated;
        };
        return messageCreator;
    }
}
