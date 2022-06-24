package com.tamvan.demoactivemq.controller.bean.request;

import lombok.Data;

@Data
public class SenderRequest {
    private String message;
    private Long delayed;
}
