<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>고객 대여금액 조회</title>

    <link href="/css/rentalAmount.css" rel="stylesheet" type="text/css">
</head>
<body>
    <header>
        <h1 id="header_h1">도서 고객 및 대여관리 프로그램 ver 1.0</h1>
    </header>
    <nav>
        <ul id="nav_ul">
            <li class="nav_li"><a class="nav_li_a" href="/customer/register">고객등록</a></li>
            <li class="nav_li"><a class="nav_li_a" href="/customer/list">고객목록/조회</a></li>
            <li class="nav_li"><a class="nav_li_a" href="/rental/list">고객대여리스트</a></li>
            <li class="nav_li"><a class="nav_li_a" href="/rental/amount">고객대여금액조회</a></li>
            <li class="nav_li"><a class="nav_li_a" href="/">홈으로</a></li>
        </ul>
    </nav>
    <section>
        <div id="section_div">
            <div id="section_div_h3">
                <h3>고객대여금액조회</h3>
            </div>
            <div id="section_div2">
                <table>
                    <thead>
                    <tr>
                        <th>고객번호</th>
                        <th>고객이름</th>
                        <th>고객등급</th>
                        <th>총대여금액</th>
                    </tr>
                    </thead>
                    <tbody>
                    <!-- 서버에서 전달받은 조회 결과를 반복하면서 출력 -->
                    <c:forEach var="result" items="${rentalAmountList}">
                        <tr>
                            <td>${result.cust_no}</td>
                            <td>${result.cust_name}</td>
                            <td>
                                <c:choose>
                                    <c:when test="${result.grade eq 'P'}">Platinum</c:when>
                                    <c:when test="${result.grade eq 'G'}">Gold</c:when>
                                    <c:when test="${result.grade eq 'S'}">Silver</c:when>
                                </c:choose>
                            </td>
                            <td>${result.total_rental_amount}</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </section>
    <footer>
        <h2 id="footer_h2">나도 할 수 있는 Java & Spring 웹 개발 종합반 (문승기)</h2>
    </footer>
</body>
</html>
