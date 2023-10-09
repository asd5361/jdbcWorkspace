package com.kh.app.main;

import com.kh.app.member.controller.MemberController;

public class Main {

	public static void main(String[] args) {

		System.out.println("로그인 후 게시글 남기는 프로그램");
		MemberController memberController = new MemberController();
		memberController.login();
	}

}
