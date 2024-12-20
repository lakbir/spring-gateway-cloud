package net.lakbir.customerservice.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RefreshScope
public class ConfigTestRestController {

    @Value("${global.params.p1}")
    private String p1;

    @Value("${global.params.p2}")
    private String p2;

    @Autowired
    private CustomerConfigParams customerConfigParams;

    @GetMapping("/test")
    public Map<String, String> getParams() {
        return Map.of("p1", p1, "p2", p2);
    }

    @GetMapping("/test_config")
    public CustomerConfigParams getParams2() {
        return customerConfigParams;
    }
}