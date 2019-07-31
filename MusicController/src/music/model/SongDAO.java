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

import music.model.dto.SongDTO;
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
			pstmt = con.prepareStatement("insert into song values(?, ?, ?, ?, ?)");
			pstmt.setInt(1, song.getId());
			pstmt.setString(2, song.getName());
			pstmt.setString(3, song.getSinger());
			pstmt.setString(4, song.getDate());
			pstmt.setString(5, song.getLyrics());
			
			int result = pstmt.executeUpdate();
		
			if(result == 1){
				return true;
			}
		}finally{
			DBUtil.close(con, pstmt);
		}
		return false;
	}
	//노래 검색(비슷한 곡들 나오게)
	public ArrayList<SongDTO> getSongs(String name) throws SQLException{
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<SongDTO> list = null;
		try{
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement("select * from (select * from song where name like '"+name+ "%' order by name) where rownum<=20");
			rset = pstmt.executeQuery();
			
			list = new ArrayList<SongDTO>();
			while(rset.next()){
				list.add(new SongDTO(rset.getInt(1), rset.getString(2), rset.getString(3),rset.getString(4) ,rset.getString(5)) );
			}
		}finally{
			DBUtil.close(con, pstmt, rset);
		}
		return list;
	}
	
	//가수로 노래검색
	public ArrayList<SongDTO> getSongsBySinger(String name) throws SQLException{
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<SongDTO> list = null;
		try{
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement("select * from song where singer =?");
			pstmt.setString(1, name);
			rset = pstmt.executeQuery();
			
			list = new ArrayList<SongDTO>();
			while(rset.next()){
				list.add(new SongDTO(rset.getInt(1), rset.getString(2), rset.getString(3),rset.getString(4) ,rset.getString(5)) );
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
				list.add(new SongDTO(rset.getInt(1), rset.getString(2), rset.getString(3), rset.getString(4), rset.getString(5)) );
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
				list.add(new SongDTO(rset.getInt(1), rset.getString(2), rset.getString(3), rset.getString(4), rset.getString(5)) );
			}
		}finally{
			DBUtil.close(con, pstmt, rset);
		}
		return list;
	}
	
	public void addSongsFromFile(String f) throws NumberFormatException, SQLException {
		try{
			//파일 객체 생성
			File file = new File(f);
			//입력 스트림 생성
			FileReader filereader = new FileReader(file);
			//입력 버퍼 생성
			BufferedReader bufReader = new BufferedReader(filereader);
			String line = "";
			while((line = bufReader.readLine()) != null){
				String[] e = line.split("\t");
	//          System.out.println(Integer.parseInt(e[0])+"  "+e[1]);
				addSong(new SongDTO(Integer.parseInt(e[0]),e[1],e[2],e[3],"aaaa"));
			}
	  //.readLine()은 끝에 개행문자를 읽지 않는다.            
			bufReader.close();
		}catch (FileNotFoundException e) {
	      // TODO: handle exception
		}catch(IOException e){
			System.out.println(e);
		}
	}
	
}
