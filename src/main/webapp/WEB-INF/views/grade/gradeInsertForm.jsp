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
</head>
<body>
<h2>성적 입력화면</h2>
${loginId }
<br>
${gradeEntity }
<br>
${gradeEntity.memberId }
<br>
${gradeEntity.name }
<br>
${gradeEntity.itScore }
<br>
${gradeEntity.japScore }

<form action="gradeInsert" method="post">
<br>
<input type="text" name="memberId" placeholder="회원아이디">
<br>
<input type="text" name="name" placeholder="회원이름">
<br>
<input type="text" name="itScore" placeholder="it점수">
<br>
 <input type="text" name="japScore" placeholder="일본어점수"><br>
<input type="submit" value="성적입력"><br>
</form>


<a href="selectGrade">select 화면으로 이동</a><br>
<a href="gradeDeleteGamen">delete 화면으로 이동</a><br>
<a href="gradeDeleteGamenPractice">delete 화면으로 이동(연습)</a>

</body>
</html>