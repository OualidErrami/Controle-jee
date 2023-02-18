package org.sid.demospringcloudstreamskafka.services;

import org.apache.kafka.clients.admin.NewTopic;
import org.sid.demospringcloudstreamskafka.entities.BillingEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
@Service
public class BillProducer {

    private static final Logger LOGGER = LoggerFactory.getLogger(BillProducer.class);

    private NewTopic topic;

    private KafkaTemplate<String, BillingEvent> kafkaTemplate;

    public BillProducer(NewTopic topic, KafkaTemplate<String, BillingEvent> kafkaTemplate) {
        this.topic = topic;
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(BillingEvent event){
        LOGGER.info(String.format("Order event => %s", event.toString()));

        // create Message
        Message<BillingEvent> message = MessageBuilder
                .withPayload(event)
                .setHeader(KafkaHeaders.TOPIC, topic.name())
                .build();
        kafkaTemplate.send(message);
    }

}

