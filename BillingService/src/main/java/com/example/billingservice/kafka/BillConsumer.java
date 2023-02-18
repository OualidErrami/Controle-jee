package com.example.billingservice.kafka;

import com.example.billingservice.entities.BillingEvent;
import com.example.billingservice.repositories.BillRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class BillConsumer {

    @Autowired
    private BillRepository billRepository;
    private static final Logger LOGGER = LoggerFactory.getLogger(BillConsumer.class);

    @KafkaListener(
            topics = "${spring.kafka.topic.name}"
            , groupId = "${spring.kafka.consumer.group-id}"
    )
    public void consume(BillingEvent event) {
        LOGGER.info(String.format("Order event received in stock service => %s", event.toString()));
    billRepository.save(event.getBill());

    }
}
