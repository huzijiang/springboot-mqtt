package com.demo.mqtt.sender;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

/**
 * @author huzj
 * @version 1.0
 * @date 2022/7/3 17:52
 */
@Configuration
public class MqttSendConfig {

    @Autowired
    private MqttSendClient mqttSendClient;
    @Conditional(MqttSendCondition.class)
    @Bean
    public MqttSendClient getMqttSendClient(){

        mqttSendClient.connect();
        return mqttSendClient;
    }
}

