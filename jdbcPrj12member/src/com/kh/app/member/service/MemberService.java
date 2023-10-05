package com.kh.app.member.service;

import java.sql.*;
import java.util.*;

import com.kh.app.db.JDBCTemplate;
import com.kh.app.member.dao.MemberDao;
import com.kh.app.member.vo.MemberVo;

public class MemberService {
	
	private final MemberDao dao;
	
	public MemberService() {
		dao = new MemberDao();
	}

	//회원가입
	public int join(MemberVo vo) throws Exception {
		//conn
		Connection conn = JDBCTemplate.getConnetion();
		
		//DAO
		int result = dao.join(conn,vo);
		
		//tx
		if(result == 1) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		
		//close
		JDBCTemplate.close(conn);
		return result;
	}
	
	//로그인
	public ArrayList<MemberVo> login() {
		//conn
		//DAO
		//tx
		//close
		//conn
		//DAO
		//tx
		//close
		
		return null;
	}
	
	//전체 회원목록 조회
	public ArrayList<MemberVo> getMemberList(){
		//conn
		//DAO
		//tx
		//close
		
		return null;
	}
	
	//회원탈퇴
	public int quit(MemberVo vo) throws Exception{
		//conn
		Connection conn = JDBCTemplate.getConnetion();
		
		//DAO
		int result = dao.quit(conn,vo);
		
		//tx
		if(result == 1 ) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		//close
		JDBCTemplate.close(conn);
		
		return result;
	}
	
	//비밀번호 변경
	public int editPwd(MemberVo vo, String newPwd) {
		//conn
		//DAO
		//tx
		//close
		
		return 0;
	}
	
}
