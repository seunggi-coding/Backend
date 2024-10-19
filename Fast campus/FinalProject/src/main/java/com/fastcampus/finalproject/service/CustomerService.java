package com.fastcampus.finalproject.service;

import com.fastcampus.finalproject.dto.CustomerDTO;
import com.fastcampus.finalproject.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {
    // ApplicationContext ac = new GenericXmlApplicationContext("file:src/main/webapp/WEB-INF/spring/**/root-context.xml");

    // @RequiredArgsConstructor를 사용하려면 final 키워드 꼭 붙일것
    private final CustomerRepository customerRepository;
    private final DataSource ds;

    public int save(CustomerDTO customerdto) {

        try (Connection conn = ds.getConnection();
            PreparedStatement pstmt = conn.prepareStatement("insert into member_tbl values (?, ?, ?, now(), ?, ?)")) {
            pstmt.setInt(1, customerdto.getCust_no());
            pstmt.setString(2, customerdto.getCust_name());
            pstmt.setString(3, customerdto.getPhone());
            pstmt.setString(4, customerdto.getCust_email());
            pstmt.setString(5, customerdto.getGrade());

            int rowCnt = pstmt.executeUpdate();
            System.out.println("customerdto.getCust_name() = " + customerdto.getCust_name());
            System.out.println("rowCnt = " + rowCnt);
            return rowCnt;
        } catch (SQLException e) {
            e.printStackTrace();
            // 예외 처리 또는 throw 등을 수행
            return 0; // 예외 발생 시 0 또는 다른 기본값 반환
        }
    }

    public int getMaxCustNo() throws SQLException {
        try (Connection conn = ds.getConnection()) {
            String sql = "SELECT MAX(cust_no) FROM member_tbl";
            try (PreparedStatement pstmt = conn.prepareStatement(sql);
                ResultSet rs = pstmt.executeQuery()) {
                if (rs.next() && rs.getObject(1) != null) {
                    System.out.println("최대 cust_no: " + rs.getInt(1));
                    return rs.getInt(1);  // 최대 cust_no 값 반환
                }
            }
        }
        // 결과가 없을 경우, 0 또는 다른 기본값을 반환할 수 있습니다.
        return 0;
    }

    public List<CustomerDTO> getCustomerList() {
        List<CustomerDTO> customerList = new ArrayList<>();

        try (Connection conn = ds.getConnection();
            PreparedStatement pstmt = conn.prepareStatement("select * from member_tbl");
            ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                CustomerDTO customerDTO = new CustomerDTO();
                customerDTO.setCust_no(rs.getInt("cust_no"));
                customerDTO.setCust_name(rs.getString("cust_name"));
                customerDTO.setPhone(rs.getString("phone"));

                // join_date가 Date 타입인 경우
                Date join_date = rs.getDate("join_date");
                customerDTO.setJoin_date(join_date);
                System.out.println("join_date = " + join_date);

                customerDTO.setCust_email(rs.getString("cust_email"));
                customerDTO.setGrade(rs.getString("grade"));

                customerList.add(customerDTO);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            // 예외 처리 또는 throw 등을 수행
        }

        return customerList;
    }

    public CustomerDTO getCustomerByNo(int custNo) {
        CustomerDTO customerDTO = new CustomerDTO();

        try (Connection conn = ds.getConnection();
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM member_tbl WHERE cust_no = ?")) {

            pstmt.setInt(1, custNo);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    customerDTO.setCust_no(rs.getInt("cust_no"));
                    customerDTO.setCust_name(rs.getString("cust_name"));
                    customerDTO.setPhone(rs.getString("phone"));
                    customerDTO.setJoin_date(rs.getDate("join_date"));
                    customerDTO.setCust_email(rs.getString("cust_email"));
                    customerDTO.setGrade(rs.getString("grade"));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return customerDTO;
    }

    public int update(CustomerDTO customerdto) {
        try (Connection conn = ds.getConnection();
            PreparedStatement pstmt = conn.prepareStatement("UPDATE member_tbl SET cust_name=?, phone=?, cust_email=?, grade=? WHERE cust_no=?")) {

            pstmt.setString(1, customerdto.getCust_name());
            pstmt.setString(2, customerdto.getPhone());
            pstmt.setString(3, customerdto.getCust_email());
            pstmt.setString(4, customerdto.getGrade());
            pstmt.setInt(5, customerdto.getCust_no());

            return pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }
}
