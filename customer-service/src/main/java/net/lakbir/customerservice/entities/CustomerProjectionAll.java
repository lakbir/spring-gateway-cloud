package net.lakbir.customerservice.entities;

import org.springframework.data.rest.core.config.Projection;

@Projection(name = "all", types = { Customer.class })
public interface CustomerProjectionAll {
    String getName();
    String getEmail();
}
