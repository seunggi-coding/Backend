package com.fastcampus.finalproject.dto;

import lombok.Data;

import java.sql.Date;

@Data
public class CustomerDTO {
    private int cust_no;
    private String cust_name;
    private String phone;
    private Date join_date;
    private String cust_email;
    private String grade;

}
