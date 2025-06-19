package com.springboot.point_of_sale.service.impl;

import com.springboot.point_of_sale.dto.CustomerDTO;
import com.springboot.point_of_sale.dto.request.CustomerUpdateDTO;
import com.springboot.point_of_sale.entity.Customer;
import com.springboot.point_of_sale.exceptions.NotFoundException;
import com.springboot.point_of_sale.repo.CustomerRepo;
import com.springboot.point_of_sale.service.CustomerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class CustomerServiceIMPL implements CustomerService {

    @Autowired
    public CustomerRepo customerRepo;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public String customerSave(CustomerDTO customerDTO) {
        Customer customer = modelMapper.map(customerDTO, Customer.class);

        customerRepo.save(customer);
        return customer.getCustomerName();
    }

    @Override
    public String updateCustomer(CustomerUpdateDTO customerUpdateDTO) {
        if (customerRepo.existsById(customerUpdateDTO.getCustomerId())) {
            Customer customer = modelMapper.map(customerUpdateDTO, Customer.class);

            customerRepo.save(customer);
            return "customer saved" ;
        }else {
            throw new NotFoundException("Customer not found");
        }
    }

    @Override
    public CustomerDTO getCustomerByID(int id) {
        if (customerRepo.existsById(id)) {
            Customer customer = customerRepo.getReferenceById(id);
            CustomerDTO customerDTO = new CustomerDTO(
                    customer.getCustomerId(),
                    customer.getCustomerName(),
                    customer.getCustomerAddress(),
                    customer.getCustomerSalary(),
                    customer.getCustomerNumber(),
                    customer.getNic(),
                    customer.isActive()
            );
            return customerDTO;
        }else {
            throw new NotFoundException("Customer not found");
        }
    }

    @Override
    public List<CustomerDTO> getAllCustomers() {
        List<Customer> getAllCustomers = customerRepo.findAll();
        List<CustomerDTO> customerDTOList = new ArrayList<>();

        for (Customer customer : getAllCustomers) {
            CustomerDTO customerDTO = new CustomerDTO(
                    customer.getCustomerId(),
                    customer.getCustomerName(),
                    customer.getCustomerAddress(),
                    customer.getCustomerSalary(),
                    customer.getCustomerNumber(),
                    customer.getNic(),
                    customer.isActive()
            );
            customerDTOList.add(customerDTO);
        }
        return customerDTOList;
    }

    @Override
    public String customerDelete(int id) {
        if(customerRepo.existsById(id)) {
            customerRepo.deleteById(id);
            return "customer deleted" ;
        }else {
            throw new NotFoundException("Customer not found");
        }
    }

    @Override
    public List<CustomerDTO> getAllCustomersByActiveState(boolean status) {
        List<Customer> getAllCustomers = customerRepo.findAllByActiveEquals(status);
        List<CustomerDTO> customerDTOList = new ArrayList<>();

        for (Customer customer : getAllCustomers) {
            CustomerDTO customerDTO = new CustomerDTO(
                    customer.getCustomerId(),
                    customer.getCustomerName(),
                    customer.getCustomerAddress(),
                    customer.getCustomerSalary(),
                    customer.getCustomerNumber(),
                    customer.getNic(),
                    customer.isActive()
            );
            customerDTOList.add(customerDTO);
        }
        return customerDTOList;
    }

}
