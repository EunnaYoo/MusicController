package music.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

import music.model.dto.SongDTO;
import music.model.dto.singSongDTO;
import music.model.util.DBUtil;

public class SongDAO {
	private static SongDAO instance= new SongDAO();
	private SongDAO() {};
	public static SongDAO getInstance() {
		return instance;
	}
	
	//추가
	public boolean addSong(SongDTO song) throws SQLException{
		Connection con = null;
		PreparedStatement pstmt = null;
		try{
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement("insert into song values(?, ?, ?, ?)");
			pstmt.setInt(1, song.getId());
			pstmt.setString(2, song.getName());
			pstmt.setString(3, song.getSinger());
			pstmt.setString(4, song.getDate());
			
			int result = pstmt.executeUpdate();
		
			if(result == 1){
				return true;
			}
		}finally{
			DBUtil.close(con, pstmt);
		}
		return false;
	}
	
	public void addSongsFromFile(String f) throws NumberFormatException, SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		try{
			//파일 객체 생성
			File file = new File(f);
			//입력 스트림 생성
			FileReader filereader = new FileReader(file);
			//입력 버퍼 생성
			BufferedReader bufReader = new BufferedReader(filereader);
			String line = "";
			
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement("insert into song values(?, ?,?,?)");
			
			while((line = bufReader.readLine()) != null){
				String[] e = line.split("\t");
				pstmt.setInt(1, Integer.parseInt(e[0]));
				pstmt.setString(2, e[1]);
				pstmt.setInt(3, Integer.parseInt(e[2]));
				pstmt.setString(4, e[3]);
				try {
					pstmt.executeUpdate();
				} catch (Exception e2) {
					System.out.println(Arrays.toString(e));
					e2.printStackTrace();
				}
				
			}
			//.readLine()은 끝에 개행문자를 읽지 않는다.            
			bufReader.close();
		}catch (FileNotFoundException e) {
			e.getStackTrace();
		}catch(IOException e){
			e.getStackTrace();
		}
	}
	
	public static boolean deleteSong(int songId) throws SQLException {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement("delete from song where song_id=?");
			pstmt.setInt(1, songId);
			int result = pstmt.executeUpdate();
			if (result == 1) {
				return true;
			}
		} finally {
			DBUtil.close(con, pstmt);
		}
		return false;
	}

	// 제목으로 노래 검색
	public ArrayList<singSongDTO> getSongs(String title) throws SQLException{
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<singSongDTO> list = null;
		
		try{
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement("select sg.song_id, sg.song_name, s.singer_name, sg.release_date " + 
					"from song sg inner join singer s " + 
					"on sg.singer_id = s.singer_id " + 
					"where sg.song_name like '"+title+"%' " + 
					"order by sg.song_name");
			
			rset = pstmt.executeQuery();
			
			list = new ArrayList<singSongDTO>();
			while(rset.next()){
				list.add(new singSongDTO(rset.getInt(1), rset.getString(2), rset.getString(3), rset.getString(4)));
			}
		}finally{
			DBUtil.close(con, pstmt, rset);
		}
		return list;
	}
	
	// 가수로 노래검색
	public ArrayList<singSongDTO> getSongsBySinger(int id) throws SQLException{
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<singSongDTO> list = null;
		try{
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement("select sg.song_id, sg.song_name, s.singer_name, sg.release_date " + 
					"from song sg inner join singer s " + 
					"on sg.singer_id = s.singer_id " + 
					"where s.singer_id = ? " + 
					"order by sg.song_name");
			
			pstmt.setInt(1, id);
			
			rset = pstmt.executeQuery();
			
			list = new ArrayList<singSongDTO>();
			while(rset.next()){
				list.add(new singSongDTO(rset.getInt(1), rset.getString(2), rset.getString(3),rset.getString(4) ) );
			}
		}finally{
			DBUtil.close(con, pstmt, rset);
		}
		return list;
	}
	
	//getLatest
	public ArrayList<SongDTO> getLatest() throws SQLException{
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<SongDTO> list = null;
		try{
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement("select * from latest");
			rset = pstmt.executeQuery();
			
			list = new ArrayList<SongDTO>();
			while(rset.next()){
				list.add(new SongDTO(rset.getInt(1), rset.getString(2), rset.getString(3), rset.getString(4)) );
			}
		}finally{
			DBUtil.close(con, pstmt, rset);
		}
		return list;
	}
	
	//getPopular
	public ArrayList<SongDTO> getPopular() throws SQLException{
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<SongDTO> list = null;
		try{
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement("select * from popular");
			rset = pstmt.executeQuery();
			
			list = new ArrayList<SongDTO>();
			while(rset.next()){
				list.add(new SongDTO(rset.getInt(1), rset.getString(2), rset.getString(3), rset.getString(4)) );
			}
		}finally{
			DBUtil.close(con, pstmt, rset);
		}
		return list;
	}
}
