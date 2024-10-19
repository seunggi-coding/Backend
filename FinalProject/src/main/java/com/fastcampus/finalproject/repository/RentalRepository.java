package com.fastcampus.finalproject.repository;

import com.fastcampus.finalproject.dto.RentalAmountDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class RentalRepository {
    private final DataSource ds;
    public List<RentalAmountDTO> getRentalAmountList() {
        List<RentalAmountDTO> rentalAmountList = new ArrayList<>();

        try (Connection conn = ds.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(
                     "SELECT m.cust_no, m.cust_name, m.grade, SUM(r.rent_price) AS total_rental_amount " +
                             "FROM member_tbl m " +
                             "LEFT JOIN rent_tbl r ON m.cust_no = r.cust_no " +
                             "GROUP BY m.cust_no, m.cust_name, m.grade " +
                             "HAVING total_rental_amount IS NOT NULL " +
                             "ORDER BY total_rental_amount DESC"
             )) {

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    RentalAmountDTO rentalAmountDTO = new RentalAmountDTO();
                    rentalAmountDTO.setCust_no(rs.getInt("cust_no"));
                    rentalAmountDTO.setCust_name(rs.getString("cust_name"));
                    rentalAmountDTO.setGrade(rs.getString("grade"));
                    rentalAmountDTO.setTotal_rental_amount(rs.getInt("total_rental_amount"));

                    rentalAmountList.add(rentalAmountDTO);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            // 예외 처리 또는 throw 등을 수행
        }

        return rentalAmountList;
    }
}
