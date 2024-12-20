package net.lakbir.bellingservice.feign;

import net.lakbir.bellingservice.dto.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "INVENTORY-SERVICE")
public interface ProductRestClient {

    @GetMapping("/api/products/{id}")
    Product findProductById(@PathVariable String id);

    @GetMapping("/api/products")
    PagedModel<Product> findAllProducts();
}
