package com.fastcampus.finalproject.dto;

import lombok.Data;

@Data
public class RentalAmountDTO {
    private int cust_no;
    private String cust_name;
    private String grade;
    private int total_rental_amount;
}
