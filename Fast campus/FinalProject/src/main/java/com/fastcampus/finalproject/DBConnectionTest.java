package com.fastcampus.finalproject;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DBConnectionTest {
    public static void main(String[] args) throws Exception {
        // String DB_URL = "jdbc:mysql://127.0.0.1:3306/finalproject?useUnicode=true&characterEncoding=utf-8";
        //
        // String DB_USER = "seung";
        // String DB_PASSWORD = "anstmdrl21!";
        //
        // Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        // Statement stmt = conn.createStatement();
        //
        // String query = "SELECT now()";
        // ResultSet rs = stmt.executeQuery(query);
        //
        // while(rs.next()) {
        //     String curDate = rs.getString(1);
        //     System.out.println(curDate);
        // }
        
        // root-context.xml 파일에 등록을 해두어서 아래와 같이 작성하여 사용 가능
        ApplicationContext ac = new GenericXmlApplicationContext("file:src/main/webapp/WEB-INF/spring/**/root-context.xml");
        DataSource ds = ac.getBean(DataSource.class);

        Connection conn = ds.getConnection(); // 데이터베이스의 연결을 얻는다.

        System.out.println("conn = " + conn);

    }
}
