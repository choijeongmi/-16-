package com.KoreaIT.java.Am.controller; //컨트롤러 내의 있는 메소드들은 아티클과 멤버 둘다 겹칠때 사용





import com.KoreaIT.java.Am.dto.Member;
import com.KoreaIT.java.Am.util.Util;

public abstract class Controller {

	public static Member loginedMember; // 현재 로그인한 회원 정보

	public abstract void doAction(String cmd, String actionMethodName);

	public abstract void makeTestData();

	public static boolean isLogined() {
		return loginedMember != null;
	}

}
