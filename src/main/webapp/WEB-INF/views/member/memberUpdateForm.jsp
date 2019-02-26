<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<title>회원정보 수정</title>
	<link href="resources/img/favicon.ico" rel="icon" type="image/x-icon" />
	<link href="https://fonts.googleapis.com/css?family=Gamja+Flower" rel="stylesheet">
	<script src="../resources/js/jquery-3.1.1.js"></script>

	</head>
	
	<script>
	
	$(document).ready(function(){	
		
		$('#password').on('blur',function(){			
			var pw =$('#password').val();

			$.ajax({
				url: 'pw1UpdateCheck',
				type: 'post',	
				dataType: 'html',
				data:{ pw  : pw	 },
				success: function(msg){
					
					if(msg == '사용가능합니다.') {
						var pwCheckResult1=document.getElementById('pwCheckResult1');
						pwCheckResult1.innerHTML='<b>'+msg+'</b>';
					} else {
						$('#password').val("");
						var pwCheckResult1=document.getElementById('pwCheckResult1');
						pwCheckResult1.innerHTML='<b>'+msg+'</b>';
					}
				},
				error: function(a){
					alert('에러' + msg);						
				}
			});
		});	 
		

		$('#password2').on('blur',function(){			
			var pw =$('#password').val();
			var pw2 =$('#password2').val();

			$.ajax({
				url: 'pw2Check',
				type: 'post',	
				dataType: 'html',
				data:{ pw  : pw, 
					   pw2 : pw2 
					 },
				success: function(msg){
					
					if(msg == '사용가능합니다.') {
						var pwCheckResult2=document.getElementById('pwCheckResult2');
						pwCheckResult2.innerHTML='<b>'+msg+'</b>';						
					} else if (msg == '비밀번호를 입력하세요.'){
						$('#password').val("");
						$('#password2').val("");

						var pwCheckResult2=document.getElementById('pwCheckResult2');
						pwCheckResult2.innerHTML='<b>'+msg+'</b>';
					} else {
						$('#password').val("");
						$('#password2').val("");
						var pwCheckResult2=document.getElementById('pwCheckResult2');
						pwCheckResult2.innerHTML='<b>'+msg+'</b>';
					}
				},
				error: function(a){
					alert('에러');						
				}
			});
		});	 
		

		$('#name').on('blur',function(){			
			var name =$('#name').val();

			$.ajax({
				url: 'nameCheckUpdate',
				type: 'post',	
				dataType: 'html',
				data:{name: name},
				success: function(msg){
					
					if(msg=='사용가능합니다.'){
						var nameCheckResult=document.getElementById('nameCheckResult');
						nameCheckResult.innerHTML='<b>'+msg+'</b>';
					} else {
						$('#name').val("");
						var nameCheckResult=document.getElementById('nameCheckResult');
						nameCheckResult.innerHTML='<b>'+msg+'</b>';
					}
				},
				error: function(a){
					alert('에러');						
				}
			});
		});	 
	});

	function formCheck() {
		var password = document.getElementById('password');
		var password2 = document.getElementById('password2');
		var name = document.getElementById('name');
		
		
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
		return true;
	}

	
	
	
	</script>
	

	<body>
				<div>
					<h2>회원정보수정</h2>
					<form id="update" action="memberUpdate"  method="post" onsubmit="return formCheck();">
						
						<div>
						  <label>ID</label>&nbsp;&nbsp;
						    <div>
						      	<input type="text" id="memberId" name="memberId" maxlength="12" value="${memberEntity.memberId}" readonly="readonly" placeholder="Enter UserID">&nbsp;&nbsp;
					      	</div>
				     	</div>
						
						<div class="form-group">
					      	<label>Password</label>&nbsp;&nbsp;
					      	<div class="row">
						  		<input type="password" id="password" name="password" placeholder="Enter Password"><br>&nbsp;&nbsp;
						  		<span id="pwCheckResult1"></span>	
						  		
						  	</div><br>
						  	
					      	<label>Password Check</label>&nbsp;&nbsp;
					      	<div>
						      	<input type="password" id="password2" placeholder="Check the password"><br>&nbsp;&nbsp;
							  	<span id="pwCheckResult2"></span>	
						  	</div>
					    </div>
	
						<div>
					    	<label>Name</label>&nbsp;&nbsp;
					    	<div>
							<input type="text" id="name" name="name" value="${memberEntity.name}" placeholder="Enter Name">&nbsp;&nbsp;
							<br>
							<span id="nameCheckResult" class="checkResult"></span>
							</div>
						</div>
		
						<div class="form-group">
					    	<label>Email</label>&nbsp;&nbsp;
					    	<div class="row">
							<input type="text" id="email" name="email" value="${memberEntity.email}" readonly="readonly"  placeholder="Enter Name">&nbsp;&nbsp;
							<br>
							</div>
						</div>
		
						<div class="form-group">
							<label>전화번호</label>&nbsp;&nbsp;
							<div class="row">
								<input type="text" id="phone" name="phone" class="form-control" value="${memberEntity.phone}" placeholder="Enter PhoneNumber">&nbsp;&nbsp;
							</div>
						</div>
						
						<button type="submit" >Edit</button>
						<button type="reset"> Reset</button>

					</form>
				</div>
	
	
	</body>
</html>