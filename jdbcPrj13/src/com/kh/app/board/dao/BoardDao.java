package com.kh.app.board.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.kh.app.board.vo.BoardVo;
import com.kh.app.member.db.JDBCTemplate;

import oracle.jdbc.proxy.annotation.Pre;

public class BoardDao {

	public ArrayList<BoardVo> list(Connection conn) throws Exception{

		//sql
		String sql = "SELECT * FROM J_BOARD";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		//rs
		ResultSet rs = pstmt.executeQuery(); 
		ArrayList<BoardVo> boardList = new ArrayList<BoardVo>(); 
		while(rs.next()) {
			BoardVo vo = new BoardVo();
			vo.setNo(rs.getString("BOARD_NO"));
			vo.setTitle(rs.getString("TITLE"));
			vo.setContent(rs.getString("CONTENT"));
			vo.setWriter(rs.getString("WRITER_NO"));
			vo.setDate(rs.getString("ENROLL_DATE"));
			vo.setDel(rs.getString("DEL_YN"));
			boardList.add(vo);
		}
		//close
		JDBCTemplate.Close(rs);
		JDBCTemplate.Close(pstmt);
		
		return boardList;
	}

	public int write(Connection conn,BoardVo vo) throws Exception{
		//sql
		String sql = "INSERT INTO J_BOARD (BOARD_NO,TITLE,CONTENT,WRITER_NO,ENROLL_DATE,DEL_YN) VALUES "
				+ "(NVL((SELECT J.BOARD_NO FROM J_BOARD J WHERE ROWNUM = 1), 0) + 1,?,?,?,SYSDATE,'N')";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		pstmt.setString(1,vo.getTitle());
		pstmt.setString(2,vo.getContent());
		pstmt.setString(3, vo.getWriter());
		int result = pstmt.executeUpdate();
		//rs
		
		//close
		return result;
	}

	public ArrayList<BoardVo> read(Connection conn, String key) throws Exception{

		//sql
		String keyword = "%"+key + "%";
		String sql = "SELECT * FROM J_BOARD WHERE TITLE LIKE ? OR CONTENT LIKE ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, keyword);
		pstmt.setString(2, keyword);
		
		//rs
		ResultSet rs=pstmt.executeQuery();
		ArrayList<BoardVo> boardList = new ArrayList<BoardVo>();
		while(rs.next()) {
			BoardVo vo = new BoardVo();
			vo.setNo(rs.getString("BOARD_NO"));
			vo.setTitle(rs.getString("TITLE"));
			vo.setContent(rs.getString("CONTENT"));
			vo.setWriter(rs.getString("WRITER_NO")); 
			vo.setDate(rs.getString("ENROLL_DATE"));
			vo.setDel(rs.getString("DEL_YN"));
			boardList.add(vo);
		}
		
		//close
		JDBCTemplate.Close(rs);
		JDBCTemplate.Close(pstmt);
		
		return boardList;
	}

	public int update(Connection conn, BoardVo vo) throws Exception{
		
		//sql
		String sql;
		PreparedStatement pstmt;
		if(vo.getTitle()==null) {
			sql = "UPDATE J_BOARD SET CONTENT = ? WHERE BOARD_NO = ?";			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getContent());
			pstmt.setString(2, vo.getNo());
		}else if(vo.getContent()==null) {
			sql = "UPDATE J_BOARD SET TITLE = ? WHERE BOARD_NO = ?";			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getNo());
		}else{
			sql = "UPDATE J_BOARD SET TITLE = ? ,CONTENT = ? WHERE BOARD_NO = ?";			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContent());
			pstmt.setString(3, vo.getNo());
		}
		//rs
		int result = pstmt.executeUpdate();
		
		//close
		JDBCTemplate.Close(pstmt);
		
		return result;
	}

}
