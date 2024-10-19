<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>고객등록</title>

<link href="/css/registerCustomers.css" rel="stylesheet" type="text/css">
<!-- JavaScript 파일 링크 -->
<script type="text/javascript" src="/js/registerCustomers.js"></script>
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
                <form action="/customer/signup" method="post" id="section_div_form">
                    <div id="form_div">
                        <label class="section_form_label" for="cust_no">고객번호&nbsp;:<input type="text" id="cust_no" name="cust_no" value="${value}"></label>
                        <label class="section_form_label" for="cust_name">고객이름&nbsp;:<input type="text" id="cust_name" name="cust_name" autofocus="autofocus"></label>
                        <label class="section_form_label" for="phone">전화번호&nbsp;:<input type="text" id="phone" name="phone" placeholder="010-XXXX-XXXX"></label>
                        <label class="section_form_label" for="cust_email">이메일&nbsp;:<input type="email" id="cust_email" name="cust_email" placeholder="example@naver.com"></label>
                        <label class="section_form_label" for="grade">고객 등급(P,G,S)&nbsp;:<input type="text" id="grade" name="grade" placeholder="P : Platinum / G : GOLD / S : Silver"></label>
                        <div id="form_btns">
                            <input type="submit" onclick="return validateForm();" value="등록">
                            <input type="reset" value="취소">
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </section>
    <footer>
        <h2 id="footer_h2">나도 할 수 있는 Java & Spring 웹 개발 종합반 (문승기)</h2>
    </footer>
</body>
</html>