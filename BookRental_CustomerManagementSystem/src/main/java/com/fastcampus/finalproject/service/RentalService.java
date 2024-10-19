package com.fastcampus.finalproject.service;

import com.fastcampus.finalproject.dto.RentalAmountDTO;
import com.fastcampus.finalproject.dto.RentalDTO;
import com.fastcampus.finalproject.repository.RentalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RentalService {
    // @RequiredArgsConstructor를 사용하려면 final 키워드 꼭 붙일것
    private final RentalRepository rentalRepository;
    private final DataSource ds;

    public List<RentalDTO> getRentalList() {
        List<RentalDTO> rentalList = new ArrayList<>();

        try (Connection conn = ds.getConnection();
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM rent_tbl");
            ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                RentalDTO rentalDTO = new RentalDTO();
                rentalDTO.setCust_no(rs.getInt("cust_no"));
                rentalDTO.setRent_no(rs.getInt("rent_no"));
                rentalDTO.setBook_code(rs.getString("book_code"));
                rentalDTO.setRent_price(rs.getInt("rent_price"));
                rentalDTO.setRent_date(rs.getDate("rent_date"));

                rentalList.add(rentalDTO);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            // 예외 처리 또는 throw 등을 수행
        }

        return rentalList;
    }

    public List<RentalAmountDTO> getRentalAmountList() {
        return rentalRepository.getRentalAmountList();
    }
}
