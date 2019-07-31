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

import music.model.dto.SingerDTO;
import music.model.dto.SongDTO;
import music.model.util.DBUtil;

public class SingerDAO {
	private static SingerDAO instance= new SingerDAO();
	private SingerDAO() {};
	public static SingerDAO getInstance() {
		return instance;
	}
	//추가
	public boolean addSinger(SingerDTO singer) throws SQLException{
		Connection con = null;
		PreparedStatement pstmt = null;
		try{
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement("insert into singer values(?, ?)");
			pstmt.setInt(1, singer.getId());
			pstmt.setString(2, singer.getName());
			
			int result = pstmt.executeUpdate();
		
			if(result == 1){
				return true;
			}
			
		} finally {
			DBUtil.close(con, pstmt);
		}
		return false;
	}
	
	public ArrayList<SingerDTO> getSingers(String name) throws SQLException{
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<SingerDTO> list = null;
		try{
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement("select * from (select * from singer where name like '"+name+ "%' order by name) where rownum<=20");
			rset = pstmt.executeQuery();
			
			list = new ArrayList<SingerDTO>();
			while(rset.next()){
				list.add(new SingerDTO(rset.getInt(1), rset.getString(2)) );
			}
		}finally{
			DBUtil.close(con, pstmt, rset);
		}
		return list;
	}
	
	public void addSingersFromFile(String f) throws NumberFormatException, SQLException {
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
				addSinger(new SingerDTO(Integer.parseInt(e[0]),e[1]));
			}
	  //.readLine()은 끝에 개행문자를 읽지 않는다.            
			bufReader.close();
		} catch (FileNotFoundException e) {
	      // TODO: handle exception
		} catch (IOException e) {
			System.out.println(e);
		}
	}
}
