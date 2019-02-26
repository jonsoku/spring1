<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<title>비번 찾기</title>
	<link href="resources/img/favicon.ico" rel="icon" type="image/x-icon" />
	<link href="https://fonts.googleapis.com/css?family=Gamja+Flower" rel="stylesheet">
	<script src="../resources/js/jquery-3.1.1.js"></script>
	<!--이메일 인증번호 소스  -->
	  <script src="https://smtpjs.com/smtp.js"></script>
	<script>

$(document).ready(function(){

	$('#pwSendMailButton').on('click',function(){

		var memberId =$('#memberId').val();
		var email =$('#email').val();
		
			$.ajax({
				url: 'pwFindMail',
				type: 'post',	
				dataType: 'html',
				data:{memberId: memberId, email : email},			
				success: function(msg){
					
					if( msg == '아이디와 이메일이 일치하지 않습니다.' ) {
						$('#memberId').val("");
						$('#email').val("");
						var pwFindResult=document.getElementById('pwFindResult');
						pwFindResult.innerHTML='<b>'+msg+'</b>';
					} else if ( msg == 'memberId을 입력하세요.' || msg == '가입하지 않은 아이디입니다.') {
						$('#memberId').val("");
						var pwFindResult=document.getElementById('pwFindResult');
						pwFindResult.innerHTML='<b>'+msg+'</b>';
					} else if ( msg == 'email을 입력하세요.' || msg == '가입하지 않은 이메일입니다.' ) {
						$('#email').val("");
						var pwFindResult=document.getElementById('pwFindResult');
						pwFindResult.innerHTML='<b>'+msg+'</b>';
					}  else  {
											
						Email.send("marumaru34@naver.com", 			//보내는 이메일 계정
									email,							//받는 이메일 계정
									"kimschool 인증번호 입니다. ^^", 			// 제목 
									"kimschool 회원님의 비밀번호 재설정을 위한 인증번호는 " + msg + "입니다", // 내용
									"smtp.naver.com",					//네이버 smtp
									"marumaru34",						//네이버 아이디
									"marumaru2018!");
						
						$('#codeValue').val('msg');
						var pwFindResult=document.getElementById('pwFindResult');
						pwFindResult.innerHTML='<b>인증번호 발송 완료</b>';
				
					} 
				},
				error: function(e){
					alert(e+'error');
				}
			});
		
			
			$('#codeCheck').on('click',function(){
				
				var code =$('#code').val();
				var codeValue =$('#codeValue').val();
				if (codeValue == null) {
					$.ajax({
						url: 'pwCodeCheck',
						type: 'post',	
						dataType: 'html',
						data:{code: code},
						success: function(msg){
							$('#code').val("");
							var pwCodeResult=document.getElementById('pwCodeResult');
							pwCodeResult.innerHTML='<b>'+msg+'</b>';
						},
						error: function(a){
							alert('에러');		
						
						}
					});
				}else{
					var pwCodeResult=document.getElementById('pwCodeResult');
					
					
					if (code == codeValue) {
						pwCodeResult.innerHTML='<b id="emailCheckView">인증번호를 확인하였습니다.</b>';
						
					}else{
						pwCodeResult.innerHTML='<b id="emailCheckView">인증번호가 다릅니다.</b>';
					}
				}
				/* $.ajax({
					url: 'pwCodeCheck',
					type: 'post',	
					dataType: 'html',
					data:{code: code},
					success: function(msg){
						
						if(msg=='인증번호를 확인하였습니다.'){
							var pwCodeResult=document.getElementById('pwCodeResult');
							pwCodeResult.innerHTML='<b id="emailCheckView">'+msg+'</b>';
							
							$('#pwSubmitButton').click();
						
						} else {
							$('#code').val("");
							var pwCodeResult=document.getElementById('pwCodeResult');
							pwCodeResult.innerHTML='<b>'+msg+'</b>';
						}
					},
					error: function(a){
						alert('에러');		
					
					}
				}); */
			});	 
	});	 
	
});	 


//가입폼 확인
function formCheck() {
	var memberId = document.getElementById('memberId');
	var email = document.getElementById('email');

	// ID 썼는지 검사 
	if (memberId.value == '') {
		memberId.focus();
		memberId.select();
		return false;
	}

	// email 썼는지 검사 
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
	<div>
		<div>
			<h3 style="font-size: x-large;">비밀번호 찾기</h3>
			<br>
			<form id="codeModify" action="codeModify" method="post"
				onsubmit="return formCheck();">
			
				<div>
					<input type="text" id="memberId" name="memberId"
						placeholder="Enter memberId">
				</div>
				<div>
					<input type="text" id="email" placeholder="Enter Email"> <span
						id="pwFindResult" style="font-size: medium;"></span> <br>
				</div>

				<div>
					<button type="button" id="pwSendMailButton" style="width: 250px">인증번호발송</button>
				</div>

				<div>
					<br> <input type="text" id="code"
						placeholder="인증번호 입력"> <span id="pwCodeResult"
						style="font-size: medium;"></span> <br>
				</div>
				<div>
					<button type="button" id="codeCheck" style="width: 250px">인증번호 확인</button>
				</div>

				<button type="submit" id="pwSubmitButton" hidden="true"
					style="width: 250px">Check Password</button>
					<input type="hidden" id="codeValue">
			</form>
		</div>
	</div>
</body>
</html>