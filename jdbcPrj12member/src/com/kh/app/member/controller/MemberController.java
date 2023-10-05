package com.kh.app.member.controller;

import java.util.ArrayList;
import java.util.Scanner;

import com.kh.app.member.service.MemberService;
import com.kh.app.member.vo.MemberVo;

public class MemberController {
	
	//필드 == 멤버변수
	private final Scanner sc;
	private final MemberService ms;
	//기본 생성자
	public MemberController() {
		sc = new Scanner(System.in);
		ms = new MemberService();
	}

	//메뉴선택
	public void MemberMenu() {
	}
	
	//회원가입
	public void join() {
		System.out.println("회원가입===");
		
		//데이터
		System.out.print("ID : ");
		String id = sc.nextLine();
		System.out.print("PWD : ");
		String pwd = sc.nextLine();
		System.out.print("NICK : ");
		String nick = sc.nextLine();
		
		MemberVo vo = new MemberVo();
		vo.setId(id);
		vo.setPwd(pwd);
		vo.setNick(nick);
		
		try {
			//서비스
			int result = ms.join(vo);
			
			//결과
			if(result == 1) {
				System.out.println("회원가입 성공");
			}else {
				throw new Exception();
			}
		}catch(Exception e) {
			System.out.println("회원가입 실패");
			e.printStackTrace();
		}
	
	}
	
	//로그인
	public void login() {
		System.out.println("로그인===");
		//데이터
		System.out.print("ID : ");
		String id = sc.nextLine();
		System.out.print("PWD : ");
		String pwd = sc.nextLine();
		
		MemberVo vo = new MemberVo();
		vo.setId(id);
		vo.setPwd(pwd);
		
		try {
			
			//서비스
			ArrayList<MemberVo> voList = ms.login(vo);
			//결과

			if(voList.get(1) != null) {
				System.out.println("님 환영합니다. 로그인 성공");				
			}else {
				throw new Exception();
			}
			
		}catch(Exception e) {
			System.out.println("로그인 실패");
			e.printStackTrace();
		}
		
	}
	
	//전체 회원몰고 조회
	public void printMemberList() {
		//데이터
		
		//서비스
		
		//결과
		
	}
	
	//회원탈퇴
	public void quit() {
		System.out.println("회원탈퇴===");
		
		//데이터
		System.out.print("ID : ");
		String id = sc.nextLine();
		System.out.print("PWD : ");
		String pwd = sc.nextLine();
		
		MemberVo vo = new MemberVo();
		vo.setId(id);
		vo.setPwd(pwd);
		
		try {
			//서비스
			int result = ms.quit(vo);
			
			//결과
			if(result == 1) {
				System.out.println("회원탈퇴 성공");
			}else {
				throw new Exception();
			}
			
		}catch(Exception e) {
			System.out.println("회원 탈퇴 실패");
			e.printStackTrace();
		}
	}
	
	//비밀번호 변경
	public void editPwd() {
		System.out.println("패스워드 변경===");
		
		//데이터
		System.out.print("ID : ");
		String id = sc.nextLine();
		System.out.print("PWD : ");
		String pwd = sc.nextLine();
		System.out.print("NEW PWD : ");
		String newPwd = sc.nextLine();
		
		MemberVo vo = new MemberVo();
		vo.setId(id);
		vo.setPwd(pwd);
		vo.setNick(newPwd);
		try {
			//서비스
			int result = ms.editPwd(vo, newPwd);
			//결과
			if(result == 1) {
				System.out.println("비밀번호 변경 성공");
			}else {
				throw new Exception();
			}
		}catch(Exception e) {
			System.out.println("변경 실패");
			e.printStackTrace();
		}
		
	}
	
	
}
