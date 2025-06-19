package com.springboot.point_of_sale.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomerUpdateDTO {
    private int customerId;
    private String customerName;
    private String customerAddress;
    private double customerSalary;


}
