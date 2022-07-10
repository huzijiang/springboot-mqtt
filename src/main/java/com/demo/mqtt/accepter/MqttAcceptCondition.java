package com.demo.mqtt.accepter;

import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * @author huzj
 * @version 1.0
 * @date 2022/7/3 18:08
 */
public class MqttAcceptCondition implements Condition {

    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {

        System.out.println("mqttaccepter-Condition。。。。");

        //1、能获取到ioc使用的beanfactory
        ConfigurableListableBeanFactory beanFactory = context.getBeanFactory();

        //2、获取类加载器
        ClassLoader classLoader = context.getClassLoader();

        //3、获取当前环境信息
        Environment environment = context.getEnvironment();
        String isOpen = environment.getProperty("mqttaccepter.isOpen");
        return Boolean.valueOf(isOpen);
    }
}
