<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html>
<head>
<title>회원탈퇴 페이지</title>
<link href="resources/img/favicon.ico" rel="icon" type="image/x-icon" />
<link href="https://fonts.googleapis.com/css?family=Gamja+Flower"
	rel="stylesheet">
<script src="../resources/js/jquery-3.1.1.js"></script>
<script>

$(document).ready(function(){

	$('#delete').on('click',function(){
		var memberId =$('#memberId').val();
		var password =$('#password').val();
	
		$.ajax({
			url: 'deleteCheck',
			type: 'post',	
			dataType: 'html',
			data:{memberId: memberId, password : password},			
			success: function(msg){
				
				if(msg == '아이디나 비밀 번호가 잘못되었습니다.'){
				
					$('#password').val("");
					var deleteResult=document.getElementById('deleteResult');
					deleteResult.innerHTML='<b>'+msg+'</b>';
				}					
				
				if(msg == '탈퇴가능'){
					var deleteResult=document.getElementById('deleteResult');
					deleteResult.innerHTML='<b></b>';

					$('#deleteComplete').submit();
					
				}
				
			},
			error: function(e){
				alert(e+'error');

			}
		});
	});	 
	
});	 



//아이디 비번 폼 검사
function formCheck() { 
	var memberId = document.getElementById('memberId');
	var password = document.getElementById('password');
	
	if (memberId.value == '') {
		memberId.focus();
		memberId.select();
		return false;
	}
	
	// 비밀번호 썼는지 검사 
	if (password.value == '') {
		password.focus();
		password.select();
		return false;
	}
	 
	 	
	 if(confirm('탈퇴하시겠습니까?')){
		return true;
	 }

return false;
}

</script>

</head>
<body>
			<div>			
				<h2>회원탈퇴</h2>
				<form id="deleteComplete" action="deleteComplete" method="post" onSubmit="return formCheck();">
				
					<div>
						<input type="text" id="memberId" name="memberId" value="${loginId }" readonly="readonly">
					</div>
					
					<div>
						<input type="password" id="password" name="password" placeholder="Enter Password">
					</div>

					<span id="deleteResult" ></span>
					<br><br>			
					<div>
					
					<button type="button" id="delete">회원탈퇴</button>
			</div>
				</form>
		</div>


</body>
</html>