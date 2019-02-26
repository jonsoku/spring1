	<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page session="true" %>

<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta http-equiv="X-UA-Compatible" content="ie=edge">
	<title>김스쿨</title>
	
	<!-- style -->
	<link rel="stylesheet" href="css/font.css">
	<link rel="stylesheet" href="css/font-awesome.css">
	<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.6.3/css/all.css" integrity="sha384-UHRtZLI+pbxtHCWp1t77Bi1L4ZtiqrqD80Kn4Z8NTSRyMA2Fd33n5dQ8lWUE00s/" crossorigin="anonymous">
	<link rel="stylesheet" href="resources/css/standard/reset.css">
	<link rel="stylesheet" href="resources/css/standard/standard.css">	
	<link rel="stylesheet" href="resources/css/standard/header.css">
	<link rel="stylesheet" href="resources/css/standard/footer.css">
	<link rel="stylesheet" href="resources/css/index/banner.css">
	<link rel="stylesheet" href="resources/css/index/contents.css">
	<!--[If IE 7]>
	    <script type="text/javascript">
	        alert("Please Upgrade Your BROWSER !");
	    </script>
	<![endif]-->

	<!--[if lt IE 9]>
	    <script src="resources/js/html5shiv.min.js"></script>
	<![endif]-->

	<!-- script -->
	<script src="resources/js/ie-checker.js"></script>
