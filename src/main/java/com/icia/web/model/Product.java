package com.icia.web.model;

import java.io.Serializable;

public class Product implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private long productNo;
	private String categoryCode;
	private String productName;
	private String productPrice;
	private String productContent;
	private String productRegDate;
	private ProductFile productFile;	//첨부파일
	
	private long startRow;				
	private long endRow;
	
	private String searchType;			//검색타입(1:이름, 2:제목, 3:내용)
	private String searchValue;	
	
	public Product()
	{
		productNo = 0;
		categoryCode = "";
		productName = "";
		productPrice = "";
		productContent = "";
		productRegDate = "";
		productFile = null;	//hiboard의 시작 주소값만 가지고있는거
		
		
	}


	public long getProductNo() {
		return productNo;
	}

	public void setProductNo(long productNo) {
		this.productNo = productNo;
	}

	public String getCategoryCode() {
		return categoryCode;
	}

	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(String productPrice) {
		this.productPrice = productPrice;
	}

	public String getProductContent() {
		return productContent;
	}

	public void setProductContent(String productContent) {
		this.productContent = productContent;
	}

	public String getProductRegDate() {
		return productRegDate;
	}

	public void setProductRegDate(String productRegDate) {
		this.productRegDate = productRegDate;
	}

	public ProductFile getProductFile() {
		return productFile;
	}

	public void setProductFile(ProductFile productFile) {
		this.productFile = productFile;
	}

	public long getStartRow() {
		return startRow;
	}

	public void setStartRow(long startRow) {
		this.startRow = startRow;
	}

	public long getEndRow() {
		return endRow;
	}

	public void setEndRow(long endRow) {
		this.endRow = endRow;
	}

	public String getSearchType() {
		return searchType;
	}

	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}

	public String getSearchValue() {
		return searchValue;
	}

	public void setSearchValue(String searchValue) {
		this.searchValue = searchValue;
	}
	
	

	
	
}
