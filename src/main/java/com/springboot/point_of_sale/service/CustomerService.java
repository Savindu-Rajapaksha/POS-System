package com.springboot.point_of_sale.service;

import com.springboot.point_of_sale.dto.CustomerDTO;
import com.springboot.point_of_sale.dto.request.CustomerUpdateDTO;

import java.util.List;

public interface  CustomerService {
    public String customerSave(CustomerDTO customerDTO);

    String updateCustomer(CustomerUpdateDTO customerUpdateDTO);

    CustomerDTO getCustomerByID(int id);

    List<CustomerDTO> getAllCustomers();

    String customerDelete(int id);

    List<CustomerDTO> getAllCustomersByActiveState(boolean status);
}
