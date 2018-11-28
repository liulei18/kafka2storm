package com.lenovo.order;

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;

import java.util.Properties;

public class OrderMqSender {
    public static void main(String[] args) {
        String TOPIC = "orderMq";
        Properties props = new Properties();
        props.put("serializer.class", "kafka.serializer.StringEncoder");
        props.put("metadata.broker.list", "mini2:9092,mini3:9092,mini4:9092");
        props.put("request.required.acks", "1");
        props.put("partitioner.class", "kafka.producer.DefaultPartitioner");
        Producer<String, String> producer = new Producer<>(new ProducerConfig(props));
        for (int messageNo = 1; messageNo < 10000; messageNo++) {
            producer.send(new KeyedMessage<String, String>(TOPIC, messageNo + "",new OrderInfo().random() ));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
