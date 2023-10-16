package com.kh.mini.member.service;

import java.sql.*;

import com.kh.jdbc.JDBCTemplate;
import com.kh.mini.member.dao.MemberDao;
import com.kh.mini.member.vo.MemberVo;

public class MemberService {

	private final MemberDao dao;
	
	public MemberService() {
		dao = new MemberDao();
	}

	//회원가입
	public int join(MemberVo vo) throws Exception {
		
		//conn
		Connection conn = JDBCTemplate.getConnection();
		//dao
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
	public MemberVo login(MemberVo vo) throws Exception{
		//conn
		Connection conn = JDBCTemplate.getConnection();
		
		//dao
		MemberVo dbvo = dao.login(conn, vo);
		
		//close
		JDBCTemplate.close(conn);
		
		return dbvo;
	}

	//회원탈퇴
	public int quit(MemberVo vo) throws Exception{
		//conn
		Connection conn = JDBCTemplate.getConnection();
		//dao
		int result = dao.quit(conn, vo);
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

	//비밀번호 수정
	public int pwdEdite(MemberVo vo, String newPwd) throws Exception{
		//conn
		Connection conn = JDBCTemplate.getConnection();
		//dao
		int result = dao.pwdEdite(conn,vo,newPwd);
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

	//닉네임 수정
	public int nickEdit(MemberVo vo, String newNick) throws Exception{
		//conn
		Connection conn = JDBCTemplate.getConnection();
		//dao
		int result = dao.nickEdite(conn,vo,newNick);
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

	

}
