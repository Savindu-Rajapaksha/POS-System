package com.springboot.point_of_sale.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.ArrayList;

@Entity
@Table(name = "customer")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Customer {

    @Id
    @Column(name = "customer_id", length = 45)
    private int customerId;

    @Column(name = "customer_name", length = 100, nullable = false)
    private String customerName;

    @Column(name = "customer_address", length = 300)
    private String customerAddress;

    @Column(name = "customer_salary")
    private double customerSalary;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "customer_number", length = 100)
    private ArrayList customerNumber;

    @Column(name = "nic")
    private String nic;

    @Column(name = "active_state", columnDefinition = "TINYINT  default 0")
    private boolean active;


}
