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
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;

import music.model.dto.PrintSongDTO;
import music.model.dto.SongDTO;
import music.model.util.DBUtil;

public class SongDAO {
	
	private static SongDAO instance= new SongDAO();
	private SongDAO(){};
	public static SongDAO getInstance() {
		return instance;
	}
	
	//�߰�
	public boolean addSong(SongDTO song) throws SQLException {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try{
			con = DBUtil.getConnection();
			
			pstmt = con.prepareStatement("insert into song values(?, ?, ?, ?)");
			pstmt.setInt(1, song.getId());
			pstmt.setString(2, song.getName());
			pstmt.setInt(3, song.getSinger());
			pstmt.setString(4, song.getDate());
			
			int result = pstmt.executeUpdate();
		
			if(result == 1){
				return true;
			}
			
		} finally {
			DBUtil.close(con, pstmt);
		}
		return false;
	}
	
	public void addSongsFromFile(String f) throws NumberFormatException, SQLException {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			// ���� ��ü ����
			File file = new File(f);
			// �Է� ��Ʈ�� ����
			FileReader filereader = new FileReader(file);
			// �Է� ���� ����
			BufferedReader bufReader = new BufferedReader(filereader);
			String line = "";

			con = DBUtil.getConnection();
			pstmt = con.prepareStatement("insert into song values(?, ?,?,?, 0)");
			
			while ((line = bufReader.readLine()) != null) {
				
				String[] e = line.split("\t");
				
				pstmt.setInt(1, Integer.parseInt(e[0]));
				pstmt.setString(2, e[1]);
				pstmt.setInt(3, Integer.parseInt(e[2]));
				pstmt.setString(4, e[3]);
				
				try {
					pstmt.executeUpdate();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
			// .readLine()�� ���� ���๮�ڸ� ���� �ʴ´�.
			bufReader.close();
		} catch (FileNotFoundException fn) {
			fn.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// �뷡 �˻�(�����ڿ�)  SongDTO(SongId, SongName, SingerId, Date)
	public ArrayList<SongDTO> getSongs(String name) throws SQLException {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		ArrayList<SongDTO> list = null;
		
		try{
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement("select * "
										+ "from (select * from song where song_name like '"+name+ "%' order by song_name) "
										+ "where rownum<=20");
			
			rset = pstmt.executeQuery();
			list = new ArrayList<SongDTO>();
			
			while(rset.next()){
				list.add(new SongDTO(rset.getInt(1), rset.getString(2), rset.getInt(3),rset.getString(4), rset.getInt(5)));
			}
			
		} finally {
			DBUtil.close(con, pstmt, rset);
		}
		return list;
	}
	
	//�뷡 �˻�  PrintSong(songId,songName,SingerName,Date)
	public ArrayList<PrintSongDTO> printSongs(String name) throws SQLException {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		ArrayList<PrintSongDTO> list = null;
		
		try{
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement("select sg.song_id, sg.song_name, s.singer_name, sg.release_date " 
										+ "from song sg inner join singer s " 
										+ "on sg.singer_id = s.singer_id " 
										+ "where sg.song_name like '" + name + "%' " 
										+ "order by sg.song_name");
			
			rset = pstmt.executeQuery();
			list = new ArrayList<PrintSongDTO>();
			
			while(rset.next()){
				list.add(new PrintSongDTO(rset.getInt(1), rset.getString(2), rset.getString(3),rset.getString(4)));
			}
			
		} finally {
			DBUtil.close(con, pstmt, rset);
		}
		return list;
	}
	
	// ������ �뷡�˻�
	public ArrayList<PrintSongDTO> getSongsBySinger(int id) throws SQLException {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		ArrayList<PrintSongDTO> list = null;
		
		try{
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement("select sg.song_id, sg.song_name, s.singer_name, sg.release_date " 
										+ "from song sg inner join singer s " 
										+ "on sg.singer_id = s.singer_id " 
										+ "where s.singer_id = ? " 
										+ "order by sg.song_name");
			
			pstmt.setInt(1, id);
			rset = pstmt.executeQuery();
			
			list = new ArrayList<PrintSongDTO>();
			
			while(rset.next()) {
				list.add(new PrintSongDTO(rset.getInt(1), rset.getString(2), rset.getString(3),rset.getString(4) ) );
			}
			
		} finally {
			DBUtil.close(con, pstmt, rset);
		}
		return list;
	}
	
	//delete
	public boolean deleteSong(int songId) throws SQLException {

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
}
