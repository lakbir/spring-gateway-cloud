package net.lakbir.bellingservice;


import net.lakbir.bellingservice.dto.Customer;
import net.lakbir.bellingservice.dto.Product;
import net.lakbir.bellingservice.entities.Bill;
import net.lakbir.bellingservice.entities.ProductItem;
import net.lakbir.bellingservice.feign.CustomerRestClient;
import net.lakbir.bellingservice.feign.ProductRestClient;
import net.lakbir.bellingservice.repository.BillRepository;
import net.lakbir.bellingservice.repository.ProductItemRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.util.Collection;
import java.util.Date;
import java.util.Random;

@SpringBootApplication
@EnableFeignClients
public class BellingServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BellingServiceApplication.class, args);
    }
    @Bean
    CommandLineRunner commandLineRunner(BillRepository billRepository,
                                        ProductItemRepository productItemRepository,
                                        CustomerRestClient customerRestClient,
                                        ProductRestClient productRestClient){

        return args -> {
            Collection<Customer> customers = customerRestClient.findAllCustomers().getContent();
            Collection<Product> products = productRestClient.findAllProducts().getContent();
            System.out.println(customers.toString());
            System.out.println(products.toString());
           /* customers.forEach(customer -> {
                Bill bill = Bill.builder()
                        .billingDate(new Date())
                        .customerId(customer.getId())
                        .build();
                billRepository.save(bill);
                products.forEach(product -> {
                    ProductItem productItem = ProductItem.builder()
                            .bill(bill)
                            .productId(product.getId())
                            .quantity(1+new Random().nextInt(10))
                            .unitPrice(product.getPrice())
                            .build();
                    productItemRepository.save(productItem);
                });
            });*/
        };
    }

}