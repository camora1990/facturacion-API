package com.sofka.tiendaonline.service;

import com.sofka.tiendaonline.domain.Customer;
import com.sofka.tiendaonline.repository.CustomerRepository;
import com.sofka.tiendaonline.service.interfaces.ICrudServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class CustomerService implements ICrudServices<Customer> {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public List<Customer> getAll() {
        return customerRepository.findAll();
    }

    @Override
    public Customer create(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public Optional<Customer> get(Integer id) {
        return customerRepository.findById(id);
    }

    @Override
    public Optional<Customer> delete(Integer id) {
        var customer = customerRepository.findById(id);
        if (customer.isEmpty()) {
            return customer;
        }
        customerRepository.deleteById(id);
        return customer;
    }

    @Override
    public Customer update(Customer customer, Integer id) {
        var getCustomer = customerRepository.findById(id).orElse(null);
        if (getCustomer == null) {
            return null;
        }
        var name = customer.getName() == null ? getCustomer.getName() : customer.getName();
        var email = customer.getEmail() == null ? getCustomer.getEmail() : customer.getEmail();
        var celphone = customer.getCelPhone() == null ? getCustomer.getCelPhone() : customer.getCelPhone();
        Customer updateCustomer = new Customer(id, name, celphone, email);
        return customerRepository.save(updateCustomer);
    }
}
