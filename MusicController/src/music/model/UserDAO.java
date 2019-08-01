package music.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import music.model.dto.UserDTO;
import music.model.util.DBUtil;

public class UserDAO {
	
	private static UserDAO instance= new UserDAO();
	private UserDAO(){};
	public static UserDAO getInstance() {
		return instance;
	}
	
	// 전체 users 목록 보기
	public ArrayList<UserDTO> getAllUsers() throws SQLException {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		ArrayList<UserDTO> list = null;
		
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement("select * from users");
			rset = pstmt.executeQuery();

			list = new ArrayList<UserDTO>();
			
			while (rset.next()) {
				list.add(new UserDTO(rset.getInt(1), rset.getString(2), rset.getString(3)));
			}
			
		} finally {
			DBUtil.close(con, pstmt, rset);
		}
		return list;
	}
	
	// 추가
	public boolean addUsers(UserDTO users) throws SQLException{
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try{
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement("insert into users values(?, ?, ?)");
			pstmt.setInt(1, users.getId());
			pstmt.setString(2, users.getName());
			pstmt.setString(3, users.getPassword());
			
			int result = pstmt.executeUpdate();
		
			if(result == 1){
				return true;
			}
			
		} finally {
			DBUtil.close(con, pstmt);
		}
		return false;
	}
	
	// 삭제
	public boolean deleteUsers(int usersId) throws SQLException {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement("delete from users where user_id=?");
			pstmt.setInt(1, usersId);
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
