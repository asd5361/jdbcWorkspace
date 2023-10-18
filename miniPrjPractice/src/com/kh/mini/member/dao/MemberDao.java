package com.kh.mini.member.dao;

import java.sql.*;

import com.kh.jdbc.JDBCTemplate;
import com.kh.mini.member.vo.MemberVo;

public class MemberDao {


	//회원가입
	public int join(Connection conn, MemberVo vo) throws Exception{
		
		String sql = "INSERT INTO MEMBER_MINI(MEMBER_NO,AREAS_CODE,ID,PWD,NICK,NAME,EMAIL,PHONE,ADDRESS)"
				+ "VALUES (SEQ_MEMBER_MINI.NEXTVAL,?,?,?,?,?,?,?,?)";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, vo.getAreasCode());
		pstmt.setString(2, vo.getId());
		pstmt.setString(3, vo.getPwd());
		pstmt.setString(4, vo.getNick());
		pstmt.setString(5, vo.getName());
		pstmt.setString(6, vo.getEmail());
		pstmt.setString(7, vo.getPhone());
		pstmt.setString(8, vo.getAddress());
		
		int result = pstmt.executeUpdate();
		JDBCTemplate.close(pstmt);
		
		return result;
	}

	//회원가입에 쓸 주소 -> 동네코드 추출 (예)서울시강남구신사동 -> 신사동
	public String codeMake(Connection conn, String address) throws Exception{
		int f = address.indexOf("구");
		int e = address.indexOf("동");
		String code = address.substring(f+1);
		String sql = "SELECT AREAS_CODE FROM AREAS_MINI WHERE AREAS_NAME LIKE ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, code);
		ResultSet rs = pstmt.executeQuery();
		String areasCode = null;
		if(rs.next()) {
			areasCode = rs.getString("AREAS_CODE");			
		}
		JDBCTemplate.close(rs);
		JDBCTemplate.close(pstmt);
		
		return areasCode;
	}

	public MemberVo login(Connection conn, MemberVo vo) throws Exception{
		String sql = "";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		MemberVo userVo= null;
		if(rs.next()) {
			userVo = new MemberVo();
			userVo.setMemberNo(rs.getString("MEMBER_NO"));
			userVo.setAreasCode(rs.getString("AREAS_CODE"));
			userVo.setId(rs.getString("ID"));
			userVo.setPwd(rs.getString("PWD"));
			userVo.setNick(rs.getString("NICK"));
			userVo.setName(rs.getString("NAME"));
			/*
			
			
			EMAIL
			PHONE
			ADDRESS
			JOIN_DATE
			EDIT_DATE
			*/
		}
			
		return userVo;
	}

}
