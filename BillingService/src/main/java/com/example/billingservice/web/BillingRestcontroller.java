package com.example.billingservice.web;

import com.example.billingservice.entities.Bill;
import com.example.billingservice.feign.CustomerRestClient;
import com.example.billingservice.feign.ProductRestClient;
import com.example.billingservice.model.Customer;
import com.example.billingservice.model.Product;
import com.example.billingservice.repositories.BillRepository;
import com.example.billingservice.repositories.ProductItemRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BillingRestcontroller {
    public ProductRestClient productRestClient;
    public CustomerRestClient customerRestClient;
    public BillRepository billRepository;
    public ProductItemRepository productItemRepository;

    public BillingRestcontroller(ProductRestClient productRestClient, CustomerRestClient customerRestClient, BillRepository billRepository, ProductItemRepository productItemRepository) {
        this.productRestClient = productRestClient;
        this.customerRestClient = customerRestClient;
        this.billRepository = billRepository;
        this.productItemRepository = productItemRepository;
    }

    @GetMapping(path = "/fullBill/{id}")
    public Bill getbill(@PathVariable(name = "id") Long id) {
        Bill bill = billRepository.findById(id).get();
        Customer customer = customerRestClient.getCustomerbyid(bill.getCustomerID());
        bill.setCustomer(customer);
        bill.getProductItems().forEach(
                pi -> {
                    Product product = productRestClient.getProductById(pi.getProduct_ID());
                    pi.setProduct(product);

                }
        );
        return bill;
    }
}
