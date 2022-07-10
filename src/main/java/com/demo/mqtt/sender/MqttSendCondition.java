package com.demo.mqtt.sender;

import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * @author huzj
 * @version 1.0
 * @date 2022/7/3 17:48
 */
public class MqttSendCondition implements Condition {
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {

        System.out.println("mqttsender-Condition。。。。");

        //1、能获取到ioc使用的beanfactory
        ConfigurableListableBeanFactory beanFactory = context.getBeanFactory();

        //2、获取类加载器
        ClassLoader classLoader = context.getClassLoader();

        //3、获取当前环境信息
        Environment environment = context.getEnvironment();

        String isOpen = environment.getProperty("mqttsender.isOpen");

        return Boolean.valueOf(isOpen);
    }
}
