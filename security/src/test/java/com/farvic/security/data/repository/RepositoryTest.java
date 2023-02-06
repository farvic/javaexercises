package com.farvic.security.data.repository;

import com.farvic.security.data.model.Customer;
import com.farvic.security.data.model.Order;
import org.apache.commons.collections4.IterableUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class RepositoryTest {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private CustomerRepository customerRepository;

    @Test
    public void testGetAllOrders() {
        Iterable<Order> orders = orderRepository.findAll();
        assert (IterableUtils.size(orders) == 3);
    }

    @Test
    public void testGetAllCustomers() {
        Iterable<Customer> customers = customerRepository.findAll();
        assert (IterableUtils.size(customers) == 7);
    }
}
