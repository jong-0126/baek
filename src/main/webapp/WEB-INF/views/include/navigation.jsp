<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<link rel="stylesheet" href="/resources/style/navigation.css">


<%
	if(com.icia.web.util.CookieUtil.getCookie(request, (String)request.getAttribute("AUTH_COOKIE_NAME")) != null)
	{
%>

<div id="skipNav">
    <a href="#">JiHoon's Webpage</a>
</div>
<hr class="hidden" />

<div id="wrap">
    <div id="header">
        <h1 id="logo"><img src="" width="200" height="39" alt="Jihoon LOGO" /></h1>
        <h2 class="noVisible">메인메뉴</h2>
        <ul id="menu">
            <li class="current"><a href="jh_main.html">HOME</a></li>
            <li><a href="/shop/dogMain">강아지</a></li>
            <li><a href="#">고양이</a></li>
            <li><a href="/qna/list">고객센터</a></li>
            <li><a href="#" style="font-size: 15px">장바구니 </a></li>
            <li><a href="#" style="font-size: 15px;">마이페이지</a></li>
			<li><a href="/user/loginOut" style="font-size: 15px;">로그아웃</a></li>
            
        </ul>
    </div>
    <hr class="hidden" />
</div>

<%
	}
	else
	{
%>

<div id="skipNav">
    <a href="#">JiHoon's Webpage</a>
</div>
<hr class="hidden" />

<div id="wrap">
    <div id="header">
        <h1 id="logo"><img src="" width="200" height="39" alt="Jihoon LOGO" /></h1>
        <h2 class="noVisible">메인메뉴</h2>
        <ul id="menu">
            <li class="current"><a href="/index">HOME</a></li>
            <li><a href="/shop/dogMain">강아지</a></li>
            <li><a href="#">고양이</a></li>
            <li><a href="/qna/list">고객센터</a></li>
            <li><a href="/user/loginPage" style="font-size: 15px">로그인</a></li>
            <li><a href="/user/join" style="font-size: 15px;">회원가입</a></li>
        </ul>
    </div>
    <hr class="hidden" />
</div>
<%
	}
%>

   
   
   