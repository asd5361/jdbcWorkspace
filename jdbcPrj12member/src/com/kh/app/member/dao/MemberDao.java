package com.kh.app.member.dao;

import java.sql.*;
import java.util.ArrayList;

import com.kh.app.db.JDBCTemplate;
import com.kh.app.member.vo.MemberVo;

public class MemberDao {

	//회원가입
	public int join(Connection conn,MemberVo vo) throws Exception {
		
		//SQL
		String sql = "INSERT INTO MEMBER(ID,PWD,NICK) VALUES(?,?,?) ";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, vo.getId());
		pstmt.setString(2, vo.getPwd());
		pstmt.setString(3, vo.getNick());
		int result = pstmt.executeUpdate();
		
		//rs
		
		//close
		JDBCTemplate.close(pstmt);
		
		return result;
	}
	
	//로그인
	public ArrayList<MemberVo> login(Connection conn,MemberVo vo) throws Exception {
		//SQL
		String sql = "SELECT * FROM MEMBER WHERE ID=? AND PWD= ?";
		//rs
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, vo.getId());
		pstmt.setString(2, vo.getPwd());
		ResultSet rs = pstmt.executeQuery();
		
		ArrayList<MemberVo> voList = new ArrayList<MemberVo>();
		MemberVo dbvo = null;
		if(rs.next()) {
			dbvo = new MemberVo();
			String dbid = rs.getString("ID");
			String dbpwd = rs.getString("PWD");
			dbvo.setId(dbid);
			dbvo.setPwd(dbpwd);
		}
		voList.add(dbvo);
		
		//close
		JDBCTemplate.close(rs);
		JDBCTemplate.close(pstmt);
		
		return null;
	}
	
	//전체 회원목록 조회
	public ArrayList<MemberVo> getMemberList(Connection conn){
		//SQL
		//rs
		//close
		
		return null;
	}
	
	//회원탈퇴
	public int quit(Connection conn,MemberVo vo) throws Exception{
		
		//SQL
		String sql = "DELETE MEMBER WHERE ID = ? AND PWD = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1,vo.getId());
		pstmt.setString(2, vo.getPwd());
		int result = pstmt.executeUpdate();
		//rs
		
		//close
		JDBCTemplate.close(pstmt);
		
		return result;
	}
	
	//비밀번호 변경
	public int editPwd(Connection conn,MemberVo vo, String newPwd) throws Exception{
		//SQL
		String sql = "UPDATE MEMBER SET PWD = ? WHERE ID = ? AND PWD = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, newPwd);
		pstmt.setString(2, vo.getId());
		pstmt.setString(3, vo.getPwd());
		
		int result = pstmt.executeUpdate();
		//rs
		
		//close
		JDBCTemplate.close(pstmt);
		return result;
	}
	
}
