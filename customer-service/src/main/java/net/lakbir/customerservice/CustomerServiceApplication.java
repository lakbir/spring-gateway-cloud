package net.lakbir.customerservice;


import net.lakbir.customerservice.entities.Customer;
import net.lakbir.customerservice.repositories.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CustomerServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomerServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(CustomerRepository  customerRepository) {
        return args -> {
            for (int i = 1; i <= 5; i++) {
                customerRepository.save(Customer.builder()
                        .name("Customer " + i)
                        .email("customer"+i+"@email.com")
                        .build());
            }
        };
    }
}
