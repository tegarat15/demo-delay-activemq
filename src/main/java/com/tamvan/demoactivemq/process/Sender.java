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
        sendData(constructMessageCreator(message, scheduled));
    }

    private void sendData(MessageCreator messageCreator) {
        jmsTemplate.send("tamvan", messageCreator);
    }

    private MessageCreator constructMessageCreator(String message, Long scheduled) {
        MessageCreator messageCreator = session -> {
            Message messageCreated = session.createTextMessage(message);
            messageCreated.setLongProperty("_AMQ_SCHED_DELIVERY", System.currentTimeMillis() + scheduled);
            return messageCreated;
        };
        return messageCreator;
    }
}
