package com.serviceImplementations;

import com.DAO.CustomerDAO;
import com.entities.Customer;
import com.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerDAO customer;

    @Autowired
    public CustomerServiceImpl(CustomerDAO customer) {
        this.customer = customer;
    }

    @Override
    public List<Customer> getCustomers() {
        return customer.getCustomers();
    }
}
