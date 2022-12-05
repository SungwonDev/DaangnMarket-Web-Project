package dangn.town.ttown;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dangn.town.fileidx.fileuploadidxDTO;

import dangn.town.db.DBconn;
import dangn.town.ttown.TtownDTO;

public class TtownDAO {
	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;
	String sql = "";
	
	public TtownDTO insertFile(TtownDTO ttownDTO) {
		conn = null;
		pstmt = null;

		try {
			String sql = "INSERT INTO t_town (t_name, t_select1, t_select2, sample6_address, sample6_detailAddress, t_hp, t_web, t_content, t_pic1,t_pic2,t_pic3, t_pic4, t_pic5, t_filepath) VALUES (? ,? ,?, ?, ? ,? ,? ,? ,? ,? ,?, ?, ?, ?)";
			conn = DBconn.getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, ttownDTO.getName());
			pstmt.setString(2, ttownDTO.getSelect1());
			pstmt.setString(3, ttownDTO.getSelect2());
			pstmt.setString(4, ttownDTO.getAddress());
			pstmt.setString(5, ttownDTO.getDetailAddress());
			pstmt.setString(6, ttownDTO.getHp());
			pstmt.setString(7, ttownDTO.getWeb());
			pstmt.setString(8, ttownDTO.getContent());
			pstmt.setString(9, ttownDTO.getPic1());
			pstmt.setString(10, ttownDTO.getPic2());
			pstmt.setString(11, ttownDTO.getPic3());
			pstmt.setString(12, ttownDTO.getPic4());
			pstmt.setString(13, ttownDTO.getPic5());
			pstmt.setString(14, ttownDTO.getFilepath());
			int result = pstmt.executeUpdate();
			
			if(result >= 1) {
				return ttownDTO;
			}
			
		}catch(Exception e) {
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
	

	
	public List<TtownDTO> selectList(int start){
		List<TtownDTO> ttownList = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		try {
			conn = DBconn.getConnection();
			String sql = "SELECT * FROM t_town where t_idx=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, start);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				TtownDTO ttown = new TtownDTO();
				
				ttown.setIdx(rs.getInt("t_idx"));
				ttown.setName(rs.getString("t_name"));
				ttown.setSelect1(rs.getString("t_select1"));
				ttown.setSelect2(rs.getString("t_select2"));
				ttown.setAddress(rs.getString("sample6_address"));
				ttown.setDetailAddress(rs.getString("sample6_detailAddress"));
				ttown.setHp(rs.getString("t_hp"));
				ttown.setWeb(rs.getString("t_web"));
				ttown.setContent(rs.getString("t_content"));
				ttown.setPic1(rs.getString("t_pic1"));
				ttown.setPic2(rs.getString("t_pic2"));
				ttown.setPic3(rs.getString("t_pic3"));
				ttown.setPic4(rs.getString("t_pic4"));
				ttown.setPic5(rs.getString("t_pic5"));
				ttown.setFilepath(rs.getString("t_filepath"));
				ttownList.add(ttown);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBconn.close(conn,  pstmt, rs);
		}
		return ttownList;
	}

	
	public List<TtownDTO> selectListAll(){
		List<TtownDTO> ttownList = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DBconn.getConnection();
			String sql = "SELECT * FROM t_town ORDER BY t_idx DESC";
			pstmt = conn.prepareStatement(sql);		
			rs = pstmt.executeQuery();
			while(rs.next()) {
				TtownDTO ttown = new TtownDTO();
				
				ttown.setIdx(rs.getInt("t_idx"));
				ttown.setName(rs.getString("t_name"));
				ttown.setSelect1(rs.getString("t_select1"));
				ttown.setSelect2(rs.getString("t_select2"));
				ttown.setAddress(rs.getString("sample6_address"));
				ttown.setDetailAddress(rs.getString("sample6_detailAddress"));
				ttown.setHp(rs.getString("t_hp"));
				ttown.setWeb(rs.getString("t_web"));
				ttown.setContent(rs.getString("t_content"));
				ttown.setPic1(rs.getString("t_pic1"));
				ttown.setPic2(rs.getString("t_pic2"));
				ttown.setPic3(rs.getString("t_pic3"));
				ttown.setPic4(rs.getString("t_pic4"));
				ttown.setPic5(rs.getString("t_pic5"));
				ttown.setFilepath(rs.getString("t_filepath"));
				ttownList.add(ttown);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBconn.close(conn,  pstmt, rs);
		}
		return ttownList;
	}
	
	public List<fileuploadidxDTO> selectListidx(){
		List<fileuploadidxDTO> ttownidxList = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DBconn.getConnection();
			String sql = "SELECT t_idx FROM t_town";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				fileuploadidxDTO fileuploadidxDTO = new fileuploadidxDTO();
				
				fileuploadidxDTO.setIdx(rs.getInt("t_idx"));
				ttownidxList.add(fileuploadidxDTO);
				
			}	
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBconn.close(conn, pstmt, rs);
		}
		return ttownidxList;
	}
	
	public int countColumn(){
		int count=0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DBconn.getConnection();
			String sql = "SELECT COUNT(*) FROM t_town";
			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery();
			if(rs.next()){
                count = rs.getInt(1);
            }
        }catch(Exception ex){
            System.out.println("getListCount : " + ex);
        }finally{
            if(rs!=null)try{rs.close();}catch(SQLException ex){}
            if(pstmt!=null)try{pstmt.close();}catch(SQLException ex){}
        }
       
        return count;
    }

}

