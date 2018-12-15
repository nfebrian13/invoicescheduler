package com.nana.bankapp.services;

import java.util.List;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nana.bankapp.dao.CustomerDAO;
import com.nana.bankapp.model.Customer;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	CustomerDAO customerDAO;

	@Override
	@Transactional
	public boolean saveCustomer(Customer customer) {
		return customerDAO.saveCustomer(customer);
	}

	@Override
	@Transactional
	public boolean editCustomer(Customer customer) {
		return customerDAO.editCustomer(customer);
	}

	@Override
	@Transactional
	public List<Customer> getCustomers() {
		return customerDAO.getCustomers();
	}

	@Override
	@Transactional
	public Customer getCustomer(UUID customerId) {
		return customerDAO.getCustomer(customerId);
	}

	@Override
	@Transactional
	public boolean deleteCustomer(UUID customerId) {
		return customerDAO.deleteCustomer(customerId);
	}

}