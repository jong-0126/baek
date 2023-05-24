package com.icia.web.model;

import java.io.Serializable;

public class ProductFile implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private long productNo;
	private long fileSeq;
	private String fileOrgName;
	private String fileName;
	private String fileExt;
	private long fileSize;
	private String categoryCode;
	private String productRegDate;
	
	
	public ProductFile() {
		
		productNo = 0;
		fileSeq = 0;
		fileOrgName = "";
		fileName = "";
		fileExt = "";
		fileSize = 0;
		categoryCode = "";
		productRegDate = "";
	}


	public long getProductNo() {
		return productNo;
	}


	public void setProductNo(long productNo) {
		this.productNo = productNo;
	}


	public long getFileSeq() {
		return fileSeq;
	}


	public void setFileSeq(long fileSeq) {
		this.fileSeq = fileSeq;
	}


	public String getFileOrgName() {
		return fileOrgName;
	}


	public void setFileOrgName(String fileOrgName) {
		this.fileOrgName = fileOrgName;
	}


	public String getFileName() {
		return fileName;
	}


	public void setFileName(String fileName) {
		this.fileName = fileName;
	}


	public String getFileExt() {
		return fileExt;
	}


	public void setFileExt(String fileExt) {
		this.fileExt = fileExt;
	}


	public long getFileSize() {
		return fileSize;
	}


	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
	}


	public String getCategoryCode() {
		return categoryCode;
	}


	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}


	public String getProductRegDate() {
		return productRegDate;
	}


	public void setProductRegDate(String productRegDate) {
		this.productRegDate = productRegDate;
	}

	
}

