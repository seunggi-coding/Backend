package com.fastcampus.finalproject.dto;

import lombok.Data;

import java.sql.Date;

@Data
public class RentalDTO {
    private int cust_no;
    private int rent_no;
    private String book_code;
    private int rent_price;
    private Date rent_date;
}
