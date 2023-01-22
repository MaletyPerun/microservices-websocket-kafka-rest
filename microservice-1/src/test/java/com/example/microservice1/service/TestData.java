package com.example.microservice1.service;

import com.example.microservice1.model.Message;
import com.example.microservice1.util.TimeUtil;

public class TestData {

    public static final int SESSION = 23;

    public static Message getInitMessage() {
        Message initMessage = new Message();
        initMessage.setSession(23);
        initMessage.setMC1_timestamp(TimeUtil.getDateTime());
        return initMessage;
    }
}
