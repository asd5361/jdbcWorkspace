package com.kh.mini.board.dao;

import java.sql.*;
import java.util.*;

import com.kh.jdbc.JDBCTemplate;
import com.kh.mini.board.vo.BoardVo;
import com.kh.mini.main.Main;

public class BoardDao {

	public int write(Connection conn, BoardVo vo) throws Exception{

		//sql
		String sql = "INSERT INTO BOARD(NO,TITLE,CONTENT,WRITER_NO) VALUES (SEQ_BOARD.NEXTVAL,?,?,?)";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, vo.getTitle());
		pstmt.setString(2, vo.getContent());
		pstmt.setString(3, vo.getWriterNo());
		int result = pstmt.executeUpdate();
		
		//close
		JDBCTemplate.close(pstmt);
		
		return result;
	}

	public List<BoardVo> boardList(Connection conn) throws Exception{
		//sql
		String sql = "SELECT M.NO ,B.TITLE ,M.NICK AS WRITER_NICK ,B.HIT ,TO_CHAR(B.ENROLL_DATE,'YYYY-MM-DD') AS ENROLL_DATE FROM BOARD B JOIN MEMBER M ON B.WRITER_NO = M.NO WHERE B.DEL_YN = 'N' ORDER BY B.NO DESC";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		
		List<BoardVo> voList = new ArrayList<BoardVo>();
		
		//rs
		while(rs.next()) {
			BoardVo vo = new BoardVo();
			vo.setNo(rs.getString("NO"));
			vo.setTitle(rs.getString("TITLE"));
			vo.setWriterNick(rs.getString("WRITER_NICK"));
			vo.setHit(rs.getString("HIT"));
			vo.setEnrollDate(rs.getString("ENROLL_DATE"));
			voList.add(vo);
		}
		
		//close
		JDBCTemplate.close(rs);
		JDBCTemplate.close(pstmt);
		
		return voList;
	}

	public BoardVo boardDetailByNo(Connection conn, String num) throws Exception{
		//sql
		String sql = "SELECT B.NO,TITLE,CONTENT,M.NICK AS WRITERNICK,HIT,TO_CHAR(B.ENROLL_DATE,'MM/DD')AS ENROLLDATE,TO_CHAR(B.MODIFY_DATE,'MM/DD') AS MODIFYDATE FROM BOARD B JOIN MEMBER M ON B.WRITER_NO = M.NO WHERE B.NO = ? AND B.DEL_YN = 'N'";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, num);
		ResultSet rs = pstmt.executeQuery();
		//rs
		BoardVo vo = null;
		if(rs.next()) {
			String no = rs.getString("NO");
			String title = rs.getString("TITLE");
			String content = rs.getString("CONTENT");
			String writerNick = rs.getString("WRITERNICK");
			String hit = rs.getString("HIT");
			String enrollDate = rs.getString("ENROLLDATE");
			String modifyDate = rs.getString("MODIFYDATE");
			vo = new BoardVo();
			vo.setNo(no);
			vo.setTitle(title);
			vo.setContent(content);
			vo.setWriterNick(writerNick);
			vo.setHit(hit);
			vo.setEnrollDate(enrollDate);
			vo.setModifyDate(modifyDate);
		}
		
		//close
		JDBCTemplate.close(rs);
		JDBCTemplate.close(pstmt);
		
		return vo;
	}

}
