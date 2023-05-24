<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/views/include/navigation.jsp" %>
<link rel="stylesheet" href="/resources/style/dogMain.css" type="text/css">
</head>

<script type="text/javascript">

$(document).ready(function() {

	
</script>

<body>

<div class="content">
    
	<div class="option_nav">
		<p class="option_sort">
			<a class="option" href="">전체</a>
			<a class="option" href="">실내</a>
			<a class="option" href="">실외</a>
		</p>
	</div>
    

	<div class="goods_list">
	
	<c:if test="${!empty list}">
		<c:forEach var="product" items="${list}" varStatus="status">
			<div class="goods_img">
				<a class="deail_goods" href="/shop/detail">
				    <img class="main_img" src="resources/images/product/1.jpg">
			    	<p class="product_info goods_price">${product.productPrice}</p>
			    	<p class="product_info goods_name">${product.productName}</p>
				</a>
			</div>
		</c:forEach>
	</c:if>
			

	</div>
	
	<form name="productForm" id="productForm" method="post">
			
		<input type="hidden" name="productNo" id="productNo" value="${product.productNo}" />
		<input type="hidden" name="productName" id="productName" value="${product.productName}" />
		<input type="hidden" name="productPrice" id="productPrice" value="${product.productPrice}" />
		<input type="hidden" name="productContent" id="productContent" value="${product.productContent}" />
	</form>	
</div>






</body>
</html>