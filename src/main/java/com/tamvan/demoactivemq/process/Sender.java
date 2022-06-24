package com.tamvan.demoactivemq.process;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.jms.core.MessagePostProcessor;
import org.springframework.stereotype.Service;

import javax.jms.JMSException;
import javax.jms.Message;

@Service
@RequiredArgsConstructor
@Slf4j
public class Sender {

    private final JmsTemplate jmsTemplate;

    public void kirimPesan(String message, Long scheduled) {
        MessageCreator messageCreator = constructMessageCreator(message);
        jmsTemplate.setDeliveryDelay(scheduled);
        jmsTemplate.send("tamvan", messageCreator);
    }

    private MessageCreator constructMessageCreator(String message) {
        MessageCreator messageCreator = session -> {
            Message messageCreated = session.createTextMessage(message);
            return messageCreated;
        };
        return messageCreator;
    }
}
