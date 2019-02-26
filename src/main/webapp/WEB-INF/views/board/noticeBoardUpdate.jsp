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
	<title>공지게시판 수정</title>
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
	     var title = document.noticeUpdateform.title.value;
	     $("#titleresult").val(title.length);
	   
	    if(title.length > 85) { 
	        $("#titleresult").html(title.length+"/85글자 입니다. + 작성하실 수 없습니다. "); 
	        
	    } else {
	    	
	    $("#titleresult").html(title.length+"/85글자 입니다.");
	    }
	    
	    });


	
	    $('#text').keyup(function(event) {
	     var text = document.noticeUpdateform.text.value;
	     $("#textresult").val(text.length);
	   
	    
	    if(text.length > 1000) { 
	        $("#textresult").html(text.length+"/1000글자 입니다. + 작성하실 수 없습니다. "); 
	        
	    } else {
	    	
	    $("#textresult").html(text.length+"/1000글자 입니다.");
	    }
	    
	    });
	
	
		$('#noticeUpdate').on('click',function(){
	
			var title =$('#title').val();
			var text =$('#text').val();
	
			$.ajax({
				url: 'boardWriteCheck',
				type: 'post',	
				dataType: 'html',
				data:{title: title, content : text},			
				success: function(msg){
					
					if(msg == 'success'){
						
						$('#noticeUpdateform').submit();
		
					} else if (msg == 'Please enter the title over 5 letters.' || msg == 'Please enter the title.' || msg =='Please enter the content within 3000 letters.') {
						alert(msg);
						$('#title').focus();
						$('#title').select();
						
					} else {
						alert(msg);
						$('#text').focus();
						$('#text').select();
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
<h2>NoticeBoardUpdate</h2>

		<div class="content-wrapper">
			<div class="board">
				<form class="col-12" id="noticeUpdateform" name="noticeUpdateform" action="noticeBoardUpdate"  method="post" >
					<input type="hidden" id="boardNum" name="boardNum" value="${noticeBoard.boardNum } ">
					<div class="board_read">
						<div class="form-group">
							<div class="read_header">
								<h2><a><input type="text" name="title" id="title" placeholder="제목" value="${noticeBoard.title }" class="formtitle" style="width: 100%;"></a>
								</h2>
							</div>
				
						</div>

						<p class="time"><a id="titleresult">0/85글자 입니다.</a></p>
						<div class="read_body">
							<div class="xe_content">
								<div class="form-group">
									<div class="col-sm-15">
										<textarea class="form-control" name="content" id="text" style="height: 200px; resize: none;">${noticeBoard.content }</textarea>
									</div><br>
									<p id="textresult">0/1000글자 입니다.</p>
								</div>
							</div>
						</div>
					</div>
					<div align="center">
				
						<button type="button" id="noticeUpdate" class="btn"><i class="fas fa-sign-in-alt"></i> 저장</button>
						<button type="reset" class="btn"><i class="fas fa-trash-alt"></i> 다시 작성</button>
					</div>
				</form>
			</div>
		</div>

</body>
</html>