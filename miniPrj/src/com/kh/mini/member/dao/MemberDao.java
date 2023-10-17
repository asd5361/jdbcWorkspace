package com.kh.mini.member.dao;

import java.sql.*;

import com.kh.jdbc.JDBCTemplate;
import com.kh.mini.member.vo.MemberVo;

public class MemberDao {

	//회원가입
	public int join(Connection conn, MemberVo vo) throws Exception {

		//sql
		String sql = "INSERT INTO MEMBER (NO,ID,PWD,NICK) VALUES (SEQ_MEMBER.NEXTVAL,?,?,?)";
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
	public MemberVo login(Connection conn, MemberVo vo) throws Exception{

		//sql
		String sql = "SELECT * FROM MEMBER WHERE ID = ? AND PWD = ? AND DEL_YN = 'N'";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, vo.getId());
		pstmt.setString(2, vo.getPwd());
		ResultSet rs = pstmt.executeQuery();
		
		MemberVo dbvo = null;
		//rs -> vo
		if(rs.next()) {
			dbvo = new MemberVo();
			dbvo.setNo(rs.getString("NO"));
			dbvo.setId(rs.getString("ID"));
			dbvo.setPwd(rs.getString("PWD"));
			dbvo.setNick(rs.getString("NICK"));
			dbvo.setEnrollDate(rs.getString("ENROLL_DATE"));
			dbvo.setModifyDate(rs.getString("MODIFY_DATE"));
			dbvo.setDelYn(rs.getString("DEL_YN"));
		}

		//close
		JDBCTemplate.close(rs);
		JDBCTemplate.close(pstmt);
		
		return dbvo;
	}

	//회원탈퇴
	public int quit(Connection conn, String no) throws Exception{
		//sql
		String sql = "UPDATE MEMBER SET DEL_YN = 'Y', MODIFY_DATE = SYSDATE WHERE NO = ? AND DEL_YN = 'N'";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, no);
		int result = pstmt.executeUpdate();
		
		//close
		JDBCTemplate.close(pstmt);
		
		return result;
	}

	//비밀번호 수정
	public int pwdEdite(Connection conn, MemberVo vo, String newPwd) throws Exception{
		//sql
		String sql = "UPDATE MEMBER SET PWD = ? WHERE ID = ? AND PWD = ? AND DEL_YN = 'N'";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, newPwd);
		pstmt.setString(2, vo.getId());
		pstmt.setString(3, vo.getPwd());
		int result = pstmt.executeUpdate();
		
		//cloes
		JDBCTemplate.close(pstmt);
		
		return result;
	}

	//닉네임 수정
	public int nickEdite(Connection conn, MemberVo vo, String newNick) throws Exception{
		//sql
		String sql = "UPDATE MEMBER SET NICK = ? WHERE ID = ? AND PWD = ? AND DEL_YN = 'N'";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, newNick);
		pstmt.setString(2, vo.getId());
		pstmt.setString(3, vo.getPwd());
		int result = pstmt.executeUpdate();
		
		//cloes
		JDBCTemplate.close(pstmt);
		
		return result;
	}

	

}
