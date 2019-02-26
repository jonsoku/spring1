<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!doctype html>
<html>
<head>
	<title>공지사항 게시글</title>
	<link href="resources/img/favicon.ico" rel="icon" type="image/x-icon" />
	<link href="https://fonts.googleapis.com/css?family=Gamja+Flower" rel="stylesheet">
	<style>
		*{margin:0; padding:0;}
		
		/* 게시판  */
		.board_read{width:700px; background-color:fff; padding:10px; border-radius: 20px;}	
		.board_read .read_header>h2{margin-bottom:10px;}
		.board_read .read_header .read_header_info{border-bottom:1.5px solid black;padding-bottom:15px; margin-top:15px;}
		.board_read .read_header .meta:before{content:'작성자  : '; margint-right:10px; font-weight: bold;}
		.board_read .read_header .meta{; margin-right:11px;}
		.board_read .read_header .time:before{content:'작성일  : '; margint-right:10px; font-weight: bold}
		.board_read .read_header .time{; margin-right:11px;}
		.board_read .read_header .read:before{content:'조회수  : '; margint-right:10px; font-weight: bold}
		.board_read .read_header .read{; margin-right:11px;}
		
		/* 본 문 */
		#fbtext{width:700px; height:500px;margin-top:15px;}
		.board-btn-div{text-align:center;}
		.board-btn-div .board-btn{display:inline-block; text-decoration:none;color:black;padding: 10px 20px; margin-bottom:30px; ;  background-color: #f6f6f5; text-align: center;margin:0 auto; border-radius: 20px;}
		
		#commentDiv{}
		#commentDiv .fbHeader {}
		#commentDiv .fbHeader h4 {display:inline-block; letter-spacing:3px; margin-top:30px;	}
		
		.comment-div{display:inline-block; background-color:#f6f6f5;margin-bottom:10px; width:700px; padding:10px; border-radius: 20px;}
		#commentDiv .comment-author{margin-top:20px; font-size:11px; color:grey;}
		#commentDiv .comment-author:before{content:'작성자  ::  '}
		#commentDiv .comment-date{margin-left:40px; font-size:11px; color:grey;} 
		#commentDiv .comment-date:before{content:'작성일 :: '}
		#commentDiv .comment-desc{width:100%; margin-top:10px; font-size:17px; font-weight: bold;}

		.replybutton{display:inline-block;}
		
		.comment-modify{text-align:right; width:700px; margin-bottom:30px;}
		.comment-modify p{display:inline-block;}
		.comment-modify p a{text-decoration:none; color: black; margin-left:10px;}
		
		.comment-input{width:720px; margin-top:30px; position:relative;}
		.comment-input textarea{display:block;width:700px; height:100px;padding:10px; border-radius: 20px;}
		.comment-input button{cursor:pointer;width:80px; display:inline-block ;border:0; height:21px ;position:absolute; right:1%; bottom:-30px; border-radius: 20px;}
	</style>
	<script src="../resources/js/jquery-3.1.1.js"></script>
	<script>
		$(document).ready(function(){
			loginId = '${loginId}';
			init();
			//리플 쓰기 폼 체크 및 전송
		});

		function deleteCheck(boardNum) {
			
			if(confirm('삭제하시겠습니까?')) {
				
				return true;
			}
			
			return false;
		}

		function insertReply(){
			var boardNum =$('#boardNum').val();
			var retext =$('#retext').val();

			if (retext.length < 5) {
				alert('리플 내용을 5자 이상 입력하세요.');
				retext.focus();
				retext.select();
				return false;
			}
				
			$.ajax({
				url: 'noticeReplyWrite',
				type: 'post',	
				dataType: 'html',
				data:{boardNum: boardNum, commentContent: retext},
				success: function(msg){
				
					$('#retext').val("");
					init(); 
				},
				error: function(e){
					alert('리플을 1000자 이내로 입력하세요');
				}
			});
		}

		 function init(){
		 	
			var boardNum =$('#boardNum').val();
			//var memberId =$('#mId').val();
			
			$.ajax({	
				url: 'noticeList',
				type: 'post' ,
				dataType: 'json',
				data:{boardNum: boardNum},
				success: function(z){
					
					str = '';
					if(loginId != ''){	
						str +='<div class="comment-input"><textarea name="text" id="retext"></textarea>';
						str +='<button id="insertReply">댓글 작성</button></div>';
					}	
					
		 	 		str +='<div class="fbHeader" ><h4>Comments</h4></div>';
					$.each(z,function(index,item){
						str +='<div class="comment comment-div"><span class="comment-author">'
			if(item.depth>0){
			 		for(var i=0;i<item.depth;i++){
						str +='&nbsp';
					}
			 		str +='ㄴ';			
				} 
				
				str +=item.memberId+'</span>';
				str +='<span class="comment-date">'+item.commentDate+'</span>';
				if (item.delFlg == '0') {									
					str +='<p class="comment-desc">'+item.commentContent+'</p></div>';
				}else{
					if (item.depth == '0') {						
						str +='<p>작성자에 의해 삭제된 댓글 입니다</p>'
					}else{
						str +='<p>작성자에 의해 삭제된 답글 입니다</p>'
					}
				}
				
		
				if(item.memberId!=loginId&&loginId!=''){
					str +=	'<p class="replybutton" width="60px">[<a class="reinsert" href="#" commentNum="'+item.commentNum+'" boardNum="'+item.boardNum+'" indexnum="'+index+'">답글</a>]</p>';
					}
				if(item.memberId==loginId&&loginId!=''){
					if (item.delFlg == '0') {						
					str+='<div class="comment-modify"><p plass="replybutton" width="60px"><a class="edit" href="#" commentNum="'+item.commentNum+'" boardNum="'+item.boardNum+'" retext="'+item.commentContent+'"indexnum="'+index+'" >수정</a></p>';				
					str+='<p class="replybutton" width="60px"><a class="delete" href="#" commentNum="'+item.commentNum+'" boardNum="'+item.boardNum+'">삭제</a></p></div>';			
					}
				}
				str +='</li></ul>'; 
				str +='<p class="white" colspan="4"><div class="divForm'+index+'" height="50px"></div></p>';
					});
				
				
				
				$('#commentDiv').html(str);
				$('.edit').on('click',replyEditForm);
				$('.reinsert').on('click',reReplyForm);
				$('#insertReply').on('click',insertReply);
				$('.delete').on('click', function(){
				 var commentNum=$(this).attr('commentNum');
				if( confirm('삭제 하시겠습니까?')){
					
				}else{
					return false;
				}
				 
					$.ajax({
					url: 'noticeCommentDelete',
					type: 'get',
					data:{commentNum: commentNum , boardNum: boardNum},
					
					success: function(num){
						if(num=='1'){
						
						init();
						}
						
					},
					error: function(a){
						alert('error');
					}
				 });
				});
							
				}, 
			
			error:function(a){
				alert('error');
			}
			
		});

	} 

		//리플 수정
		function replyEditForm() {
			
			var commentNum=$(this).attr('commentNum'); 
			var retext=$(this).attr('retext'); 
			var index =$(this).attr('indexnum');
			//해당 리플번호를 붙여 생성한 <div>태그에 접근
			
			var str ='<input type="hidden" id="commentNum" value="'+commentNum+'">';
			str += '<input type="text" id="text" value="' + retext + '" style="width:530px;height:30px;">';
			str += '<a href="#" id="updateComment">[저장]</a>';
			str += '<a href="#" id="updateCancle">[취소]</a>';
			$('.divForm'+index).html(str);
			$('#updateComment').on('click',updateReply)
			$('#updateCancle').on('click',function(){
				$('.divForm'+index).html(""); 
			})
		}
		function updateReply(){
			var commentNum =$('#commentNum').val();
			var text =$('#text').val();
		   
			if (text.length < 5) {
				alert('리플 내용을 입력하세요.');
				text.focus();
				text.select();
				return false;
			}
			
			$.ajax({
				url: 'noticeCommentEdit',
				type: 'post',
				data:{commentNum: commentNum , commentContent: text},
				
				success: function(num){
					if(num=='1'){
					
					init();
					}
					
				},
				error: function(){
					alert('error');
				}
			 });
		}

		//리플의 리플
		function reReplyForm() {
			
			var commentNum=$(this).attr('commentNum'); 
		 	var boardNum=$(this).attr('boardNum'); 
			var index =$(this).attr('indexnum');
			//해당 리플번호를 붙여 생성한 <div>태그에 접근
			
			var str ='<input type="hidden" id="commentNum" value="'+commentNum+'">';
		 	str += '<input type="hidden" name="boardNum" value="'+boardNum+'">'; 
			str += '<input type="text" id="text" value="" style="width:530px;height:30px;">';
			str += '<a href="#" id="reReplyInsert">[저장]</a>';
			str += '<a href="#" id="reReplyCancle">[취소]</a>';
			$('.divForm'+index).html(str);
			$('#reReplyInsert').on('click',reReply)
			$('#reReplyCancle').on('click',function(){
				$('.divForm'+index).html(""); 
			})
		}

		function reReply(){
			var commentNum =$('#commentNum').val();
			var boardNum =$('#boardNum').val();
			var text =$('#text').val();
		 
			if (text.length < 5) {
				alert('리플 내용을 입력하세요.');
				text.focus();
				text.select();
				return false;
			}
			
			$.ajax({
				url: 'noticeCommentReInsert',
				type: 'post',
				data:{commentNum: commentNum ,boardNum: boardNum, commentContent: text},
				
				success: function(num){
					if(num=='1'){
					
					init();
					}
					
				},
				error: function(){
					alert('error');
				}
			 });
		}

		
		</script>
</head>

<body>

	<div class="content-wrapper">
		<div class="board">
		
			<!--자바스크립트에 값 불러오기위해 hidden으로 넣음  -->
			<input type="hidden" id="mId" value="${MemberEntity.memberId }">
			<input type="hidden" name="boardNum" id="boardNum"
				value="${noticeBoard.boardNum}" />



			
			<br>

			<div class="board_read">
				<div class="read_header">
					<!-- 글제목 -->
					<h2>
						${noticeBoard.title }
					</h2>
					<div class="read_header_info">
						<span class="meta">
							${noticeBoard.memberId}
						</span>
						
						<span class="time">
							${noticeBoard.regDate }
						</span>
	
						<span class="read">
							${noticeBoard.boardCount }
						</span>
					</div>	

				</div>
				<div class="read_body">
					<div class="xe_content">
						<div id="fbtext">${noticeBoard.content }</div>
					</div>
					<div class="board-btn-div">
						<a href="noticeBoardList" class="board-btn">목록으로</a>
						<c:if test="${noticeBoard.memberId == loginId}">
							<a href="noticeUpdate?boardNum=${noticeBoard.boardNum }" class="board-btn">수정하기</a>
							<a href="noticeDelete?boardNum=${noticeBoard.boardNum }" class="board-btn">
								<button class="btn" style="border:0;background:#f6f6f5; font-size:11px;;" onclick="return deleteCheck(${noticeBoard.boardNum });">
								삭제하기
								</button>
							</a>
						</c:if>
					</div>

				</div>
			</div>
			
			
			
			<div class="feedback" id="comment">
				<div id="commentDiv"></div>
			</div>
		</div>
	</div>
</body>
</html>
