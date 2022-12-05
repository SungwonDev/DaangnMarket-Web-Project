package com.main.board;
import com.main.fileidx.*;
import com.main.filecount.*; 

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import com.main.db.DBconn;
import com.main.board.boardDTO;

public class boardDAO {
	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;
	String sql="";
	
	
	public boardDTO insertFile(boardDTO boardDTO){
		conn = null;
		pstmt =null;
		
		try {
			sql="INSERT INTO pj_board (b_email, b_name, b_title, b_content, b_hit, b_file1, b_file2, b_file3, b_file4, b_file5, b_filepath) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			conn = DBconn.getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, boardDTO.getEmail());
			pstmt.setString(2, boardDTO.getName());
			pstmt.setString(3, boardDTO.getTitle());
			pstmt.setString(4, boardDTO.getContent());
			pstmt.setInt(5, boardDTO.getHit());
			pstmt.setString(6, boardDTO.getFile1());
			pstmt.setString(7, boardDTO.getFile2());
			pstmt.setString(8, boardDTO.getFile3());
			pstmt.setString(9, boardDTO.getFile4());
			pstmt.setString(10, boardDTO.getFile5());
			pstmt.setString(11, boardDTO.getFilepath());
			int result = pstmt.executeUpdate();

			if(result>=1) {
				return boardDTO;
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			try {
				if(pstmt !=null) {pstmt.close();}
				if(conn !=null) {conn.close();}
			}catch(SQLException e) {
				throw new RuntimeException(e.getMessage());
			}
		}
		return null;
	}
	
	public List<boardDTO> selectList(int start){
		List<boardDTO> fList = new ArrayList();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DBconn.getConnection();
			String sql = "SELECT * FROM pj_board WHERE b_idx=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, start);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				boardDTO boardDTO = new boardDTO();
				
				boardDTO.setIdx(rs.getInt("b_idx"));
				boardDTO.setEmail(rs.getString("b_email"));
				boardDTO.setName(rs.getString("b_name"));
				boardDTO.setTitle(rs.getString("b_title"));
				boardDTO.setContent(rs.getString("b_content"));
				boardDTO.setHit(rs.getInt("b_hit"));
				boardDTO.setFile1(rs.getString("b_file1"));
				boardDTO.setFile2(rs.getString("b_file2"));
				boardDTO.setFile3(rs.getString("b_file3"));
				boardDTO.setFile4(rs.getString("b_file4"));
				boardDTO.setFile5(rs.getString("b_file5"));
				boardDTO.setFilepath(rs.getString("b_filepath"));
				fList.add(boardDTO);
			}	
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBconn.close(conn, pstmt, rs);
		}
		return fList;
	}
	

	//紐⑤뱺 �긽�뭹 �벑濡� 湲� 由ъ뒪�듃�뿉 �꽔湲곗씤 寃� 媛숈쓬
	public List<boardDTO> selectListAll(){
		List<boardDTO> fList = new ArrayList();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DBconn.getConnection();
			String sql = "SELECT * FROM pj_board WHERE b_idx=?";
			pstmt = conn.prepareStatement(sql);
			//pstmt.setInt(1, start);
//			pstmt.setInt(2, pagePerCount);	
			rs = pstmt.executeQuery();
			while(rs.next()) {
				boardDTO boardDTO = new boardDTO();
				
				boardDTO.setIdx(rs.getInt("b_idx"));
				boardDTO.setEmail(rs.getString("b_email"));
				boardDTO.setName(rs.getString("b_name"));
				boardDTO.setTitle(rs.getString("b_title"));
				boardDTO.setContent(rs.getString("b_content"));
				boardDTO.setHit(rs.getInt("b_hit"));
				boardDTO.setFile1(rs.getString("b_file1"));
				boardDTO.setFile2(rs.getString("b_file2"));
				boardDTO.setFile3(rs.getString("b_file3"));
				boardDTO.setFile4(rs.getString("b_file4"));
				boardDTO.setFile5(rs.getString("b_file5"));
				boardDTO.setFilepath(rs.getString("b_filepath"));
				fList.add(boardDTO);
			}	
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBconn.close(conn, pstmt, rs);
		}
		return fList;
	}
	
	//�궎�썙�뱶媛� �룷�븿�맂 寃뚯떆湲��뱾 由ъ뒪�듃
	public List<boardDTO> selectListAll(String keyWord){
		List<boardDTO> fList = new ArrayList();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DBconn.getConnection();
			StringBuffer query = new StringBuffer();
		
			//�궎�썙�뱶媛� �엳嫄곕굹 諛붾줈 �뿏�꽣瑜� �늻瑜댁� �븡�븯�떎硫�
			//湲� �젣紐⑹뿉留� �궎�썙�뱶媛� �엳�뒗 寃쎌슦�쓽 sql臾몄씠�뿬�꽌 b_comment�뿉�룄 �궎�썙�뱶媛� �엳�뒗 寃쎌슦 異붽��븿
			if(keyWord!=null && !keyWord.equals("")) {
				query.append("SELECT * FROM pj_board WHERE b_name LIKE ? ORDER BY b_idx DESC");
				pstmt = conn.prepareStatement(query.toString());
				pstmt.setString(1, "%"+keyWord+"%");
				rs = pstmt.executeQuery();

			}else{
				//�궎�썙�뱶 �뾾�씠 洹몃깷 寃��깋�뻼�떎硫�
				query.append("SELECT * FROM pj_board ORDER BY b_idx DESC");
				pstmt = conn.prepareStatement(query.toString());

				rs = pstmt.executeQuery();
			}
		
			while(rs.next()) {
				boardDTO boardDTO = new boardDTO();
				
				boardDTO.setIdx(rs.getInt("b_idx"));
				boardDTO.setEmail(rs.getString("b_email"));
				boardDTO.setName(rs.getString("b_name"));
				boardDTO.setTitle(rs.getString("b_title"));
				boardDTO.setContent(rs.getString("b_content"));
				boardDTO.setHit(rs.getInt("b_hit"));
				boardDTO.setFile1(rs.getString("b_file1"));
				boardDTO.setFile2(rs.getString("b_file2"));
				boardDTO.setFile3(rs.getString("b_file3"));
				boardDTO.setFile4(rs.getString("b_file4"));
				boardDTO.setFile5(rs.getString("b_file5"));
				boardDTO.setFilepath(rs.getString("b_filepath"));
				fList.add(boardDTO);
			}	
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBconn.close(conn, pstmt, rs);
		}
		return fList;
	}
	
	//吏��뿭 + �궎�썙�뱶濡� 寃��깋�떆
	public List<boardDTO> selectListAll(String S_location, String keyWord){
		List<boardDTO> fList = new ArrayList();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DBconn.getConnection();
			StringBuffer query = new StringBuffer();
		
			if(keyWord!=null && !keyWord.equals("")) {
				query.append("SELECT * FROM pj_board WHERE b_name LIKE ? ORDER BY b_idx DESC");
				pstmt = conn.prepareStatement(query.toString());
				pstmt.setString(1, "%"+keyWord+"%");
				rs = pstmt.executeQuery();

			}else{
				query.append("SELECT * FROM pj_board ORDER BY b_idx DESC");
				pstmt = conn.prepareStatement(query.toString());
				rs = pstmt.executeQuery();
			}
			
			
			while(rs.next()) {
				boardDTO boardDTO = new boardDTO();
				
				boardDTO.setIdx(rs.getInt("b_idx"));
				boardDTO.setEmail(rs.getString("b_email"));
				boardDTO.setName(rs.getString("b_name"));
				boardDTO.setTitle(rs.getString("b_title"));
				boardDTO.setContent(rs.getString("b_content"));
				boardDTO.setHit(rs.getInt("b_hit"));
				boardDTO.setFile1(rs.getString("b_file1"));
				boardDTO.setFile2(rs.getString("b_file2"));
				boardDTO.setFile3(rs.getString("b_file3"));
				boardDTO.setFile4(rs.getString("b_file4"));
				boardDTO.setFile5(rs.getString("b_file5"));
				boardDTO.setFilepath(rs.getString("b_filepath"));
				fList.add(boardDTO);
			}	
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBconn.close(conn, pstmt, rs);
		}
		return fList;
	}
	
	
	//idx 由ъ뒪�듃
	//�궗�슜 �슜�룄 �씠�빐 �븘吏� 紐삵븿
	public List<boardidxDTO> selectListidx(){
		List<boardidxDTO> fileidxList = new ArrayList();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DBconn.getConnection();
			String sql = "SELECT b_idx FROM pj_board";
			pstmt = conn.prepareStatement(sql);
//			pstmt.setInt(1, start);
//			pstmt.setInt(2, pagePerCount);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				boardidxDTO boardidxDTO = new boardidxDTO();
				
				boardidxDTO.setIdx(rs.getInt("b_idx"));
				fileidxList.add(boardidxDTO);
				
			}	
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBconn.close(conn, pstmt, rs);
		}
		return fileidxList;
	}

	//移댁슫�듃
	//�궗�슜�슜�룄 �븘吏� �씠�빐 紐삵븿
	public int countColumn(){
		int count=0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DBconn.getConnection();
			String sql = "SELECT COUNT(*) FROM pj_board";
			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery();
			if(rs.next()){
                count = rs.getInt(1);
            }
        }catch(Exception ex){
            System.out.println("getListCount �뿉�윭 : " + ex);
        }finally{
            if(rs!=null)try{rs.close();}catch(SQLException ex){}
            if(pstmt!=null)try{pstmt.close();}catch(SQLException ex){}
        }
       
        return count;
    }
	
	
	//寃��깋
	//comment�뿉 寃��깋 �궎�썙�뱶媛� �엳�쓣 �떆�뿉�룄 sql �옉�꽦�빐�꽌 由ъ뒪�듃�뿉 �뿰寃곗떆耳쒖빞 �븿.(異붽� �셿猷�)
	public List<boardDTO> searchtAllList(String keyWord){
		List<boardDTO> fList = new ArrayList();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DBconn.getConnection();
			String sql = "SELECT * FROM pj_board";
			
			if(keyWord!=null && !keyWord.equals("")) {
				sql +="WHERE b_name LIKE '%" +keyWord.trim()+ "%' OR b_comment LIKE '%" +keyWord.trim()+ "%' ORDER BY b_idx";

			}else {
				sql +="order by b_idx";
			}
			
			pstmt = conn.prepareStatement(sql);	
			rs = pstmt.executeQuery();
			while(rs.next()) {
				boardDTO boardDTO = new boardDTO();
				
				boardDTO.setIdx(rs.getInt("b_idx"));
				boardDTO.setEmail(rs.getString("b_email"));
				boardDTO.setName(rs.getString("b_name"));
				boardDTO.setTitle(rs.getString("b_title"));
				boardDTO.setContent(rs.getString("b_content"));
				boardDTO.setHit(rs.getInt("b_hit"));
				boardDTO.setFile1(rs.getString("b_file1"));
				boardDTO.setFile2(rs.getString("b_file2"));
				boardDTO.setFile3(rs.getString("b_file3"));
				boardDTO.setFile4(rs.getString("b_file4"));
				boardDTO.setFile5(rs.getString("b_file5"));
				boardDTO.setFilepath(rs.getString("b_filepath"));
				fList.add(boardDTO);
			}	
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBconn.close(conn, pstmt, rs);
		}
		return fList;
	}
	
	

	//�궘�젣 //id �빐�떦 �궘�젣�삁�젙 肄붾뱶
	//
	public int deleteList(String id){
		DBconn db = new DBconn();
		int result = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		int rs = 0;
		try {
			conn = DBconn.getConnection();
			String sql = "delete from pj_board where id = ?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id.trim());//	
			rs = pstmt.executeUpdate();
		
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			db.close();
		}
		return rs;
	}
}