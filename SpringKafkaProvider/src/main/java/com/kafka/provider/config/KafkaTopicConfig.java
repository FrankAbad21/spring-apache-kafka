package com.kafka.provider.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.config.TopicConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaTopicConfig {

    @Bean
    public NewTopic generateTopic() {

        Map<String, String> configurations = new HashMap<>();
        //configurations.put(TopicConfig.CLEANUP_POLICY_CONFIG, TopicConfig); // delete (borra mensaje) - compact (Mantiene el mas actual)
        configurations.put(TopicConfig.RETENTION_MS_CONFIG, "86400000"); // Tiempo (milisegundos) de retencion de mensajes, defecto -1 (No se borra nunca)
        configurations.put(TopicConfig.SEGMENT_BYTES_CONFIG, "1073741824"); // Tamanio maximo del segmento - defecto 1073741824 bytes - 1GB
        configurations.put(TopicConfig.MAX_MESSAGE_BYTES_CONFIG, "1000012"); // Tamanio maximo de cada mensaje - defecto 1000000 - 1 MB

        NewTopic newTopic = TopicBuilder.name("baufest-topic")
                .partitions(2) // Numero de particiones
                .replicas(2) // Numero de replicas
                .configs(configurations)
                .build();

        return newTopic;
    }
}