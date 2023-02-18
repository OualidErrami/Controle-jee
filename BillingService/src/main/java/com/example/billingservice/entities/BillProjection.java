package com.example.billingservice.entities;

import org.springframework.data.rest.core.config.Projection;

import java.util.Date;

@Projection(name = "fullBill", types = Bill.class)
public interface BillProjection {
    Long getid();

    Date getbillingDate();

    Long getCustomerID();
}
