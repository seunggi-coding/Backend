<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>도서 고객 및 대여관리 프로그램 ver 1.0</title>

<link href="/css/index.css" rel="stylesheet" type="text/css">
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
	        <div id="section_div_h2">
	            <h2>도서 고객 및 대여관리 프로그램</h2>
	        </div>
	        <div id="section_div_list">
	            <h3>프로그램 작성 순서</h3>
	            <ol>
	                <li>고객정보 테이블을 생성한다.</li>
	                <li>대여정보 테이블을 생성한다.</li>
	                <li>고객정보, 대여정보 테이블에 제시된 데이터를 생성한다.</li>
	                <li>고객정보 입력 화면 프로그램을 작성한다.</li>
	                <li>고객정보 조회 프로그램을 작성한다.</li>
	                <li>고객대여리스트를 조회하는 프로그램을 작성한다.</li>
	                <li>고객별 대여금액을 조회하는 프로그램을 작성한다.</li>
	            </ol>
	        </div>
	    </div>
	</section>
	<footer>
	    <h2 id="footer_h2">나도 할 수 있는 Java & Spring 웹 개발 종합반 (문승기)</h2>
	</footer>
</body>
</html>