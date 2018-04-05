package com.msa.customer.notification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class CustomerRegistrar {

    private CustomerRepository customerRepository;
    private Sender sender;

    @Autowired
    public CustomerRegistrar(CustomerRepository customerRepository, Sender sender) {
        this.customerRepository = customerRepository;
        this.sender = sender;
    }

    public Mono<Customer> register(Customer customer) {
        if(customerRepository.findByName(customer.getName()).isPresent()) {
            System.out.println("Duplicated");
        } else {
            customerRepository.save(customer);
            sender.send(customer.getEmail());
        }

        return Mono.just(customer);
    }
}
