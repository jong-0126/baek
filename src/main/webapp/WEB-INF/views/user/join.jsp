<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>SignIn</title>
    <%@ include file="/WEB-INF/views/include/navigation.jsp" %>
    <%@  include file="/WEB-INF/views/include/head.jsp" %>
    <link rel="stylesheet" href="/resources/style/join.css">
    <script type="text/javascript">
    
    $(document).ready(function(){
    	
    	$("#btnJoin").on("click", function(){
    		
    		//모든 공백 체크 정규식
    		var emptCheck = /\s/g;
    		//영문 대소문자, 숫자로만 이루어진 4~12자리 정규식
    		var idPwCheck = /^[a-zA-Z0-9]{4,12}$/;
    		if($.trim($("#userId").val()).length <=0) {
    			alert("사용자 아이디를 입력하세요.");
    			$("#userId").val("");
    			$("#useId").focus();
    			return;
    		}
    		
    		if(emptCheck.test($("#userId").val())) {
    			alert("사용자 아이디는 공백을 포함할 수 없습니다.");
    			$("#userId").focus();
    			return;
    		}
    		
    		if(!idPwCheck.test($("#userId").val())) {
    			alert("사용자 아이디는 4~12자의 영문 대소문자와 숫자로만 입력하세요.");
    			$("#userId").focus();
    			return;
    		}
    		
    		if($.trim($("#userPwd").val()).length <=0) {
    			alert("비밀번호를 입력하세요.");
    			$("#userPwd1").val("");
    			$("#userPwd1").focus();
    			return;
    		}
    		
    		if(emptCheck.test($("#userPwd").val())) {
    			alert("비밀번호는 공백을 포함할 수 없습니다.");
    			$("#userPwd1").focus();
    			return;
    		}
    		
    		if(!idPwCheck.test($("#userPwd").val())) {
    			alert("비밀번호는 영문 대소문자와 숫자로 4~12자리입니다.");
    			$("#userPwd1").focus();
    			return;
    		}
    	
    		if($("#userPwd1").val()!=$("#userPwd1").val()) {
    			alert("비밀번호가 일치하지 않습니다.");
    			$("#userPwd2").focus();
    			return;
    		}
    		
    		if($.trim($("#userName").val()).length <=0) {
    			alert("사용자 이름을 입력하세요");
    			$("#userName").val("");
    			$("#userName").focus();
    			return;
    		}
    		
    		if(!fn_validateEmail($("#userEmail").val())) {
    			alert("사용자 이메일 형식이 올바르지 않습니다.");
    			$("#userEmail").focus();
    			return;
    		}
    		
    		$("#userPwd").val($("#userPwd1").val());
    		//중복 아이디 체크 ajax
    		$.ajax({
    			 type:"POST",
    			  url:"/user/idCheck",
    		     data: {
    		    	 userId:$("#userId").val()
    		     },
    		     datatype:"JSON",
    		     beforeSend: function(xhr) {
    		    	 xhr.setRequestHeader("AJAX","true");
    		    	
    		     },
    		     success: function(response) {
    		    	 if(response.code == 0) {
    		    		 fn_userReg();
    		    	 }
    		    	 else if(response.code == 100) {
    		    		 alert("중복된 아이디입니다.");
    		    		 $("#userId").focus();
    		    	 }
    		    	 else if(response.code == 400) {
    		    		 alert("파라미터 값이 올바르지 않습니다.");
    		    		 $("#userId").focus();
    		    	 }
    		    	 else {
    		    		 alert("오류가 발생하였습니다.");
    		    		 $("#userId").focus();
    		    	 }
    		     },
    		     error:function(xhr,status,error) {
    		    	 icia.common.error(error);
    		     }
    		});
    		
    	});
    	
    });

    function fn_userReg()
    {
    	$.ajax({
    		type:"POST",
    		url:"/user/regProc",
    		data:{
    			userId:$("#userId").val(),
    			userPwd:$("#userPwd").val(),
    			userName:$("#userName").val(),
    			userEmail:$("#userEmail").val()
    		},
    		dataType:"JSON",
    		 beforeSend: function(xhr) {
    	    	 xhr.setRequestHeader("AJAX","true");
    			
    		},
    		success:function(response){
    			if(response.code==0) {
    				alert("회원 가입이 되었습니다.");
    				location.href="/index";
    			}
    			else if(response.code==100){
    				alert("회원 아이디가 중복 되었습니다.");
    				$("#userId").focus();
    			}
    			else if(response.code == 400) {
    				alert("파라미터 값이 올바르지 않습니다.");
    				$("#userId").focus();
    			}
    			else if(response.code == 500) {
    				alert("회원 가입 중 오류가 발생하였습니다.");
    				$("#userId").focus();
    			}
    			else {
    				alert("회원 가입 중 오류 발생");
    				$("#userId").focus();
    			}
    		},
    		error:function(xhr,status,error) {
    			icia.common.error(error);
    		}
    	});
    }

    function fn_validateEmail(value)
    {
    	var emailReg = /^([\w-\.]+@([\w-]+\.)+[\w-]{2,4})?$/;
    	
    	return emailReg.test(value);
    }
    
    
    </script>
</head>
<body>
<div class="container">
	<p class="login">회원가입</p>
    <div id="text">
        <input type="text" id="userId" maxlength="9" placeholder="아이디">
    </div>
    <div id="text">
        <input type="Password" id="userPwd" maxlength="9" placeholder="비밀번호">
    </div>
    <div id="text">
        <input type="Password" id="userPwd1" maxlength="9" placeholder="비밀번호 확인">
    </div>
    <div id="text">
        <input type="text" id="userName" maxlength="9" placeholder="이름">
    </div>
    <div id="text">
        <input type="text" id="userEmail" maxlength="20" placeholder="이메일">
    </div>
    
    <div>
        <button type="button" id="btnJoin">가입하기</button>
    </div>
</div>
</body>
</html>