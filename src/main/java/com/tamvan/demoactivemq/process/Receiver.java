package com.tamvan.demoactivemq.process;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import javax.jms.TextMessage;

@Service
@RequiredArgsConstructor
@Slf4j
public class Receiver {

    public void loggingMessage(String message){
        log.info(message);
    }
}
