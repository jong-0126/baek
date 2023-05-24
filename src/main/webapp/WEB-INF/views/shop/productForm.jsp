<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/views/include/head.jsp" %>
<script type="text/javascript">
$(document).ready(function() {
    
	$("#productName").focus();
	
	$("#btnWrite").on("click", function() {
		
		$("#btnWrite").prop("disabled",true); //저장 버튼 비활성화
		
		if($.trim($("#productName").val()).length <=0) {
			
			alert("상품명을 입력하세요.");
			$("#productName").val("");
			$("#productName").focus();
			
			$("#btnWrite").prop("diasbled",false); //저장 버튼 활성화 
			
			return;
		}
		
		if($.trim($("#productPrice").val()).length <=0) {
			
			alert("상품명을 입력하세요.");
			$("#productPrice").val("");
			$("#productPrice").focus();
			
			$("#btnWrite").prop("diasbled",false); //저장 버튼 활성화 
			
			return;
		}
		
		var form = $("#productForm")[0];
		var formData = new FormData(form);
		
		$.ajax({
	    	type:"POST",
	    	enctype:"multipart/form-data",
			url:"/shop/productProc",
			data:formData,
			processData:false,			//formData를 stringㅇ로 변환하지 않음
			contentType:false,			//content-type 헤더가 multipart/form-data로 전송
			cache:false,
			beforeSend:function(xhr) {
				xhr.setRequestHeader("AJAX","true");
			},
			success:function(response) {
				if(response.code==0) {
					alert("상품이 등록되었습니다.");
					location.href="/index";
				}
				else if(response.code==400) {
					alert("파라미터 값이 올바르지 않습니다.");
					$("#btnWrite").prop("disabled",false);
				}
				else {
					alert("상품 등록 중 오류가 발생하였습니다.");
					$("#btnWrite").prop("disabled",false);
				}
			},
			error:function(error) {
				icia.common.error(error);
				alert("오류가 발생하였습니다.");
				$("#btnWrite").prop("disabled",false); //저장 버튼 활성화
				
				
			}
		});
	});
	
	$("#btnList").on("click", function() {

	});
});
</script>
</head>
<body>
<%@ include file="/WEB-INF/views/include/navigation.jsp" %>
<div class="container">
	<h2>상품 등록</h2>
	<form name="productForm" id="productForm" method="post" enctype="multipart/form-data">
	  <!-- select name="category" >
	    <option value="none">=== 선택 ===</option>
	    <option value="dog">강아지</option>
	    <option value="cat">고양이</option>
	  </select-->
		<input type="text" name="productName" id="productName" maxlength="20" style="ime-mode:active;" class="form-control mt-4 mb-2" placeholder="상품명" required />
		<input type="text" name="productPrice" id="productPrice" maxlength="30" style="ime-mode:inactive;" class="form-control mb-2" placeholder="상품가격" required />
		<div class="form-group">
			<textarea class="form-control" rows="10" name="productContent" id="productContent" style="ime-mode:active;" placeholder="상세 내용을 입력해주세요" required></textarea>
		</div>
		<input type="file" id="productFile" name="productFile" class="form-control mb-2" placeholder="파일을 선택하세요." required />
		<div class="form-group row">
			<div class="col-sm-12">
				<button type="button" id="btnWrite" class="btn btn-primary" title="저장">저장</button>
				<button type="button" id="btnList" class="btn btn-secondary" title="리스트">목록</button>
			</div>
		</div>
	</form>
	<form name="bbsForm" id="bbsForm" method="post">
		<input type="hidden" name="searchType" value="" />
		<input type="hidden" name="searchValue" value="" />
		<input type="hidden" name="curPage" value="" />
	</form>
</div>
</body>
</html>