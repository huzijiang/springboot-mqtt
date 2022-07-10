package com.demo.mqtt.accepter;

import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallbackExtended;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;

/**
 * @author huzj
 * @version 1.0
 * @date 2022/7/3 18:06
 */
@Slf4j
@Component
public class MqttAcceptCallback implements MqttCallbackExtended {
    @Autowired
    private MqttAcceptClient mqttAcceptClient;

    /**
     * 客户端断开后触发
     *
     * @param throwable
     */
    @Override
    public void connectionLost(Throwable throwable) {
        log.info("【MQTT-消费端】连接断开，可以做重连");
        if (MqttAcceptClient.getMqttClient() == null || !MqttAcceptClient.getMqttClient().isConnected()) {
            log.info("【MQTT-消费端】emqx重新连接....................................................");
            mqttAcceptClient.reconnection();
        }
    }

    /**
     * 客户端收到消息触发
     *
     * @param topic       主题
     * @param mqttMessage 消息
     */
    @Override
    public void messageArrived(String topic, MqttMessage mqttMessage) throws Exception {


        log.info("【MQTT-消费端】接收消息主题 : " + topic);
        log.info("【MQTT-消费端】接收消息Qos : " + mqttMessage.getQos());
        log.info("【MQTT-消费端】接收消息内容 : " + new String(mqttMessage.getPayload()));
    }

    /**
     * 发布消息成功
     *
     * @param token token
     */
    @Override
    public void deliveryComplete(IMqttDeliveryToken token) {

        String[] topics = token.getTopics();
        for (String topic : topics) {
            log.info("【MQTT-消费端】向主题：" + topic + "发送消息成功！");
        }
        try {
            MqttMessage message = token.getMessage();
            byte[] payload = message.getPayload();
            String s = new String(payload, "UTF-8");
            log.info("【MQTT-消费端】消息的内容是：" + s);
        } catch (MqttException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    /**
     * 连接emq服务器后触发
     *
     * @param b
     * @param s
     */
    @Override
    public void connectComplete(boolean b, String s) {

        System.out.println("s: " + s);

        log.info("--------------------【MQTT-消费端】连接成功！--------------------");
        // 以/#结尾表示订阅所有以test开头的主题
        // 订阅所有机构主题
        mqttAcceptClient.subscribe("test_queue", 0);
    }
}
