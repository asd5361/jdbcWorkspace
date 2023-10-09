package com.kh.app.member.controller;

import java.sql.*;
import java.util.Scanner;

import com.kh.app.member.service.MemberService;
import com.kh.app.member.vo.MemberVo;

public class MemberController {

	private final Scanner sc;
	private final MemberService ms;
	
	public MemberController() {
		sc = new Scanner(System.in);
		ms = new MemberService();
	}
	
	public void join() {
		//데이터 준비
		System.out.println("\n==============================\n");
		System.out.println("회원가입 창입니다.값을 입력해주세요");
		System.out.print("ID : ");
		String id = sc.nextLine();
		System.out.print("PWD : ");
		String pwd = sc.nextLine();
		System.out.print("NICK : ");
		String nick = sc.nextLine();
		
		MemberVo vo = new MemberVo(); //객체 담기
		vo.setId(id);
		vo.setPwd(pwd);
		vo.setNick(nick);
		
		try {
			//서비스
			int result =ms.join(vo);
			//결과
			if(result == 1) {
				System.out.println("회원가입 완료");
				login();
			}else {
				throw new Exception();
			}
		}catch(Exception e) {
			System.out.println("회원가입 실패");
			e.printStackTrace();
		}
	}
	
	public void login() {
		//데이터 준비
		System.out.println("\n==============================\n");
		System.out.println("로그인 창입니다. ID와 PWD를 입력해주세요");
		System.out.print("ID : ");
		String id = sc.nextLine();
		System.out.print("PWD : ");
		String pwd = sc.nextLine();
		
		MemberVo vo = new MemberVo(); //객체 담기
		vo.setId(id);
		vo.setPwd(pwd);
		
		//서비스
		MemberVo dbVo;
		try {
			
			dbVo = ms.login(vo);
			
			//결과
			if(dbVo != null) {
				System.out.println(dbVo.getNick()+"님 환영합니다.");
			}else {
				throw new Exception();
			}
			
		}catch(Exception e) {
			System.out.println("로그인 실패");
			e.printStackTrace();
		}
	}

}
