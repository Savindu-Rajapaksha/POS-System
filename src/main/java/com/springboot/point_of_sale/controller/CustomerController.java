package com.springboot.point_of_sale.controller;

import com.springboot.point_of_sale.dto.CustomerDTO;
import com.springboot.point_of_sale.dto.request.CustomerUpdateDTO;
import com.springboot.point_of_sale.service.CustomerService;
import com.springboot.point_of_sale.util.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("api/v1/customer")

public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping(path = "/save")
    public ResponseEntity<StandardResponse> customerSave(@RequestBody CustomerDTO customerDTO){
        String message = customerService.customerSave(customerDTO);

        return new ResponseEntity<StandardResponse>(
                new StandardResponse(201, "Success", message),
                HttpStatus.CREATED
        );
    }

    @PutMapping("/update")
    public ResponseEntity<StandardResponse> updateCustomer(@RequestBody CustomerUpdateDTO customerUpdateDTO){
        String message = customerService.updateCustomer(customerUpdateDTO);

        return new ResponseEntity<StandardResponse>(
                new StandardResponse(201, "Updated", message),
                HttpStatus.OK
        );
    }

    @GetMapping(path = "/get-by-id")
    public ResponseEntity<StandardResponse> getCustomerById(@RequestParam("id") int id){
        CustomerDTO customerDTO = customerService.getCustomerByID(id);

        return new ResponseEntity<StandardResponse>(
                new StandardResponse(201, "Updated", customerDTO),
                HttpStatus.OK
        );
    }

    @GetMapping(path = "/get-all-customers")
    public ResponseEntity<StandardResponse> getAllCustomers(){
        List<CustomerDTO> customerDTOList = customerService.getAllCustomers();
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(201, "Updated", customerDTOList),
                HttpStatus.OK
        );
    }

    @DeleteMapping(path = "delete-customer/{id}")
    public ResponseEntity<StandardResponse>  deleteCustomer(@PathVariable("id") int id){
        String delete = customerService.customerDelete(id);
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(201, "Updated", delete),
                HttpStatus.OK
        );
    }

    @GetMapping(path = "/get-all-customers-by-active-state/{status}")
    public ResponseEntity<StandardResponse> getAllCustomersByActiveState(@PathVariable("status") boolean status){
        List<CustomerDTO> customerDTOList = customerService.getAllCustomersByActiveState(status);
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(201, "Updated", customerDTOList),
                HttpStatus.OK
        );
    }


}
