<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<script>
function deleteGrade(memberId,name){
	alert(name);
	location.href ='gradeDelete?memberId='+memberId+'&name='+name;
}

</script>


</head>
<body>
<h2>selectGrade 페이지</h2>
	

	<c:if test="${gradeEntityList.size() != 0 }" >
	<table border="1">
		<tr>
			<td>맴버id</td>
			<td>이름</td>
			<td>it점수</td>		
			<td>일본어점수</td>
			<td>삭제</td>
					
		</tr>

		<c:forEach var="gradeList" items="${gradeEntityList }">
		<tr>
			<td>${gradeList.memberId }</td>
			<td>${gradeList.name }</td>
			<td>${gradeList.itScore }</td>
			<td>${gradeList.japScore }</td>
			<td><button onclick="deleteGrade('${gradeList.memberId }','${gradeList.name }')">삭제</button></td>
			
		</tr>
		</c:forEach>
	</table>
	</c:if>
	<c:if test="${gradeEntityList.size() == 0 }" >
		<h2>no date</h2>
	</c:if>
</body>
</html>