package com.kh.mini.board.controller;

import java.util.*;

import com.kh.mini.board.service.BoardService;
import com.kh.mini.board.vo.BoardVo;
import com.kh.mini.main.Main;

public class BoardController {
	
	private final BoardService service;
	
	public BoardController() {
		service = new BoardService();
	}
	public void selectMenu() {
		System.out.println("===== BOARD =====");
		System.out.println("1. 게시글작성");
		System.out.println("2. 목록조회");
		System.out.println("3. 상세조회");
//		System.out.println("4. 회원 탈퇴");
//		System.out.println("5. 비밀번호 변경");
		
		String num = Main.SC.nextLine();
		
		switch(num) {
		case "1" : write(); break;
		case "2" : boardList(); break;
		case "3" : boardDetailByNo(); break;
//		case "4" : quit();; break;
//		case "5" : pwdEdit(); break;
			default : System.out.println("잘못 입력하였습니다.");
		}
	}
	
	/*작성 (회원 전용)
	 * 
	 * 제목, 내용 입력 받아서
	 * BOARD 테이블에 INSERT
	 * INSERT INTO BOARD(NO,TITLE,CONTENT,WRITER_NO) 
	 * VALUES (시퀀스,제목,내용,현재로그인유저정보)
	 */
	public void write() {
		try {
			System.out.println("==========게시글작성==========");
			
			//로그인 여부 체크
			if(Main.loginMember == null) {
				throw new Exception("회원만 게시글 작성이 가능합니다.");
			}
			
			//데이터
			System.out.print("제목 : ");
			String title =Main.SC.nextLine();
			System.out.print("내용 : ");
			String content =Main.SC.nextLine();
			
			BoardVo vo = new BoardVo();
			vo.setTitle(title);
			vo.setContent(content);
			vo.setWriterNo(Main.loginMember.getNo());
			
			//서비스
			int result = service.write(vo);

			//결과
			if(result != 1) {
				throw new Exception();
			}
			System.out.println("작성 성공");
		}catch(Exception e) {
			System.out.println("작성 실패");
			e.printStackTrace();
		}
	}
	//삭제 (작성자, 본인만)
	
	//수정 (제목, 내용)
	
	/* 목록 조회 - 등록일 (번호, 제목, 작성자닉네임, 조회수, 작성일시)
	 * 
	 * SELECT * FROM BOARD WHERE DEL_YN = 'N' ORDER BY NO DESC
	 * 
	 */
	public void boardList() {
		try {
			//데이터
			System.out.println("===== 게시글 목록 (최신순) =====");
			//서비스
			List<BoardVo> voList = service.boardList();
			
			//결과처리
			if(voList.isEmpty()) {
				throw new Exception();
			}
			for(BoardVo vo:voList) {
				System.out.print("| "+ vo.getNo()+" | ");
				System.out.print(vo.getTitle()+" | ");
				System.out.print(vo.getWriterNick()+" | ");
				System.out.println(vo.getHit());
			}
			
		}catch(Exception e) {
			System.out.println("목록 조회 실패");
			e.printStackTrace();
		}
	}
	//목록 조회 - 조회수 (번호, 제목, 작성자닉네임, 조회수, 작성일시)
	
	//게시글 검색 - 제목
	//게시글 검색 - 작성자  닉네임
	/*
	 * 상세 조회 - 번호 (모든 칼럼)
	 * SELECT * FROM BOARD WHERE NO = ? AND DEL_YN = 'N' 
	 * 
	 */
	public void boardDetailByNo(){
		try {
			System.out.println("========== 게시글 상세 조회 (번호) ==========");
			//데이터
			System.out.print("글번호 : ");
			String num = Main.SC.nextLine();
			//서비스
			BoardVo vo = service.boardDetailByNo(num);
			
			//결과처리
			if(vo == null) {
				throw new Exception();
			}
//			System.out.println(vo);
			System.out.println("=============");
			System.out.println("글번호: "+vo.getNo());
			System.out.println("제목: "+vo.getTitle());
			System.out.println("내용: "+vo.getContent());
			System.out.println("닉네임: "+vo.getWriterNick());
			System.out.println("조회수: "+vo.getHit());
			System.out.println("가입일시: "+vo.getEnrollDate());
			System.out.println("수정일시: "+vo.getModifyDate());
			System.out.println("=============");
			
		}catch(Exception e) {
			System.out.println("상세조회실패");
			e.printStackTrace();
		}
	}
	
}
