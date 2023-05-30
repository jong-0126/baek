<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/views/include/navigation.jsp" %>
<link rel="stylesheet" href="/resources/style/dogMain.css" type="text/css">
</head>

<script type="text/javascript">

//페이징을 위한 메서드
function fn_list(curPage) {
   document.goodsForm.curPage.value = curPage;
   document.goodsForm.fileName.value = fileName;
   document.goodsForm.action = "/shop/dogMain";
   document.goodsForm.submit();
}

function fn_detail(productNo)
{
   document.goodsForm.productNo.value = productNo;
   document.goodsForm.action = "/shop/detail";
   document.goodsForm.submit();

}
   
</script>

<body>


<div class="content">
   <div id="option_list">
      <p class="option_sort">
         <a class="option" href="javascript:optionSelect('1');">전체</a>
         <a class="option" href="javascript:optionSelect('2');">실내</a>
         <a class="option" href="javascript:optionSelect('3');">실외</a>
      </p>
   </div>
    
<c:if test="${!empty list}">
   <c:set var="i" value="0" />
   <c:set var="j" value="5" />
   <c:forEach var="product" items="${list}" varStatus="status">
   <c:if test="${i%j == 0}"> 
   <div class="goods_list">
   </c:if>
      <div class="goods_img">
         <a class="deail_goods" href="javascript:void(0)" onclick="fn_detail(${product.productNo})">
          <img class="main_img" src="/resources/upload/${productFile.fileName}" alt="">
          <p class="product_info goods_name">${product.productName}</p>
          <p class="product_info goods_name">이미지 파일이름 : ${productFile.fileName}</p>
          <p class="product_info goods_price"><fmt:formatNumber type="number" maxFractionDigits="3" value="${product.productPrice}" />원</p>
         </a>
      </div>
<c:if test="${i%j == j-1}">
   </div>
   </c:if>
   <c:set var="i" value="${i+1}" />
   </c:forEach>
</c:if>

</div>





   <div class="paginations">
      <div class="pg_num">
      <c:if test="${!empty paging}">
         <c:if test="${paging.prevBlockPage gt 0 }">
         <span class="prev_btn page-item"><a href="javascript:void(0)" onclick="fn_list(${paging.prevBlockPage})">이전</a></span>
         </c:if>
         <c:forEach var="i" begin="${paging.startPage}" end="${paging.endPage}">
         <c:choose>
            <c:when test="${i ne curPage}">
         <span class="page-item"><a href="javascript:void(0)" onclick="fn_list(${i})">${i}</a></span>
            </c:when>
            <c:otherwise>
         <span class="page-item now-item"><a href="javascript:void(0)" style="cursor:default;">${i}</a></span>
            </c:otherwise>
         </c:choose>
         </c:forEach>
         <c:if test="${paging.nextBlockPage gt 0}">
         <span class="next_btn page-item"><a href="javascript:void(0)" onclick="fn_list(${paging.nextBlockPage})">다음</a></span>
         </c:if>
      </c:if>
      </div>
   </div>

<form name="goodsForm" id="goodsForm" method="post">
   <input type="hidden" name="productNo" value="" />
   <input type="hidden" name="productName" value="${productName}" />
   <input type="hidden" name="curPage" value="${curPage}" />
   <input type="hidden" name="fileName" value="${fileName}" />
</form>

<form name="fileForm" id="fileForm" method="post">
   
</form>
</body>
</html>