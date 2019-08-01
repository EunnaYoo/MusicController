package music.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import music.model.dto.PrintSong;
import music.model.util.DBUtil;

public class ChartDAO {
	private static ChartDAO instance= new ChartDAO();
	private ChartDAO() {};
	public static ChartDAO getInstance() {
		return instance;
	};
	
	//음악을 유저의 즐겨찾기 리스트에 추가
	public boolean addMyList(int songId, int userId) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		try{
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement("insert into song_user_mapping  values(?, ?)");
			pstmt.setInt(1, songId);
			pstmt.setInt(2, userId);
			
			int result = pstmt.executeUpdate();
		
			if(result == 1){
				return true;
			}
		}finally{
			DBUtil.close(con, pstmt);
		}
		return false;
	}
}
