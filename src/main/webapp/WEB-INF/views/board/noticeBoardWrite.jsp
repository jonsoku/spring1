<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
	<link href="resources/img/favicon.ico" rel="icon" type="image/x-icon" />
	<link href="https://fonts.googleapis.com/css?family=Gamja+Flower" rel="stylesheet">
	<title>공지사항 글쓰기</title>
</head>
<style type="text/css">
body {
	font-family: 'Gamja Flower', cursive;
}
</style>
<script src="../resources/js/jquery-3.1.1.js"></script>
<script>
	$(document).ready(function() {
		
	    $('#title').keyup(function(event) {
	     var title = document.noticeWriteForm.title.value;
	     $("#titleResult").val(title.length);
	   
	    if(title.length > 30) { 
	        $("#titleResult").html(title.length+"/30글자 입니다." + " 작성하실 수 없습니다."); 
	        
	    } else {
	    	
	    $("#titleResult").html(title.length+"/30글자 입니다.");
	    }
	    
	    });
	
	    $('#content').keyup(function(event) {
	     var content = document.noticeWriteForm.content.value;
	     $("#contentResult").val(content.length);
	    
	    if(content.length > 1000) { 
	        $("#contentResult").html(content.length+"/3000글자 입니다." + " 작성하실 수 없습니다."); 
	        
	    } else {
	    	
	    $("#contentResult").html(content.length+"/1000글자 입니다.");
	    }
	   
	    });
	
		$('#noticeWrite').on('click',function(){

			var title =$('#title').val();
			var content =$('#content').val();

			$.ajax({
				url: 'boardWriteCheck',
				type: 'post',	
				dataType: 'html',
				data:{title: title, content : content},			
				success: function(msg){
					
					if(msg == 'success'){
						
						$('#noticeWriteForm').submit();
						
					} else if (msg == '제목을 10자 이상 입력하세요.' || msg == '제목을 입력하세요.' || msg =='내용을 3000자 이내로 입력하세요.') {
						alert(msg);
						$('#title').focus();
						$('#title').select();
					} else {
						alert(msg);
						$('#content').focus();
						$('#content').select();
					}
				},
				error: function(e){
					alert(e+'error');
				}
			});
		});	 
		
	});	 
	
	</script>
<body>
<h2>글쓰기</h2>
<form id="noticeWriteForm" name="noticeWriteForm" action="noticeBoardWrite" method="post">
	<input type="hidden" id="boardNum" name="boardNum" value=""></input>
	<h2><input type="text" id="title" name="title" placeholder="제목을 입력해주세요."></input></h2>
	<span id="titleResult">0/30글자 입니다.</span>
	<br>
	<br>
	<textarea id="content" name="content" style="height: 200px; width: 500px; resize: none;"></textarea>
	<br><br>
	<span id="contentResult">0/1000글자 입니다.</span>
	<br>
	<div align="center">
		<input type="button" id="noticeWrite" value="저장"></input>
		<input type="reset" value="다시작성"></input>
	</div>
</form>
</body>
</html>