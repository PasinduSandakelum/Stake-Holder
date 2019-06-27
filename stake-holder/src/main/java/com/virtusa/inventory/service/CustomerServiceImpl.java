package com.virtusa.inventory.service;

import com.virtusa.inventory.exception.AlreadyExistException;
import com.virtusa.inventory.modal.Customer;
import com.virtusa.inventory.modal.Telephone;
import com.virtusa.inventory.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Customer save(Customer customer) throws AlreadyExistException {
        if (customer.getTelephone() != null) {
            for (Telephone telephone : customer.getTelephone()) {
                telephone.setCustomer(customer);
            }
        }
        try {
            return customerRepository.save(customer);
        } catch (DataIntegrityViolationException e) {
            throw new AlreadyExistException("Some fields already Exists");
//            System.out.println("Already Exist");
//            e.printStackTrace();
        }

//        return null;
    }

    @Override
    public List<Customer> fetchAll() {
        return customerRepository.findAll();
    }

    @Override
    public Optional<Customer> findOne(Integer id) {
        return customerRepository.findById(id);
    }

    @Override
    public void deleteCustomer(Integer id) {
        customerRepository.deleteById(id);
    }

    @Override
    public Optional<Customer> fetchCustomer(Integer id) {

        return customerRepository.findById(id);
    }


}
