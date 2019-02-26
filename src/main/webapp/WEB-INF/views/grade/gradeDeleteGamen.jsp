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
	location.href ='gradeDelete?memberId='+memberId+'&name='+name;
}

</script>


</head>
<body>
<h2>gradeDeleteGamen 페이지</h2>


	<input type="text"  placeholder="삭제할 맴버 아이디를 입력하세요">
	<input type="submit" value="삭제" onclick="deleteGrade('${memberId }','${name }')"><br>


</body>
</html>