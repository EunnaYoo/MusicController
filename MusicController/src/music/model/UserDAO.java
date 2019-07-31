package music.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import music.model.dto.UserDTO;
import music.model.util.DBUtil;

public class UserDAO {
	private static UserDAO instance= new UserDAO();
	private UserDAO() {};
	public static UserDAO getInstance() {
		return instance;
	}
	//Ãß°¡
	public boolean addsinger(UserDTO singer) throws SQLException{
		Connection con = null;
		PreparedStatement pstmt = null;
		try{
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement("insert into user values(?, ?)");
			pstmt.setString(1, singer.getId());
			pstmt.setString(2, singer.getName());
			
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
