package com.example.billingservice;

import com.example.billingservice.entities.Bill;
import com.example.billingservice.entities.ProductItem;
import com.example.billingservice.feign.CustomerRestClient;
import com.example.billingservice.feign.ProductRestClient;
import com.example.billingservice.model.Customer;
import com.example.billingservice.model.Product;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import com.example.billingservice.repositories.BillRepository;
import com.example.billingservice.repositories.ProductItemRepository;
import org.springframework.hateoas.PagedModel;

import java.util.Date;
import java.util.Random;

@SpringBootApplication
@EnableFeignClients
public class BillingServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BillingServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner start(
			BillRepository billRepository,
			ProductItemRepository itemRepository,
			CustomerRestClient restClient,
			ProductRestClient productRestClient
	){

		return  args -> {
			Customer customer=restClient.getCustomerbyid(1L);
			Bill bill1 = billRepository.save(new Bill(1L,new Date(),null,customer.getId(),customer));
			System.out.println("---------------");
			System.out.println(customer.getId());
			System.out.println(customer.getName());
			System.out.println(customer.getEmail());
			PagedModel<Product> productPagedModel=productRestClient.pageProducts();
			productPagedModel.forEach(
					p -> {
						ProductItem productItem=new ProductItem();
						productItem.setPrice(p.getPrice());
						productItem.setQuantity(1+new Random().nextInt(100));
						productItem.setBill(bill1);
						productItem.setProduct_ID(p.getId());
						productItem.setCustomer(customer);
						itemRepository.save(productItem);
					}
			);
		};
	}

}
