<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<title>회원가입</title>
	<link href="resources/img/favicon.ico" rel="icon" type="image/x-icon" />
	<link href="https://fonts.googleapis.com/css?family=Gamja+Flower" rel="stylesheet">
	<script src="../resources/js/jquery-3.1.1.js"></script>
	<!--이메일 인증번호 소스  -->
	  <script src="https://smtpjs.com/smtp.js"></script>
	<script>
	var managerCode = '';
	$(document).ready(function(){
	
		/* 회원정보아이디 중복검사  */
		/* blur : 입력 후, 발동하게 하는 속성 */
		$('#memberId').on('blur',function(){
			/* val : value값 */
			var memberId =$('#memberId').val();
			
			$.ajax({
				url: 'idCheck',
				type: 'post',
				dataType: 'html',
				/* data : controller단으로 보내는 데이터 */
				data: {searchId: memberId},
				/* success : 전달이 성공했을시 설정역할 */
				success: function(msg) {
					
					if(msg == 'This value can use right now.') {
						var idCheckResult = document.getElementById('idCheckResult');
						/* innerHTML : idCheckResult에 해당하는 곳에 html태그를 삽입해주는 속성  */
						idCheckResult.innerHTML = '<p>'+ msg + '</p>';
						
					} else {
						/* value값을 초기화 */
						$('#memberId').val('');
						var idCheckResult = document.getElementById('idCheckResult');
						/* innerHTML : idCheckResult에 해당하는 곳에 html태그를 삽입해주는 속성  */
						idCheckResult.innerHTML = '<p>'+ msg + '</p>';
					}
				/*  */	
				},
				error: function(e) {
					alert('아이디 체크 에러')
				}
			});
		});
	
		/* 회원 비밀번호 중복 검사 */
		$('#password').on('blur',function(){
			/* val : value값 */
			var password =$('#password').val();
			
			$.ajax({
				url: 'pwCheck1',
				type: 'post',
				dataType: 'html',
				/* data : controller단으로 보내는 데이터 */
				/* pw - controller단상의 파라메티 (key) : password - 화면상의 value값(value)*/
				data: {pw: password},
				/* success : 전달이 성공했을시 설정역할 */
				success: function(msg) {
					
					if(msg == 'This value can use right now.') {
						var pwCheckResult1 = document.getElementById('pwCheckResult1');
						/* innerHTML : pwCheckResult1에 해당하는 곳에 html태그를 삽입해주는 속성  */
						pwCheckResult1.innerHTML = '<p>'+ msg + '</p>';
						
					} else {
						/* value값을 초기화 */
						$('#password').val('');
						var pwCheckResult1 = document.getElementById('pwCheckResult1');
						/* innerHTML : nameCheckResult에 해당하는 곳에 html태그를 삽입해주는 속성  */
						pwCheckResult1.innerHTML = '<p>'+ msg + '</p>';
					}
				/*  */	
				},
				error: function(e) {
					alert('비밀번호 체크 에러');
				}
			});
		});
		
		/* 회원 다시 입력한 비밀번호 중복 검사 */
		$('#password2').on('blur',function(){
			/* val : value값 */
			var password =$('#password').val();
			var password2 =$('#password2').val();
			
			$.ajax({
				url: 'pwCheck2',
				type: 'post',
				dataType: 'html',
				/* data : controller단으로 보내는 데이터 */
				/* pw - controller단상의 파라메티 (key) : password - 화면상의 value값(value)*/
				data: {pw: password, pw2: password2},
				/* success : 전달이 성공했을시 설정역할 */
				success: function(msg) {
					
					if(msg == 'This value can use right now.') {
						var pwCheckResult2 = document.getElementById('pwCheckResult2');
						/* innerHTML : pwCheckResult1에 해당하는 곳에 html태그를 삽입해주는 속성  */
						pwCheckResult2.innerHTML = '<p>'+ msg + '</p>';
						
					} else if (msg == 'please enter the password.'){
						$('#password').val("");
						var pwCheckResult2=document.getElementById('pwCheckResult2');
						pwCheckResult2.innerHTML='<p>'+msg+'</p>';
					} else {
						$('#password').val("");
						$('#password2').val("");
						var pwCheckResult2=document.getElementById('pwCheckResult2');
						pwCheckResult2.innerHTML='<p>'+msg+'</p>';
					}
				
				},
				error: function(e) {
					alert('비밀번호1,2 체크 에러');
				}
			});
		});
		
		
		/* 회원 닉네임 중복 검사 */
		$('#name').on('blur',function(){
			/* val : value값 */
			var name =$('#name').val();
			
			$.ajax({
				url: 'nameCheck',
				type: 'post',
				dataType: 'html',
				/* data : controller단으로 보내는 데이터 */
				/* serchName - controller단상의 파라메티 (key) : name - 화면상의 value값(value)*/
				data: {searchName: name},
				/* success : 전달이 성공했을시 설정역할 */
				success: function(msg) {
					
					if(msg == 'This value can use right now.') {
						var nameCheckResult = document.getElementById('nameCheckResult');
						/* innerHTML : idCheckResult에 해당하는 곳에 html태그를 삽입해주는 속성  */
						nameCheckResult.innerHTML = '<p>'+ msg + '</p>';
						
					} else {
						/* value값을 초기화 */
						$('#name').val('');
						var nameCheckResult = document.getElementById('nameCheckResult');
						/* innerHTML : nameCheckResult에 해당하는 곳에 html태그를 삽입해주는 속성  */
						nameCheckResult.innerHTML = '<p>'+ msg + '</p>';
					}
			
				},
				error: function(e) {
					alert('아이디 체크 에러')
				}
			});
		});
		
		$('#joinSendMail').on('click',function(){
			var email =$('#email').val();
		
			$.ajax({
				url: 'emailSend',
				type: 'post',	
				dataType: 'html',
				data:{email: email},
				success: function(msg){
					
					if(msg=='Please enter the email.' || msg=='This is not in email format.' || msg=='This email already exists.'){
						$('#email').val("");
						var emailSendResult=document.getElementById('emailSendResult');
						emailSendResult.innerHTML='<p>'+msg+'</p>';
					} else {
						
					
						managerCode = msg;						
												
						Email.send("marumaru34@naver.com", 			//보내는 이메일 계정
									email,								//받는 이메일 계정
									"Kimschool 인증번호 입니다. ^^", 		// 제목 
									"Kimschool 회원 가입을 위한 인증번호는 " + managerCode + "입니다", // 내용
									"smtp.naver.com",					//네이버 smtp
									"marumaru34",						//네이버 아이디
									"marumaru2018!");						//네이버 비번
		
						var emailSendResult=document.getElementById('emailSendResult');
						emailSendResult.innerHTML='<p>You have sent the authentication number by email.</p>';		
						$('#joinSendMail').val('1');
					}
				
				},
				error: function(a){
					alert('에러');		
				
				}
			});
		});	 
		
		$('#code').on('blur',function(){
		
			var code =$('#code').val();
			
			$.ajax({
				url: 'emailCheck',
				type: 'post',	
				dataType: 'html',
				data:{code: code},
				success: function(msg){
					
					if(msg=='Authentication number checked.'){
						var emailCheckResult=document.getElementById('emailCheckResult');
						emailCheckResult.innerHTML='<p id="emailCheckView">'+msg+'</p>';
					} else {
						$('#code').val("");
						var emailCheckResult=document.getElementById('emailCheckResult');
						emailCheckResult.innerHTML='<p>'+msg+'</p>';
					}
				},
				error: function(a){
					alert('에러');		
				
				}
			});
		});	 
		
	});	 
	
	
	
	function formCheck() {
		var memberId = document.getElementById('memberId');
		var password = document.getElementById('password');
		var password2 = document.getElementById('password2');
		var name = document.getElementById('name');
		var email = document.getElementById('email');
		var phone = document.getElementById('phone');
		var code = document.getElementById('code');
		var joinSendMail = document.getElementById('joinSendMail'); 
		var emailSendResult=document.getElementById('emailSendResult');
		
		// ID 썼는지 검사 
		if (memberId.value == '') {
			custid.focus();
			custid.select();
			return false;
		}
		
		// 비밀번호 썼는지 검사 
		if (password.value == '') {
			password.focus();
			password.select();
			return false;
		}
		

		// 비밀번호 확인 했는지  검사 
		if (password2.value == '') {
			password2.focus();
			password2.select();
			return false;
		}

		
		// 닉네임 썼는지 검사 
		if (name.value == '') {
			name.focus();
			name.select();
			return false;
		}
		
		
		// email 썼는지 검사 
		if (email.value == '') {
			email.focus();
			email.select();
			return false;
		}
		
		//email 전송했는지 여부확인
		if (joinSendMail.value != '1') {
			joinSendMail.focus();
			emailSendResult.innerHTML='<p>Please press the authentication number button.</p>';
			return false;
	    }
		
		// 인증번호 썼는지 검사 
		if (code.value == '') {
		
			code.focus();
			code.select();
			return false;
		}
		
		// 인증번호 썼는지 검사 
		if (code.value != managerCode) {
		
			code.focus();
			code.select();
			return false;
		}

		

		return true;
	}
	</script>
