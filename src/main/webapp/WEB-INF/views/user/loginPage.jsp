<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>SignIn</title>
    <%@ include file="/WEB-INF/views/include/navigation.jsp" %>
    <link rel="stylesheet" href="/resources/style/loginPage.css">
    <%@  include file="/WEB-INF/views/include/head.jsp" %>
</head>
<script type="text/javascript">
$(document).ready(function() {
	
	$("#userId").focus();
	
	$("#userId").on("keypress", function(e){
		
		if(e.which == 13)
		{	
			fn_loginCheck();
		}
		
	});
	
	$("#userPwd").on("keypress", function(e){
		
		if(e.which == 13)
		{	
			fn_loginCheck();
		}
		
	});
	
	$("#btnLogin").on("click",function(){
		
		fn_loginCheck();
	
	});
});

function fn_loginCheck(){
	
	if($.trim($("#userId").val()).length <= 0){
		alert("아이디를 입력하세요.");
		$("#userId").val("");
		$("#userId").focus();
		return;
	}
	
	if($.trim($("#userPwd").val()).length <= 0){
		alert("비밀번호를 입력하세요.");
		$("#userPwd").val("");
		$("#userPwd").focus();
		return;
	}

	$.ajax({
		type:"POST",
		url:"/user/login",
		data:{
			userId:$("#userId").val(),
			userPwd:$("#userPwd").val()
		},
		datatype:"JSON",
		beforeSend:function(xhr){
			xhr.setRequestHeader("AJAX","true");
		},
		success:function(response){
			
			if(!icia.common.isEmpty(response)){
				icia.common.log(response);
			
			var code = icia.common.objectValue(response,"code",-500);
			
			if(code == 0){
				
				location.href="/index";
			}
			else{
					if(code == -1){
						alert("비밀번호가 올바르지 않습니다.");
						$("#userPwd").focus();
					}
					else if(code == 404) {
						alert("아이디와 일치하는 사용자 정보가 없습니다.");
						$("#userId").focus();
					}
					else if(code == 400) {
						alert("파라미터 값이 올바르지 않습니다.");
						$("#userId").focus();
					}
					else {
						alert("오류가 발생하였습니다.");
						$("#userId").focus();
					}
				}
			}
			else {
				alert("오류가 발생하였습니다.");
				$("#userId").focus();
			}
		},
		error:function(xhr,status,error){
			icia.common.error(error);
		}
	});
}

</script>
<body>
<div class="container">
	<p class="login">login</p>
    <div id="text">
        <input type="text" id="userId" maxlength="9" placeholder="아이디">
    </div>
    <div id="text">
        <input type="password" id="userPwd" maxlength="9" placeholder="비밀번호">
        <a id="link" href="#">Forgot Password?</a>
    </div>
    <div>
        <button type="button" id="btnLogin">Sign In</button>
    </div>  
</div>
</body>
</html>