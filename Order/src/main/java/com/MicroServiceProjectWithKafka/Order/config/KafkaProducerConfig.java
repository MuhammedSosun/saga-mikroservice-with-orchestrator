package com.MicroServiceProjectWithKafka.Order.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.kafka.support.serializer.JsonSerializer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaProducerConfig {

    //bu bean mesaj gönderen oluyor yani producer
    @Bean
    public ProducerFactory<String, Object> orderProducerFactory() {
        Map<String, Object> config = new HashMap<>();
        //kafka broker adresi
        config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        //keyi string yapıyor
        config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        //serializer yani türler arası dönüşüm oda şu demek kafka byte ile çalışır bizde java yı alırız
        //kafkanın anlayacagı dile çeviririz yani byte[] a
        //bu methodta önce objeyi json yapar sonra byte[] çevirir kafka UI da rahat görmek için
        config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        return new DefaultKafkaProducerFactory<>(config);
    }

    @Bean
    public KafkaTemplate<String, Object> orderKafkaTemplate() {
        return new KafkaTemplate<>(orderProducerFactory());
    }
    @Bean
    public NewTopic orderCreatedTopic() {
        return new NewTopic("order-events", 1, (short) 1);
    }
}
