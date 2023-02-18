package org.sid.demospringcloudstreamskafka.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Bill {
    public Long id;
    public Date billingDate;
    public Long customerID;
    private Customer customer;
}
