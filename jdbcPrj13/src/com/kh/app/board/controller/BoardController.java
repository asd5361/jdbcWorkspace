package com.kh.app.board.controller;

import java.util.ArrayList;
import java.util.Scanner;

import com.kh.app.board.service.BoardService;
import com.kh.app.board.vo.BoardVo;

public class BoardController {
	
	private final Scanner sc;
	private final BoardService bs;
	
	public BoardController() {
		sc = new Scanner(System.in);
		bs = new BoardService();
	}

	//글 작성
	public void write() {
		System.out.println("글 작성 창입니다.");
		String num = "1"; //login 후 vo 받아올 부분 수정하기
		//데이터
		System.out.print("졔목 : ");
		String title = sc.nextLine();
		System.out.print("내용 : ");
		String content = sc.nextLine();
		BoardVo vo = new BoardVo(); 
		vo.setTitle(title);
		vo.setContent(content);
		vo.setWriter(num);
		
		//서비스
		try {
			int result = bs.write(vo);
			//결과처리
			if(result == 1) {
				System.out.println("글 작성 완료했습니다.");
			}
		}catch(Exception e) {
			System.out.println("글 작성 실패");
			e.printStackTrace();
		}
		
		
	}
	
	//글 수정
	public void update() {
		System.out.println("글 수정 창입니다.");
		
		//데이터
		System.out.print("수정하고 싶은 글의 제목을 입력해주세요 : ");
		String chinge = sc.nextLine();
		System.out.print("수정할 제목 : ");
		String title = sc.nextLine();
		System.out.print("수정할 내용 : ");
		String content = sc.nextLine();
		BoardVo vo = new BoardVo();
		vo.setTitle(title);
		vo.setContent(content);
		
		//서비스
		try {
			int result = bs.update(vo);
			//결과처리
			if(result == 1) {
				System.out.println("업데이트 완료");
			}else {
				throw new Exception();
			}
		}catch(Exception e) {
			System.out.println("수정 실패");
			e.printStackTrace();
		}
		
		
	}
	
	//글 삭제
	public void delete() {
		//데이터
		
		//서비스
		
		//결과처리
		
	}
	
	//전체 조회
	public void list() {
		System.out.println("게시판입니다.");
		//데이터
		//서비스
		try {
			ArrayList<BoardVo> boardList = new ArrayList<BoardVo>();
			boardList = bs.list();
			
			//결과처리
			if(boardList != null) {
				for(BoardVo vo : boardList) {
					System.out.println("\n========================\n");
					System.out.println("제목 : "+vo.getTitle());
					System.out.println("내용 : "+vo.getContent());
					System.out.println("작성자 : "+vo.getWriter());
					System.out.println("작성일자 : "+vo.getDate());
					}
			}else {
				throw new Exception();
			}
		}catch(Exception e) {
			System.out.println("게시판 조회 실패");
			e.printStackTrace();
		}
	}
	
	//글 찾기
	public void read() {
		System.out.println("글찾기 창입니다.");
		//데이터
		System.out.print("찾으실 글의 검색어를 입력해주세요 : ");
		String key = sc.nextLine();
		
		//서비스
		try {
			ArrayList<BoardVo> boardList = bs.read(key);
			//결과처리
			if(boardList != null) {
				for(BoardVo vo : boardList) {
					System.out.println("\n========================\n");
					System.out.println("제목 : "+vo.getTitle());
					System.out.println("내용 : "+vo.getContent());
					System.out.println("작성자 : "+vo.getWriter());
					System.out.println("작성일자 : "+vo.getDate());
					}
			}
		}catch(Exception e) {
			System.out.println("검색 실패");
			e.printStackTrace();
		}
		
		
	}
	
	
}
