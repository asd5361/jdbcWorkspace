package com.kh.mini.member.dao;

import java.sql.*;
import java.util.HashMap;

import com.kh.mini.jdbc.JDBCTemplate;
import com.kh.mini.member.vo.MemberVo;

import oracle.jdbc.proxy.annotation.Pre;

public class MemberDao {


	//회원가입
	public int join(Connection conn, MemberVo vo) throws Exception{
		
		String sql = "INSERT INTO MEMBER(MEMBER_NO,AREAS_CODE,ID,PWD,NICK,NAME,EMAIL,PHONE,ADDRESS)"
				+ "VALUES (SEQ_MEMBER.NEXTVAL,?,UPPER(?),?,?,?,?,?,?)";
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
		String sql = "SELECT AREAS_CODE FROM AREAS WHERE AREAS_NAME LIKE ?";
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
	//로그인
	public MemberVo login(Connection conn, MemberVo vo) throws Exception{
		String sql = "SELECT * FROM MEMBER JOIN AREAS USING(AREAS_CODE) WHERE ID = UPPER(?) AND PWD = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1,vo.getId());
		pstmt.setString(2, vo.getPwd());
		ResultSet rs = pstmt.executeQuery();
		MemberVo userVo= null;
		if(rs.next()) {
			userVo = new MemberVo();
			userVo.setMemberNo(rs.getString("MEMBER_NO"));
			userVo.setAreasCode(rs.getString("AREAS_CODE"));
			userVo.setAreasName(rs.getString("AREAS_NAME"));
			userVo.setId(rs.getString("ID"));
			userVo.setPwd(rs.getString("PWD"));
			userVo.setNick(rs.getString("NICK"));
			userVo.setName(rs.getString("NAME"));
			userVo.setEmail(rs.getString("EMAIL"));
			userVo.setPhone(rs.getString("PHONE"));
			userVo.setAddress(rs.getString("ADDRESS"));
			userVo.setJoinDate(rs.getString("JOIN_DATE"));
			userVo.setEditDate(rs.getString("EDIT_DATE"));
		}
		JDBCTemplate.close(pstmt);
		
		return userVo;
	}
	//회원탈퇴
	public int quit(Connection conn,String no) throws Exception{
		
		String sql = "UPDATE MEMBER SET QUIT_YN = 'Y' WHERE MEMBER_NO = ?";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, no);
		int result = pstmt.executeUpdate();
		
		JDBCTemplate.close(pstmt);
		
		return result;
	}
	//비밀번호 변경
	public int changePwd(Connection conn, HashMap<String, String> map) throws Exception{
		
		String sql = "UPDATE MEMBER SET PWD = ? WHERE MEMBER_NO = ? AND PWD = ?";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, map.get("newPwd"));
		pstmt.setString(2, map.get("memberNo"));
		pstmt.setString(3, map.get("oldPwd"));
		int result = pstmt.executeUpdate();
		
		JDBCTemplate.close(pstmt);
		
		return result;
		
	}
	//닉네임 변경
	public int changeNick(Connection conn, MemberVo vo) throws Exception{
		
		String sql ="UPDATE MEMBER SET NICK = ? WHERE MEMBER_NO = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, vo.getNick());
		pstmt.setString(2, vo.getMemberNo());
		int result = pstmt.executeUpdate();
		
		JDBCTemplate.close(pstmt);
		
		return result;
	}
	//주소 변경
	public int changeAddress(Connection conn, MemberVo vo) throws Exception{
		String sql ="UPDATE MEMBER SET ADDRESS = ? WHERE MEMBER_NO = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, vo.getAddress());
		pstmt.setString(2, vo.getMemberNo());
		int result = pstmt.executeUpdate();
		
		JDBCTemplate.close(pstmt);
		
		return result;
	}
	//전화번호 변경
	public int changePhone(Connection conn, MemberVo vo)  throws Exception{
		String sql ="UPDATE MEMBER SET PHONE = ? WHERE MEMBER_NO = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, vo.getPhone());
		pstmt.setString(2, vo.getMemberNo());
		int result = pstmt.executeUpdate();
		
		JDBCTemplate.close(pstmt);
		
		return result;
	}

}