</head>
<body>
<!-- 
	<div class="login login_none">
		<div class="login_form">
				<div class="login_exit"><i class="far fa-times-circle"></i></div>
				<br>
				<input type="text" placeholder="dadsd"/>
				<br>
				<input type="text" placeholder="dadsd"/>
				<br>
				<input type="text" placeholder="dadsd"/>
				<br>
				<input type="text" placeholder="dadsd"/>
				<br>
			</div>
	</div> -->

	<div id="wrap">
		<div id="header">
			<div class="container">
				<div class="header"><!--편의상( 나중에 css작업할때 편함 )-->
					<h1 class="logo">
						<a href="#"></a>
					</h1>
					<div id="top-menu" class="top-menu">
						<ul>
							<c:if test="${loginId == null}">
								<li><a href="/kimschool/member/memberLogin">로그인</a></li>
								<li><a href="/kimschool/member/memberJoin">회원가입</a></li>								
							</c:if>
							<c:if test="${loginId != null }">
								<li><a href="/kimschool/member/memberLogout">로그아웃</a>
							</c:if>
							
							<li><a href="">日本語</a></li>
						</ul>
					</div>
					<nav id="gnb" class="gnb">
						<ul>
							<li><a href="#">학원소개</a></li>
							<li><a href="#">수강신청</a></li>
							<li><a href="/kimschool/board/noticeBoardList">질문답변</a></li>
							<li><a href="/kimschool/grade/gradeInsert">준열씨테스트</a></li>
							<li><a href="#">출석체크</a></li>
						</ul>
					</nav>
				</div>
			</div>
		</div><!--//header-->
		<div id="banner">
			<div id="slider">
				<div class="slider">
					<ul>
						<li>
							<a href="#">
							<div class="container">
								<div class="slider-info">
									<p class="slider-subtit">자바 기초반 수강생들을 위한</p>
									<p class="slider-maintit">자바 중급반 개설 !</p>
									<p class="slider-date">2019년 1월 25일 ~ 2019년 2월 9일</p>
									<p class="slider-btn">more  <i class="far fa-plus-square"></i></p>
								</div>
							</div>
							<img src="http://placeimg.com/1600/420/animals" alt="">
							</a>
						</li>
						<li>
							<a href="#">
							<div class="container">
								<div class="slider-info">
									<p class="slider-subtit">홈페이지를 꾸미고싶으신 분을 위한</p>
									<p class="slider-maintit">HTML / CSS 강좌 개설</p>
									<p class="slider-date">2019년 1월 25일 ~ 2019년 2월 9일</p>
									<p class="slider-btn">more  <i class="far fa-plus-square"></i></p>
								</div>
							</div>
							<img src="http://placeimg.com/1600/420/tech" alt="">
							</a>
						</li>
						<li>
							<a href="#">
							<div class="container">
								<div class="slider-info">
									<p class="slider-subtit">빨리 취업하고싶은 분을 위한</p>
									<p class="slider-maintit">일본어 면접스킬 Tip!</p>
									<p class="slider-date">2019년 1월 25일 ~ 2019년 2월 9일</p>
									<p class="slider-btn">more  <i class="far fa-plus-square"></i></p>
								</div>
							</div>
							<img src="http://placeimg.com/1600/420/nature" alt="">
							</a>
						</li>
												<li>
							<a href="#">
							<div class="container">
								<div class="slider-info">
									<p class="slider-subtit">뭔가 느낌이좋아 ~~</p>
									<p class="slider-maintit">자바스크립트까지 가즈아!</p>
									<p class="slider-date">2019년 1월 25일 ~ 2019년 2월 9일</p>
									<p class="slider-btn">more  <i class="far fa-plus-square"></i></p>
								</div>
							</div>
							<img src="http://placeimg.com/1600/420/anys" alt="">
							</a>
						</li>
						
					</ul>
				</div>
			</div><!--//banner-->
			<div class="dot"></div>
			<div id="arrow">
				<div class="arrow leftArrow"><i class="fas fa-angle-left"></i></div>
				<div class="arrow rightArrow"><i class="fas fa-angle-right"></i></div>
			</div>			
		</div>
		<div id="contents">
			<div id="cont1">
				<div class="container">
					<div class="cont1">
						<div class="cbox">
							<div class="cbox1 cbox1-1">
								<p>With KIMSCHOOL</p>
								<p>김스쿨을 더 편리하게 이용하세요.</p>
								<div class="cform cform_login"><a href="/kimschool/member/memberLogin">LOGIN</a></div>
								<div class="cform cform_join"><a href="/kimschool/member/memberJoin">JOIN</a></div>
							</div>
							<div class="cbox1 cbox1-2">
								<p>Admission</p>
								<p>수강료 안내/납부</p>
								<p><i class="fas fa-chalkboard"></i></p>
							</div>
							<div class="cbox1 cbox1-3">
								<p>Youtube</p>
								<p>온라인 학습</p>
								<p><i class="fab fa-youtube"></i></p>
							</div>
							<div class="cbox1 cbox1-4">
								<p>Your Score</p>
								<p>성적관리시스템</p>
								<p><i class="far fa-star"></i></p>
							</div>
							<div class="cbox1 cbox1-5">
								<p>KIMSCHOOL SUBJECT</p>										
								<div class="subboard-select">
									<select name="java" size="1" >
										<option value="">JAVA</option>
										<option value="jb">JAVA 기초</option>
										<option value="jm">JAVA 중급</option>
										<option value="ja">JAVA 고급</option>
									</select>
									<select name="web" size="1" >
										<option value="">WEB</option>
										<option value="wb">WEB 기초</option>
										<option value="wm">WEB 중급</option>
										<option value="wa">WEB 고급</option>
									</select>
									<select name="php" size="1" >
										<option value="">PHP</option>
										<option value="wb">PHP 기초</option>
										<option value="wm">PHP 중급</option>
										<option value="wa">PHP 고급</option>
									</select>
								</div>		
							</div>							
						</div>
						<div class="cbox">
								<div class="cbox2 cbox2-1">
									<h2>학사 일정</h2>
									<div class="kimsche">
										<ul>
											<li><p>2019학년도 복학신청</p><p>19.01.21(Mon)~19.01.28(Mon)</p></li>
											<li><p>2019학년도 복학신청</p><p>19.01.21(Mon)~19.01.28(Mon)</p></li>
											<li><p>2019학년도 복학신청</p><p>19.01.21(Mon)~19.01.28(Mon)</p></li>
											<li><p>2019학년도 복학신청</p><p>19.01.21(Mon)~19.01.28(Mon)</p></li>
											<li><p>2019학년도 복학신청</p><p>19.01.21(Mon)~19.01.28(Mon)</p></li>
										</ul>
									</div>														
								</div>
								<div class="cbox2 cbox2-2">
									<h2>공지사항</h2>
										<table>
										<tr>
											<td class="notice-tit"><a href="#">더미데이터입니다더미데이터입니다더미데이터입니다더미데이터입니다더미데이터입니다더미데이터입니다</a></td>
											<td class="notice-date">2018. 01. 04</td>
										</tr>
										<tr>
											<td class="notice-tit">더미데이터입니다더미데이터입니다</td>
											<td class="notice-date">2018. 01. 04</td>
										</tr>
										<tr>
											<td class="notice-tit">더미데이터입니다더미데이터입니다</td>
											<td class="notice-date">2018. 01. 04</td>
										</tr>
										<tr>
											<td class="notice-tit">더미데이터입니다더미데이터입니다</td>
											<td class="notice-date">2018. 01. 04</td>
										</tr>
									</table>
								</div>
								<div class="cbox2 cbox2-3">
									<p>KIMSCHOOL SNS</p>
									<p>소셜네트워크</p>
									<p><i class="fab fa-line line"></i><i class="fab fa-facebook-square facebook"></i><i class="fab fa-instagram instagram"></i></p>
								</div>							
							</div>
					</div>
				</div>
			<div id="cont2">
				<div class="container">
					<div class="cont2">cont2</div>
				</div>
			</div>
			<div id="cont3">
				<div class="container">
					<div class="cont3">cont3</div>
				</div>
			</div>
		</div><!--contents-->
		<div id="footer">
			<div id="footer_notice">
				<div class="container">
					footer_notice
				</div>
			</div>
			<div id="footer_info">
				<div class="container">
					footer_info
				</div>
			</div>
		</div><!--footer-->



	</div>
	


	<script src="resources/js/ban_slider.js"></script>
</body>
</html>

