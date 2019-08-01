package music.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import music.model.dto.singSongDTO;
import music.model.util.DBUtil;

public class SongUserMappingDAO {
	
	private static SongUserMappingDAO instance= new SongUserMappingDAO();
	private SongUserMappingDAO() {};
	public static SongUserMappingDAO getInstance() {
		return instance;
	};
	
	// 음악을 유저의 즐겨찾기 리스트에 추가
	public boolean addMyList(int songId, int userId) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement("insert into song_user_mapping values(?, ?)");
			pstmt.setInt(1, songId);
			pstmt.setInt(2, userId);
			
			int result = pstmt.executeUpdate();
		
			if(result == 1){
				return true;
			}
			
		} finally{
			DBUtil.close(con, pstmt);
		}
		return false;
	}
	
	// 내 목록 보기
	public ArrayList<singSongDTO> getMyList(int userId) throws SQLException{
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<singSongDTO> list = null;
		
		try{
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement("select s.song_id, s.song_name, sg.singer_name, s.release_date " + 
					"from song_user_mapping sum inner join song s " + 
					"on sum.song_id = s.song_id " + 
					"inner join singer sg " + 
					"on s.singer_id = sg.singer_id " + 
					"where sum.user_id = ?");
			
			pstmt.setInt(1, userId);
			
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
}
