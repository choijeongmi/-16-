package com.KoreaIT.java.Am.dto;

public class Article {
//	Data Transfor Object(DTO) or value Objectv(VO)
	public int id;
	public String regDate;
	public String title;
	public String body;
	public int viewCnt; // 조회수

	public Article(int id, String regDate, String title, String body) {
		this(id, regDate, title, body, 0);
	}
	public Article(int id, String regDate, String title, String body, int viewCnt) {
		this.id = id;
		this.title = title;
		this.body = body;
		this.regDate = regDate;
		this.viewCnt = viewCnt;
		
	}

	public void increaseViewCnt() {
		viewCnt++;
	}
}
