package com.KoreaIT.java.Am;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.KoreaIT.java.Am.controller.ArticleController;
import com.KoreaIT.java.Am.controller.Controller;
import com.KoreaIT.java.Am.controller.MemberController;
import com.KoreaIT.java.Am.dto.Article;
import com.KoreaIT.java.Am.dto.Member;
import com.KoreaIT.java.Am.util.Util;

public class App {
	private List<Article> articles; // 접근지정자(private = 외부접근x , 상속x ) 외 protected(외부접근x , 상속간에는 접근 가능), public(모두접근
	private List<Member> members;
	// 가능)

	public App() { // router or 라우터 라고 함. 길안내.
		articles = new ArrayList<>();
		members = new ArrayList<>();

	}

	public void start() {
		System.out.println("==프로그램 시작==");

		makeTestData();

		Scanner sc = new Scanner(System.in);

		MemberController memberController = new MemberController(members, sc);
		ArticleController articleController = new ArticleController(articles, sc);

		// 무한루프
		while (true) {
			System.out.printf("명령어 )");
			String cmd = sc.nextLine().trim(); // trim이란 입력문의 양끝 공백을 없애주는?

			if (cmd.length() == 0) {
				System.out.println("명령어를 입력하세요.");
				continue;
			}

			if (cmd.equals("끝")) {
				break;// 브레이크=멈추다.
			}
			String[] cmdBits = cmd.split(" ");// 공백을 기준으로 나눈다 ex) article detail

			if (cmdBits.length == 1) {
				System.out.println("존재하지 않는 명령어입니다.");
				continue;
			}

			String controllerName = cmdBits[0]; // article
		    String actionMethodName = cmdBits[1];//  detail
		

			Controller controller = null;

			if (controllerName.equals("article")) {
				controller = articleController;
			} else if (controllerName.equals("member")) {
				controller = memberController;
			} else {
				System.out.println("존재하지 않는 명령어입니다.");
				continue;
			}

			controller.doAction(cmd, actionMethodName);

			/*
			 * ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ ARTICLE JOIN (회원가입)
			 * ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
			 * 
			 * // if (cmd.equals("member join")) { // memberController.doJoin(); // } //
			 * ////ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ ARTICLE WRITE (작성)
			 * ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ // else if
			 * (cmd.equals("article write")) { // articleController.doWrite(); // // } //
			 * ////ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ ARTICLE LIST (목록)
			 * ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ // // else if
			 * (cmd.startsWith("article list")) { // articleController.showList(cmd); // }
			 * // ////ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ ARTICLE DETAIL (상세)
			 * ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ // //// startsWith > ~ 로
			 * 시작한다면. / split (쪼갠다, 분할) // else if (cmd.startsWith("article detail ")) { //
			 * articleController.showDetail(cmd); // // } //
			 * ////ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ ARTICLE DELETE (삭제)
			 * ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ // //// delete의 핵심은 index // else if
			 * (cmd.startsWith("article delete ")) { // articleController.showDelete(cmd);
			 * // // // } // ////ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ ARTICLE MODIFY (수정)
			 * ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ // // else if
			 * (cmd.startsWith("article modify ")) { // articleController.showModify(cmd);
			 * // // // }
			 */
//
		}
		sc.close();
		System.out.println("===프로그램 종료===");
	}

	private void makeTestData() {
		System.out.println("테스트를 위한 데이터를생성합니다.");
		articles.add(new Article(1, Util.getNowDateStr(), "title 1", "body 1", 11));
		articles.add(new Article(2, Util.getNowDateStr(), "title 2", "body 2", 22));
		articles.add(new Article(3, Util.getNowDateStr(), "title 3", "body 3", 33));

	}

}
