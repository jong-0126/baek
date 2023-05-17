package com.icia.web.model;

import java.io.Serializable;

public class Qna implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private long qnaSeq;
	private String qnaTitle;
	private String qnaContent;
	private int qnaReadCnt;
	private String regDate;
	
	private String userId;
	private String userName;
	private String userPwd;

	
	public Qna() {
		qnaSeq=0;
		userId="";
		qnaTitle="";
		qnaContent="";
		qnaReadCnt=0;
		regDate="";
		userName="";
		userPwd="";
	}

	
	public long getQnaSeq() {
		return qnaSeq;
	}

	public void setQnaSeq(long qnaSeq) {
		this.qnaSeq = qnaSeq;
	}

	public String getQnaTitle() {
		return qnaTitle;
	}

	public void setQnaTitle(String qnaTitle) {
		this.qnaTitle = qnaTitle;
	}

	public String getQnaContent() {
		return qnaContent;
	}

	public void setQnaContent(String qnaContent) {
		this.qnaContent = qnaContent;
	}

	public int getQnaReadCnt() {
		return qnaReadCnt;
	}

	public void setQnaReadCnt(int qnaReadCnt) {
		this.qnaReadCnt = qnaReadCnt;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPwd() {
		return userPwd;
	}

	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}

}
