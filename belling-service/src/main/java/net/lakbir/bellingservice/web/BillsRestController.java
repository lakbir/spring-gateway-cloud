package net.lakbir.bellingservice.web;

import lombok.AllArgsConstructor;
import net.lakbir.bellingservice.entities.Bill;
import net.lakbir.bellingservice.feign.CustomerRestClient;
import net.lakbir.bellingservice.feign.ProductRestClient;
import net.lakbir.bellingservice.repository.BillRepository;
import net.lakbir.bellingservice.repository.ProductItemRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class BillsRestController {

    private BillRepository billRepository;
    private ProductItemRepository productItemRepository;
    private ProductRestClient productRestClient;
    private CustomerRestClient customerRestClient;

    @GetMapping("/bills/{id}")
    public Bill getBill(@PathVariable Long id) {
        Bill bill = billRepository.findById(id).get();
        bill.setCustomer(customerRestClient.findCustomerById(bill.getCustomerId()));
        bill.getProductItems().forEach(productItem -> {
            productItem.setProduct(productRestClient.findProductById(productItem.getProductId()));
        });
        return bill;
    }
}
