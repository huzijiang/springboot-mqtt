package com.demo.mqtt.accepter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

/**
 * @author huzj
 * @version 1.0
 * @date 2022/7/3 18:10
 */
@Configuration
public class MqttAcceptConfig {

    @Autowired
    private MqttAcceptClient mqttAcceptClient;

    @Conditional(MqttAcceptCondition.class)
    @Bean
    public MqttAcceptClient getMqttAcceptClient(){

        mqttAcceptClient.connect();
        //mqttAcceptClient.subscribe("test_queue",0);
        return mqttAcceptClient;
    }
}
