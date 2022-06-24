package com.tamvan.demoactivemq.listener;

import com.tamvan.demoactivemq.process.Receiver;
import lombok.RequiredArgsConstructor;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ArtemisListener {

    private final Receiver receiver;

    @JmsListener(destination = "tamvan")
    public void processMessage(String content){
        receiver.loggingMessage(content);
    }
}
