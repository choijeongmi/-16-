package com.KoreaIT.java.Am.dao;

import java.util.ArrayList;
import java.util.List;

import com.KoreaIT.java.Am.dto.Article;
import com.KoreaIT.java.Am.dto.Member;

public class ArticleDao extends Dao {
	public List<Article> articles;
	
	
	public ArticleDao() {
		articles = new ArrayList<>();
	}
	
	public void add(Article article) {
		articles.add(article);
		lastId++;
	}
	
	

}
