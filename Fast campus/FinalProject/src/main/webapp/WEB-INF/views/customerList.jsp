<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>고객 목록 / 조회</title>

<link href="/css/customerList.css" rel="stylesheet" type="text/css">
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
            <h3>고객등록화면</h3>
        </div>
        <div id="section_div2">
            <table>
                <thead>
                <tr>
                    <th>고객번호</th>
                    <th>고객이름</th>
                    <th>전화번호</th>
                    <th>가입일자</th>
                    <th>이메일</th>
                    <th>고객등급</th>
                </tr>
                </thead>
                <tbody>
                <!-- 서버에서 전달받은 고객 목록을 반복하면서 출력 -->
                <c:forEach var="customer" items="${customerList}">
                    <tr>
                        <td><a href="/customer/edit/${customer.cust_no}">${customer.cust_no}</td>
                        <td>${customer.cust_name}</td>
                        <td>${customer.phone}</td>
                        <td>${customer.join_date}</td>
                        <td>${customer.cust_email}</td>
                        <td>
                            <c:choose>
                                <c:when test="${customer.grade eq 'P'}">Platinum</c:when>
                                <c:when test="${customer.grade eq 'G'}">Gold</c:when>
                                <c:when test="${customer.grade eq 'S'}">Silver</c:when>
                            </c:choose>
                        </td>
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
