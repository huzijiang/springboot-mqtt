package com.demo.mqtt.controller;

import com.demo.mqtt.sender.MqttSendClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author huzj
 * @version 1.0
 * @date 2022/7/3 18:24
 */
@RestController
public class MqttController {
    @Resource
    private MqttSendClient mqttSendClient;

    @GetMapping("/send")
    public void send(){
        mqttSendClient.publish("client:report;1","heihei");
    }
}
