<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<title>회원정보 수정</title>
	<link href="resources/img/favicon.ico" rel="icon" type="image/x-icon" />
	<link href="https://fonts.googleapis.com/css?family=Gamja+Flower" rel="stylesheet">
	<script src="../resources/js/jquery-3.1.1.js"></script>

	<script>


$(document).ready(function(){

	$('#gologin').on('click',function(){

		var memberId =$('#memberId').val();
		var password =$('#password').val();

	
		$.ajax({
			url: 'updatePWCheck',
			type: 'post',	
			dataType: 'html',
			data:{searchId: memberId, searchPW : password},			
			success: function(msg){
				
				if(msg == '인증완료') {
					var loginResult=document.getElementById('loginResult');
					loginResult.innerHTML='<b></b>';
					
					$('#updateForm').submit();
					
				} else {
					$('#password').val("");
					var loginResult=document.getElementById('loginResult');
					loginResult.innerHTML='<b>'+msg+'</b>';
			
				}
				
			},
			error: function(e){
				alert(e+'error');

			}
		});
	});	 
	
});	 


</script>

</head>
<body>	
			<div>			
				<h2>회원정보수정</h2>
				
				<form id="updateForm" action="updateForm" method="post">
					<div>
						<input type="text" id="memberId" name="memberId" value="${memberEntity.memberId}" readonly="readonly" placeholder="Enter UserID">
					</div>					
					<div>
						<input type="password" id="password" name="password" placeholder="Enter Password">
					</div>
					<span id="loginResult" ></span><br>
					<br>					
					<button type="button" id="gologin">확인</button>
		
				</form>
				
			</div>
	

</body>
</html>