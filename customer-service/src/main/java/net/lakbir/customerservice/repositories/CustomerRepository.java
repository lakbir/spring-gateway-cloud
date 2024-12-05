package net.lakbir.customerservice.repositories;

import net.lakbir.customerservice.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository
@RepositoryRestResource
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
