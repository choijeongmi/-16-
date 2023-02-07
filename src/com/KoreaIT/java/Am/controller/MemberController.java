package com.KoreaIT.java.Am.controller;

import java.util.List;
import java.util.Scanner;

import com.KoreaIT.java.Am.dto.Member;
import com.KoreaIT.java.Am.util.Util;

public class MemberController extends Controller {
	private List<Member> members;
	private Scanner sc;
	private String cmd;
	private String actionMethodName;
	
	public MemberController(List<Member> members, Scanner sc){
		this.members = members;
		this.sc =sc;
	}
	
	public void doAction(String cmd, String actionMethodName) {
		this.cmd = cmd;
		this.actionMethodName = actionMethodName;
		
		switch(actionMethodName) {
		case "join":
			doJoin();
			break;
		}
	}

	public void doJoin() {
		int id = members.size() + 1;

		String regDate = Util.getNowDateStr();

		String loginId = null;
		while (true) {
			System.out.printf("ID : ");
			loginId = sc.nextLine();
			if (isJoinableLoginId(loginId) == false) {
				System.out.printf("%s(은)는 사용중인 아이디입니다.\n", loginId);
				continue;
			}
			break;
		} // 입력한 id가 가입이 가능한지

		String loginPw = null;
		String loginPwCheck = null;

		while (true) {
			System.out.printf("PW : ");
			loginPw = sc.nextLine();
			System.out.printf("PWCheck : ");
			loginPwCheck = sc.nextLine();

			if (loginPw.equals(loginPwCheck) == false) {
				System.out.println("비밀번호가 일치하지 않습니다.");
				continue;
			}
			break;
		}

		System.out.printf("이름 : ");
		String name = sc.nextLine();

		Member member = new Member(id, regDate, loginId, loginPw, name);
		members.add(member);

		System.out.printf("%d번 회원이 가입했습니다.\n", id);

	}
	
	private boolean isJoinableLoginId(String loginId) {
		int index= getMemberIndexByLoginId(loginId);
		
		if(index == -1) {
			return true;
		}
		return false;
	}

	private int getMemberIndexByLoginId(String loginId) {
		int i = 0;
		for(Member member : members) {
			if(member.loginId.equals(loginId)) {
				return i;
			}
			i++;
		}
		return -1;
	}
		
	}

