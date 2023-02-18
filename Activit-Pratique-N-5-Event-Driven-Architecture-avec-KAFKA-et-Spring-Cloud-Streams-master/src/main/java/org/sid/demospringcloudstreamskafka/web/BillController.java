package org.sid.demospringcloudstreamskafka.web;

import org.sid.demospringcloudstreamskafka.entities.Bill;
import org.sid.demospringcloudstreamskafka.entities.BillingEvent;
import org.sid.demospringcloudstreamskafka.entities.Customer;
import org.sid.demospringcloudstreamskafka.services.BillProducer;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class BillController {
    private BillProducer billProducer;

    public BillController(BillProducer billProducer){
        this.billProducer=billProducer;
    }

    @PostMapping("/send")
    public String SendBill(Bill bill){
        Customer customer=new Customer();
        customer.setId(4L);
        customer.setName("Errami Oualid");
        customer.setEmail("errami.oualid3@gmail.com");
        bill.setBillingDate(new Date());
        bill.setCustomerID(4L);
        bill.setCustomer(customer);
        BillingEvent billingEvent = new BillingEvent();
        billingEvent.setStatus("PENDING");
        billingEvent.setMessage("order status is in pending state");
        billingEvent.setBill(bill);

        billProducer.sendMessage(billingEvent);

        return "Order placed successfully ...";
    }

}
