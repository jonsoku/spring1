<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<title>공지사항</title>

<script>
/* 해당 id속성에 대한 Form을 통한 결과값을 Controller단으로 전달하기 위한 function  */
function pagingFormSubmit(currentPage) {
	var form = document.getElementById('pagingForm');
	var page = document.getElementById('page');
	page.value = currentPage;
	form.submit();
}
</script>
</head>

<style type="text/css">

.container {
 width: 300px;
 text-align: center;
 margain: auto;
 text-decoration: none;
}

</style>
<body>
	<h2>공지사항</h2>
	
	<div align="right" style="margin: 15px;">
	<a href="noticeBoardWrite" style="text-decoration: none;">글쓰기</a>
	
	</div>
	
	<table>
		<!-- 표의 머리부분  -->
		<thead>
			<tr>
				<th>글번호</th>
				<th>글제목</th>
				<th>작성자</th>
				<th>작성일</th>
				<th>조회수</th>
			</tr>
		</thead>
		<!-- 표의 내용부분  -->
		<tbody>
		
		
			<c:forEach var="noticeBoard" items="${noticeBoardList }">

				<tr>
				<c:if test="${noticeBoard.delFlg == '0' }">
					<td>${noticeBoard.postOrder+1 }</td>		
					<!-- ? : get기능(getter)으로 인식  -->
					<td><a href="noticeBoardShowOne?boardNum=${noticeBoard.boardNum }">${noticeBoard.title } </a></td>
					<td>${noticeBoard.memberId }</td>
					<td>${noticeBoard.regDate }</td>
					<td>${noticeBoard.boardCount }</td>
				</c:if>
				<c:if test="${noticeBoard.delFlg == '1' }">
				<td>${noticeBoard.postOrder+1 }</td>		
					<!-- ? : get기능(getter)으로 인식  -->
					<td colspan="4" align="center">작성자에 의해 삭제된 게시글입니다</td>
					
				</c:if>
				
				</tr>
			</c:forEach>
		</tbody>
	</table>
	
	<!-- 페이징처리 -->
	<div class ="container">
		<!-- 페이지 이동 부분  -->
		<!-- javasript:pagingdFormSubmit 페이지관리할 때 사용하는 function -->
		<a href="javasript:pagingFormSubmit(${navi.currentPage-navi.pagePerGroup })" style="text-decoration: none;">⇦⇦</a>　&nbsp;&nbsp;
		<a href="javasript:pagingFormSubmit(${navi.currentPage-1 })" style="text-decoration: none;">⇦</a>　&nbsp;&nbsp;
		
		<c:forEach var="counter" begin="${navi.startPageGroup }" end="${navi.endPageGroup }">
			<c:if test="${counter==navi.currentPage }">
			<b><a href="javasript:pagingFormSubmit(${counter })">${counter }</a></b> 
			</c:if> 
		</c:forEach> &nbsp;&nbsp;
		
		<!-- 다음페이지 또는 마지막 페이지로 가기 -->
		<a href="javasript:pagingFormSubmit(${navi.currentPage+1 })" style="text-decoration: none;">➡</a>　&nbsp;&nbsp;
		<a href="javasript:pagingFormSubmit(${navi.currentPage+navi.pagePerGroup })" style="text-decoration: none;">➡➡</a>　&nbsp;&nbsp;
		
		<!-- 페이지 이동 처리 끝 --> <br><br>
		
		<!-- 검색 바 만들기 -->
		<!-- NoticeBoardController의 @RequestMapping에서 noticeBoardList를 action값과 일치시켜 전달받을 수 있도록 설정 -->
		<form id="pagingForm" method="get" action="noticeBoardList">
			<!-- name값은 @RequestParam의 value값을 일치시켜 전달받을 수 있도록 설정 -->
			<input type="hidden" name="page" id="page"></input>
			제목 : <input type="text" id="search" name="searchText" value="${searchText }"></input>
			<input type="button" onclick="pagingFormSubmit(1)" value="검색"></input>
		</form>
		<!-- 검색끝 -->
	</div>
</body>
</html>