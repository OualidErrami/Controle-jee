package com.example.billingservice.feign;

import com.example.billingservice.model.Customer;
import com.example.billingservice.model.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "PRODUCT-SERVICE")
public interface ProductRestClient {
    @GetMapping("/products/{id}")
    Product getProductById(@PathVariable(name = "id") Long id);
    @GetMapping("/products")
    PagedModel<Product> pageProducts();
}
