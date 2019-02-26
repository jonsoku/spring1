<%@ page language="java" contentType="text/html; charset=UTF8"
    pageEncoding="UTF8"%>
<!DOCTYPE html>
<html>
<head>
	<title>아이디 찾기</title>
	<link href="resources/img/favicon.ico" rel="icon" type="image/xicon" />
	<link href="https://fonts.googleapis.com/css?family=Gamja+Flower" rel="stylesheet">
	<script src="../resources/js/jquery-3.1.1.js"></script>
	<!--이메일 인증번호 소스    -->
	  <script src="https://smtpjs.com/smtp.js"></script>
	<script>
 
$(document).ready(function(){

	$('#sendLoginId').on('click',function(){

		var email =$('#email').val();
	
		$.ajax({
			url: 'idFindMail',
			type: 'post',	
			dataType: 'html',
			data:{email: email},			
			success: function(msg){
				
				if( msg == '이메일을 입력하세요.' || 
					msg == '이메일 형식이 아닙니다.' || 
					msg == '이메일로 가입한 이력이 없습니다.' ) {
					$('#email').val("");
					var idFindResult=document.getElementById('idFindResult');
					idFindResult.innerHTML='<b>'+msg+'</b>';
					
				}  else  {
					
					var idFindResult=document.getElementById('idFindResult');
					idFindResult.innerHTML='<b>로그인페이지로 이동합니다.</b>';
									
				
					Email.send("marumaru34@naver.com", 	
							email,							
							"kimshchool 아이디입니다. ^^", 		
							"kimschool id는 " + msg + "입니다.",	
							"smtp.naver.com",					
							"marumaru34",						
							"marumaru2018!");
					
					
					setTimeout(function(){
						
						// 메일 보내는 시간 필요함 				
						$('#tologin').submit();
						
					}, 1000);
					
				} 
			},
			error: function(e){
				alert(e+'error');
			}
		});
	});	 
	
});	 

//email 비번 폼 검사
function formCheck() { 
	var email = document.getElementById('email');
	
	if (email.value == '') {
		email.focus();
		email.select();
		return false;
	}

return true;
}

</script>

</head>

<body>

			<div >
				
				<h3 style="color: black; marginbottom: 25px; textalign: center; fontsize: 1.5em; margintop: 5px;">>Search ID</h3>
				<form action="memberLogin" id="tologin"  method="GET" onSubmit="return formCheck();" >
					<div class="formgroup">
						<input type="text" id="email" placeholder="Enter Email">
					</div>
					<span id="idFindResult"></span><br>
					<br>
					<button type="button" id="sendLoginId"  style="width: 250px">이메일로 아이디 발송</button>
				</form>
			</div>

</body>
</html>
