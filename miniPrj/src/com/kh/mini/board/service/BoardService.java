package com.kh.mini.board.service;

import java.sql.*;
import java.util.*;

import com.kh.jdbc.JDBCTemplate;
import com.kh.mini.board.dao.BoardDao;
import com.kh.mini.board.vo.BoardVo;

public class BoardService {

	private final BoardDao dao;
	
	public BoardService() {
		 dao = new BoardDao();
	}

	public int write(BoardVo vo) throws Exception{

		//conn
		Connection conn = JDBCTemplate.getConnection();
		//dao
		int result = dao.write(conn,vo);
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

	//게시글 목록 (최신순)
	public List<BoardVo> boardList() throws Exception{
		//conn
		Connection conn = JDBCTemplate.getConnection();
		//dao
		List<BoardVo> voList = dao.boardList(conn);
		
		//close
		JDBCTemplate.close(conn);
		return voList;
	}

	public BoardVo boardDetailByNo(String num) throws Exception{

		//conn
		Connection conn = JDBCTemplate.getConnection();
		//dao
		BoardVo vo = dao.boardDetailByNo(conn,num);
		//close
		JDBCTemplate.close(conn);
		
		return vo;
	}

}
