package com.tamvan.demoactivemq.controller;

import com.tamvan.demoactivemq.controller.bean.request.SenderRequest;
import com.tamvan.demoactivemq.process.Sender;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.jms.JMSException;

@RestController
@RequestMapping("/v1/send-data-artemis")
@RequiredArgsConstructor
public class TestArtemisController {

    private final Sender sender;

    @PostMapping("/sync")
    public HttpEntity<?> sendDataToActiveMqArtemis(@RequestBody SenderRequest request) throws JMSException {
        sender.kirimPesan(request.getMessage(), request.getDelayed());
        return new ResponseEntity<>("Terkirim", HttpStatus.OK);
    }
}
