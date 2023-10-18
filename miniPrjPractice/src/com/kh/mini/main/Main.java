package com.kh.mini.main;

import java.util.Scanner;

import com.kh.mini.member.controller.MemberController;
import com.kh.mini.member.vo.MemberVo;


public class Main {

	public static Scanner SC = new Scanner(System.in);
	public static MemberVo loginMember;
	
	public Main() {
	}

	public static void main(String[] args) {
		MemberController memberController = new MemberController();
		
		System.out.println("미니프로젝트 연습_01");
		
		memberController.join();
	}

}
