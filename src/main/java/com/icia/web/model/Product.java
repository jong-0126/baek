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
	
	

	public ProductFile getProductFile() {
		return productFile;
	}



	public void setProductFile(ProductFile productFile) {
		this.productFile = productFile;
	}



	public String getProductContent() {
		return productContent;
	}



	public void setProductContent(String productContent) {
		this.productContent = productContent;
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

	public String getProductRegDate() {
		return productRegDate;
	}

	public void setProductRegDate(String productRegDate) {
		this.productRegDate = productRegDate;
	}
	
}
