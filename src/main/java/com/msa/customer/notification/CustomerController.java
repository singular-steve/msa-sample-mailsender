package com.msa.customer.notification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class CustomerController {

    private CustomerRegistrar customerRegistrar;

    @Autowired
    public CustomerController(CustomerRegistrar customerRegistrar) {
        this.customerRegistrar = customerRegistrar;
    }

    @RequestMapping(path = "/register", method = RequestMethod.POST)
    Mono<Customer> register(@RequestBody Customer customer) {
        return customerRegistrar.register(customer);
    }

}
