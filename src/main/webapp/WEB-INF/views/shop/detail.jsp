<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/views/include/navigation.jsp" %>
<link rel="stylesheet" href="/resources/style/detail.css" type="text/css">
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="option_nav">
			<p class="option_sort">
				<a class="option" href="/WEB-INF/views/dog.jsp">전체</a>
				<a class="option" href="">실내</a>
				<a class="option" href="">실외</a>
			</p>
	</div>

   <div class="detail_goods_list">
      <ul class="detail_goods_img">
         <li class="imgbox">
            <img class="detail_img" src="resources/images/product/1.jpg">
         </li>
      </ul>

      <div class="detail_info">
         <p class="detail_name">하네스</p>
         <p class="detail_price">3000원</p>
         <p class="detail_size">
	         <select name="size_select" id="size_select" class="size_select">
	            <option value="" disabled selected>사이즈</option>
	            <option value="S">S</option>
	            <option value="M">M</option>
	            <option value="L">L</option>
	            <option value="XL">XL</option>
	         </select>
         </p>
   
         <div >
            <button id="decreaseBtn" onclick="decreaseQuantity()">-</button>
            <input id="orderCount" type="number" value="1" readonly>
            <button id="increaseBtn" onclick="increaseQuantity()">+</button>
         </div>
      
         <p class="detail_btn_div">
            <button type="button" class="detail_btn" id="button_buy" onclick="">구매하기</button>
            <button type="button" class="detail_btn" id="button_cart" onclick="">장바구니</button>
         </p>
         
   
 
      </div>
   </div>
   <div class="detail">
	   <div class= "detail_img">
	   	<img class="detail_img1" src="resources/images/product/2.jpg">
	   </div>
	   <div class= "detail_img">
	   	<img class="detail_img1" src="resources/images/product/3.jpg">
	   </div>
	   <div class= "detail_img">
	   	<img class="detail_img1" src="resources/images/product/4.jpg">
	   </div>
   </div>
	 
   <div class="recommend_words">추천상품</div>

   <div class="recommend_list">   
      <div class="recommend_info">
         <a class="recommend_goods" onclick="">
            <img class="recommend_img" src="" alt="">
            <span class="recommend_productName">이름</span>
            <span class="recommend_productPrice">5,000원</span>
         </a>
       </div>
       
       <div class="recommend_info">
         <a class="recommend_goods" onclick="">
            <img class="recommend_img" src="" alt="">
            <span class="recommend_productName">이름</span>
            <span class="recommend_productPrice">5,000원</span>
         </a>
       </div>
       
       <div class="recommend_info">
         <a class="recommend_goods" onclick="">
            <img class="recommend_img" src="" alt="">
            <span class="recommend_productName">이름</span>
            <span class="recommend_productPrice">5,000원</span>
         </a>
       </div>
       
       <div class="recommend_info">
         <a class="recommend_goods" onclick="">
            <img class="recommend_img" src="" alt="">
            <span class="recommend_productName">이름</span>
            <span class="recommend_productPrice">5,000원</span>
         </a>
       </div>
       
       <div class="recommend_info">
         <a class="recommend_goods" onclick="">
            <img class="recommend_img" src="" alt="">
            <span class="recommend_productName">이름</span>
            <span class="recommend_productPrice">5,000원</span>
         </a>
       </div>
    
  </div>

	
	


</body>
</html>
</body>
</html>