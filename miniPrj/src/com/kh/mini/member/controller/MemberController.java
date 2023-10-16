package com.kh.mini.member.controller;

import java.util.Scanner;

import com.kh.mini.main.Main;
import com.kh.mini.member.service.MemberService;
import com.kh.mini.member.vo.MemberVo;


public class MemberController {
	
	private final MemberService service;
	
	public MemberController() {
		service = new MemberService();
	}
	public void selectMenu() {
		System.out.println("1. 회원가입");
		System.out.println("2. 로그인");
		System.out.println("3. 마이 페이지");
		System.out.println("4. 회원 탈퇴");
		System.out.println("5. 비밀번호 변경");
		
		String num = Main.SC.nextLine();
		
		switch(num) {
		case "1" : join(); break;
		case "2" : login(); break;
		case "3" : mypage(); break;
		case "4" : quit();; break;
		case "5" : pwdEdit(); break;
			default : System.out.println("잘못 입력하였습니다.");
		}
	}
	/*
	 * 회원가입
	 * 클라이언트로부터 아이디, 패스워드, 닉네임을 입력 받아서 DB에 저장
	 * INSERT INTO MEMBER (NO,ID,PWD,NICK) VALUES (SEQ_MEMBER.NEXTVAL,?,?,?)
	 */
	public void join() {
		try {
			//데이터 준비
			System.out.print("ID : ");
			String id =Main.SC.nextLine();
			System.out.print("PWD : ");
			String pwd =Main.SC.nextLine();
			System.out.print("NICK : ");
			String nick =Main.SC.nextLine();
			
			MemberVo vo = new MemberVo();
			vo.setId(id);
			vo.setPwd(pwd);
			vo.setNick(nick);
			
			//서비스 호출
			int result = service.join(vo);
			
			//결과 처리
			if(result != 1) {
				throw new Exception();
			}
			System.out.println("회원가입 완료");
		}catch(Exception e) {
			System.out.println("회원가입 실패");
			e.printStackTrace();
		}
	}
	
	/*
	 * 로그인
	 * 아이디, 비번 입력
	 * SELECT * FROM MEMBER WHERE ID = ? AND PWD = ? AND DEL_YN = 'N' 
	 */
	public void login() {
		try {
			//데이터
			System.out.print("ID : ");
			String id =Main.SC.nextLine();
			System.out.print("PWD : ");
			String pwd =Main.SC.nextLine();
			
			MemberVo vo = new MemberVo();
			vo.setId(id);
			vo.setPwd(pwd);
			
			//서비스
			MemberVo dbvo = service.login(vo);
			
			//결과
			if(dbvo == null) {
				System.out.println("로그인 실패");
			}
			System.out.println("로그인 성공");
			Main.loginMember = dbvo;
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	//회원탈퇴
	public void quit() {
		try {
			//데이터
			System.out.print("ID : ");
			String id =Main.SC.nextLine();
			System.out.print("PWD : ");
			String pwd =Main.SC.nextLine();
			
			MemberVo vo = new MemberVo();
			vo.setId(id);
			vo.setPwd(pwd);
			//서비스
			int result = service.quit(vo);
			
			//결과
			if(result != 1) {
				throw new Exception();
			}
			System.out.println("회원 탈퇴 성공");
		}catch(Exception e) {
			System.out.println("회원탈퇴 실패");
			e.printStackTrace();
		}
	}
	//마이페이지 (현재 로그인한 본인 정보 조회)
	public void mypage() {
		try {
			//데이터
			
			//서비스
			MemberVo dbvo = Main.loginMember;
			
			//결과
			if(dbvo.getNo() == null) {
				System.out.println("로그인부터 진행해주세요");
//				throw new Exception();
			}
			
			System.err.println("마이 페이지 조회 성공");
			System.out.println(dbvo);
			
		}catch(Exception e) {
			System.out.println("마이페이지 조회 실패");
			e.printStackTrace();
		}
	}
	//비밀번호 수정
	public void pwdEdit() {
		try {
			//데이터
			System.out.print("ID : ");
			String id =Main.SC.nextLine();
			System.out.print("PWD : ");
			String pwd =Main.SC.nextLine();
			System.out.print("NEW PWD : ");
			String newPwd =Main.SC.nextLine();
			
			MemberVo vo = new MemberVo();
			vo.setId(id);
			vo.setPwd(pwd);
			
			//서비스
			int result = service.pwdEdite(vo,newPwd);
			//결과
			if(result != 1) {
				throw new Exception();
			}
			System.out.println("비밀번호 수정 완료");
		}catch(Exception e) {
			System.out.println("비밀번호 수정 실패");
			e.printStackTrace();
		}
	}
	//닉네임 수정
	public void nickEdit() {
		try {
			//데이터
			System.out.print("ID : ");
			String id =Main.SC.nextLine();
			System.out.print("PWD : ");
			String pwd =Main.SC.nextLine();
			System.out.print("NEW NICK : ");
			String newNick =Main.SC.nextLine();
			
			MemberVo vo = new MemberVo();
			vo.setId(id);
			vo.setPwd(pwd);
			
			//서비스
			int result = service.nickEdit(vo,newNick);
			//결과
			if(result != 1) {
				throw new Exception();
			}
			System.out.println("닉네임 수정 완료");
		}catch(Exception e) {
			System.out.println("닉네임 수정 실패");
			e.printStackTrace();
		}
	}
	//전체 회원 조회 (관리자만 가능)
	//회원 조회 - 번호 (관리자만 가능)
	//회원 조회 - 아이디(관리자만 가능)
	//회원 조회 - 닉네임(관리자만 가능)
	//회원 강제탈퇴 (관리자만 가능)
}
