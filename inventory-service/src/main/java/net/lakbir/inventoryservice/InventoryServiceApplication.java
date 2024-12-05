package net.lakbir.inventoryservice;

import net.lakbir.inventoryservice.entities.Product;
import net.lakbir.inventoryservice.repositories.ProductRepository;
import org.apache.catalina.core.ApplicationContext;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.UUID;

@SpringBootApplication
public class InventoryServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(InventoryServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(ProductRepository productRepository) {
        return args -> {
            for (int i = 0; i < 10; i++) {
                productRepository.save(Product.builder()
                        .id(UUID.randomUUID().toString())
                        .name("Product " + i)
                        .price(1243)
                        .quantity(2000)
                        .build());
            }

            productRepository.findAll().forEach(System.out::println);
        };
    }
}
