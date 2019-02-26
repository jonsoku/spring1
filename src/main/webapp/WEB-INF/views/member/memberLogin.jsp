<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html>
<head>
<title>로그인페이지</title>
<link href="resources/img/favicon.ico" rel="icon" type="image/x-icon" />
<link href="https://fonts.googleapis.com/css?family=Gamja+Flower"
	rel="stylesheet">
<script src="../resources/js/jquery-3.1.1.js"></script>
<script>


$(document).ready(function(){
	

		
	$('#loginButton').on('click',login);
	 
	
	
	$('#password').keypress(function(){
		 var key =event.keyCode;
				if (key ==13) {
					login();
					
					
					
		}
	});//keypress function
	

	
	
});	 


function login(){

/* 	alert('login'); */
	var memberId =$('#memberId').val();
	var password =$('#password').val();


	$.ajax({
		url: 'loginCheck',
		type: 'post',	
		dataType: 'html',
		data:{searchId: memberId, searchPW : password},			
		success: function(msg){
			
			if(msg == '아이디나 비밀 번호가 잘못되었습니다.'){
				$('#memberId').val("");
				$('#password').val("");
				var loginResult=document.getElementById('loginResult');
				loginResult.innerHTML='<p>'+msg+'</p>';
			}					
			
			if(msg != '아이디나 비밀 번호가 잘못되었습니다.'){
				var loginResult=document.getElementById('loginResult');
				loginResult.innerHTML='<p></p>';
				
				$('#loginForm').submit();
				
			}
			
		},
		error: function(e){
			alert(e+'error');

		}
	});
}

</script>


</head>

<style type="text/css">
body {
	font-family: 'Gamja Flower', cursive;
}
</style>
<body>
	
	<div class="login">
	<h2>로그인</h2>
		  <form id="loginForm" action="memberLogin" method="post">
		    <div>
			  <input type="text" id="memberId" name="memberId" placeholder="Enter UserID">
			</div>
		    <div>
			  <input type="password" id="password" name="password" placeholder="Enter Password">
			</div>
		    <div>
		    <span id="loginResult"></span><br><br>
		    <br>
			    <button type="button" id="loginButton" >Sign in</button>
		    </div>
	 	 	</form>
			 <div align="center" >
				<a href="memberIdfind" >Forgot Id?</a><br>
			  	<a href="memberPwFind">Forgot password?</a>
			 </div>
  		</div>

</body>
</html>