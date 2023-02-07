package com.KoreaIT.java.Am.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.KoreaIT.java.Am.dto.Article;

import com.KoreaIT.java.Am.util.Util;

public class ArticleController extends Controller {

	private List<Article> articles;
	private Scanner sc;
	private String cmd;
	private String actionMethodName;

	public void doAction(String cmd, String actionMethodName) {
		this.cmd = cmd;
		this.actionMethodName = actionMethodName;
		
		switch(actionMethodName) {
		case "list":
			showList();
			break;
		case "detail":
			showDetail();
			break;	
		case "write":
			doWrite();
			break;	
		case "delete":
			doDelete();
			break;	
		case "modify":
			doModify();
			break;	
		}

	}

	public ArticleController(List<Article> articles, Scanner sc) {
		this.articles = articles;
		this.sc = sc;
	}

	public void doWrite() {
		int id = articles.size() + 1;

		String regDate = Util.getNowDateStr();
		System.out.printf("제목 : ");
		String title = sc.nextLine();
		System.out.printf("내용 : ");
		String body = sc.nextLine();

		Article article = new Article(id, regDate, title, body);
		articles.add(article);

		System.out.printf("%d번글이 생성되었습니다.\n", id);

	}

	public void showList() { // cmd가 필요한 이유 : 입력받아야하기때문에 substring을 사용하기에.

		if (articles.size() == 0) { // arraylist가 비어있는 경우
			System.out.println("게시글이 없습니다.");
			return;// 함수를 끝내겠다.

		}
		String searchKeyword = cmd.substring("article list".length()).trim();

		System.out.printf("검색어 : %s\n", searchKeyword);

		List<Article> forPrintArticles = articles;

		if (searchKeyword.length() > 0) {
			forPrintArticles = new ArrayList<>();
			for (Article article : articles) {
				if (article.title.contains(searchKeyword)) {
					forPrintArticles.add(article);
				}
			}
			if (articles.size() == 0) {
				System.out.println("검색결과가 없습니다.");
				return;
			}
		}

		System.out.println("번호  |  제목  |  조회");
		for (int i = forPrintArticles.size() - 1; i >= 0; i--) {
			Article article = forPrintArticles.get(i);
			System.out.printf("%2d  |  %2s  |  %2d\n", article.id, article.title, article.viewCnt);
		}

	}

	public void showDetail() {
		String[] cmdBits = cmd.split(" "); // 공백을 기준으로 분할할래 라는 뜻 "공백"
//  	  =>명령문이 3개의 분할이기 때문에 [] 배열로 묶어준다.
		int id = Integer.parseInt(cmdBits[2]);

		Article foundArticle = getArticleById(id);

		/*
		 * for (int i = 0; i < articles.size(); i++) { Article article =
		 * articles.get(i); // => 0부터 size 전까지 탐색하겠다 라는 뜻. i = 0 if (article.id == id) {
		 * // found = true; > found 가 참일 경우 브레이크. 로 넣어줄수도 있음 foundArticle = article;
		 * break; // 가장 가까운 반복문을 탈출하는 거. 탐색 중 정답을 찾았을때 탐색을 중지시키기위해 } } // 위의 for문에서 탐색을
		 * 마친 후 정답을 찾으면 break를 통해 for문을 빠져나와 아래의 if문으로 이동.
		 */
		if (foundArticle == null) { // 찾은 게시물(foundArticle)이 없을때
			System.out.printf("%d번 게시물은 존재하지 않습니다.\n", id);
			return;
		} // 그 외의 값이면 아래가 출력 >> 외의값은 found 값이 트루일 경우 if가 아닌 else로 이동.

		foundArticle.increaseViewCnt();

		System.out.printf("번호 : %d\n", foundArticle.id);
		System.out.printf("날짜 :%s\n", foundArticle.regDate);
		System.out.printf("제목 : %s\n", foundArticle.title);
		System.out.printf("내용 : %s\n", foundArticle.body);
		System.out.printf("조회 : %d\n", foundArticle.viewCnt);

	}

	public void doModify() {
		String[] cmdBits = cmd.split(" ");

		int id = Integer.parseInt(cmdBits[2]);

		Article foundArticle = getArticleById(id);

		if (foundArticle == null) {
			System.out.printf("%d번 게시물은 존재하지 않습니다.\n", id);
			return;
		}
		System.out.printf("제목 : ");
		String title = sc.nextLine(); // 내용을 받겠다. 입력할 수 있게 도와주는 코드.
		System.out.printf("내용 : ");
		String body = sc.nextLine();

		foundArticle.title = title;
		foundArticle.body = body;

		System.out.printf("%d번 게시물이 수정 되었습니다.\n", id);

	}

	public void doDelete() {
		String[] cmdBits = cmd.split(" ");

		int id = Integer.parseInt(cmdBits[2]);

		int foundIndex = getArticleIndexById(id);

		/*
		 * for (int i = 0; i < articles.size(); i++) { Article article =
		 * articles.get(i);
		 * 
		 * if (article.id == id) {
		 * 
		 * foundIndex = i; break; } }
		 */
		if (foundIndex == -1) {
			System.out.printf("%d번 게시물은 존재하지 않습니다.\n", id);
			return;
		}
		articles.remove(foundIndex); // 또는 (id -1) 리무브id값은 3 어레이리스트 값은 2이기 때문에 -1을 해준다 방번호는 0부터 시작이기 때문에~~

		System.out.printf("%d번 게시물이 삭제 되었습니다.\n", id);

	}

	private int getArticleIndexById(int id) {
		int i = 0;
		for (Article article : articles) {
			if (article.id == id) {
				return i;
			}
			i++;
		}

		return -1;
	}

	private Article getArticleById(int id) {
		/*
		 * 과정1 for (int i = 0; i < articles.size(); i++) { Article article =
		 * articles.get(i);
		 * 
		 * if (article.id == id) { Article foundArticle = article;
		 * 
		 * break; } }
		 */

		/*
		 * 과정2 for(Article article : articles) { if(article.id == id) { return article;
		 * } }
		 */

		int index = getArticleIndexById(id);
		if (index != -1) {
			return articles.get(index);
		}

		return null;
	}
}
