package music.model;

import java.sql.SQLException;
import java.util.ArrayList;

import music.model.dto.SingerDTO;
import music.model.dto.SongDTO;
import music.model.dto.UserDTO;
import music.model.dto.singSongDTO;

public class Service {
	
	private static Service instance = new Service();
	private Service() {};
	public static Service getInstance() {
		return instance;
	}
	
	private SongDAO songDAO = SongDAO.getInstance();
	private SingerDAO singerDAO = SingerDAO.getInstance();
	private UserDAO userDAO = UserDAO.getInstance();
	private SongUserMappingDAO sumDAO = SongUserMappingDAO.getInstance();
	
	
	// Song
	public ArrayList<singSongDTO> getSongList(String name) throws SQLException{
		return songDAO.getSongs(name);
	}
	
	public boolean addSong(SongDTO song) throws SQLException {
		return songDAO.addSong(song);
	}
	public void addSongsFromFile(String f) throws Exception{
		songDAO.addSongsFromFile(f);
	}
	public ArrayList<singSongDTO> getSongListBySinger(int id) throws SQLException{
		return songDAO.getSongsBySinger(id);
	}
	
	// NewSong
	public boolean addNewSong(String date) throws SQLException {
		return songDAO.addNewSong(date);
	}
	
	public ArrayList<singSongDTO> getNewSong() throws SQLException {
		return songDAO.getNewSong();
	}
	
	
	// Singer
	public boolean addSinger(SingerDTO singer) throws SQLException {
		return singerDAO.addSinger(singer);
	}
	public void addSingersFromFile(String f) throws Exception{
		singerDAO.addSingersFromFile(f);
	}
	
	public boolean deleteSinger(int singerId) throws SQLException {
		return SingerDAO.deleteSinger(singerId);
	}
	
	public ArrayList<SingerDTO> getSingerList(String name) throws SQLException{
		return singerDAO.getSingers(name);
	}
	
	
	// Users
	public ArrayList<UserDTO> getAllUsers() throws SQLException {
		return userDAO.getAllUsers();
	}
	
	public boolean addUser(UserDTO user) throws SQLException {
		return userDAO.addUsers(user);
	}
	
	public boolean deleteUsers(int usersId) throws SQLException {
		return userDAO.deleteUsers(usersId);
	}
	
	
	// mapping
	public boolean addMyList(int songId, int userId) throws SQLException {
		return sumDAO.addMyList(songId, userId);
	}
	
	public ArrayList<singSongDTO> getMyList(int userId) throws SQLException {
		return sumDAO.getMyList(userId);
	}
}