</head>
<style type="text/css">
body {
	font-family: 'Gamja Flower', cursive;
}
</style>
<body>
	<div>
		<h2>회원가입</h2>
		<div>
			<!-- onsubmit : form태그를 controller단으로 보내기 전에 다시한번 확인 -->
			<form action="memberJoin" id="joinForm" method="post" onsubmit="return formCheck();">
				<div>
					<label>아이디</label>&nbsp;&nbsp;
					<div>
						<input type="text" id="memberId" name="memberId" placeholder="아이디를 입력해주세요."></input>&nbsp;&nbsp;
						<span id="idCheckResult"></span>
					</div>
				</div>
				<div>
					<label>비밀번호</label>&nbsp;&nbsp;
					<div>
						<input type="password" id="password" name="password" placeholder="패스워드를 입력해주세요."></input>&nbsp;&nbsp;
						<span id="pwCheckResult1"></span>
					</div>
				</div>
				<div>
					<label>비밀번호확인</label>&nbsp;&nbsp;
					<div>
						<input type="password" id="password2" placeholder="패스워드를 다시한번 입력해주세요."></input>&nbsp;&nbsp;
						<span id="pwCheckResult2"></span>
					</div>
				</div>
				<div>
					<label>이름</label>&nbsp;&nbsp;
					<div>
						<input type="text" id="fullName" name="fullName" placeholder="이름을 입력해주세요."></input>&nbsp;&nbsp;
					</div>
				</div>
				<div>
					<label>닉네임</label>&nbsp;&nbsp;
					<div>
						<input type="text" id="name" name="name" placeholder="닉네임을 입력해주세요."></input>&nbsp;&nbsp;
						<span id="nameCheckResult"></span>
					</div>
				</div>
				<div>
					<label>이메일주소</label>&nbsp;&nbsp;
					<div>
						<input type="text" id="email" name="email" placeholder="이메일주소를 입력해주세요."></input>&nbsp;&nbsp;
						<button type="button" id="joinSendMail" >인증번호 전송</button>
						<span id="emailSendResult"></span>
					</div>
				</div>
				<div>
					<label>이메일 인증번호</label>&nbsp;&nbsp;
					<div>
						<input type="text" id="code" name="code" placeholder="인증번호를 입력해주세요."></input>&nbsp;&nbsp;
						<br>						
						<span id="emailCheckResult"></span>
					</div>
				</div>
				<div>
					<label>전화번호</label>&nbsp;&nbsp;
					<div>
						<input type="text" id="phone" name="phone" placeholder="전화번호를 입력해주세요."></input>&nbsp;&nbsp;
					</div>
				</div>
				<div>
					<div>
						<button type="submit">회원가입</button>&nbsp;&nbsp;&nbsp;
						<button type="reset">다시 작성</button>
					</div>
				</div>
			</form>
		</div>
	</div>

	
</body>
</html>