package com.tamvan.demoactivemq.process;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class Receiver {

    public void loggingMessage(String message){
        log.info(message);
    }
}
