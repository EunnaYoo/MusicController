package music.service;

import java.sql.SQLException;
import java.util.ArrayList;

import music.model.MappingDAO;
import music.model.NewSongDAO;
import music.model.PopularDAO;
import music.model.SingerDAO;
import music.model.SongDAO;
import music.model.UserDAO;
import music.model.dto.PrintSongDTO;
import music.model.dto.SingerDTO;
import music.model.dto.SongDTO;
import music.model.dto.UserDTO;

public class Service {
	
	private static Service instance = new Service();
	private Service(){};
	public static Service getInstance() {
		return instance;
	}
	
	private SongDAO songDAO = SongDAO.getInstance();
	private SingerDAO singerDAO = SingerDAO.getInstance();
	private UserDAO userDAO = UserDAO.getInstance();
	private MappingDAO mappingDAO = MappingDAO.getInstance();
	private NewSongDAO newsongDAO = NewSongDAO.getInstance();
	private PopularDAO popularDAO = PopularDAO.getInstance();
	
	// Song
	public ArrayList<SongDTO> getSongList(String name) throws SQLException {
		return songDAO.getSongs(name);
	}
	
	public ArrayList<PrintSongDTO> printSongList(String name) throws SQLException {
		return songDAO.printSongs(name);
	}
	
	public boolean addSong(SongDTO song) throws SQLException {
		return songDAO.addSong(song);
	}
	
	public void addSongsFromFile(String f) throws Exception {
		songDAO.addSongsFromFile(f);
	}
	
	public ArrayList<PrintSongDTO> getSongListBySinger(int id) throws SQLException {
		return songDAO.getSongsBySinger(id);
	}
	
	public boolean deleteSong(int songId) throws SQLException {
		return songDAO.deleteSong(songId);
	}
	
	// Singer
	public boolean addSinger(SingerDTO singer) throws SQLException {
		return singerDAO.addSinger(singer);
	}
	
	public void addSingersFromFile(String f) throws Exception {
		singerDAO.addSingersFromFile(f);
	}
	
	public ArrayList<SingerDTO> getSingerList(String name) throws SQLException {
		return singerDAO.getSingers(name);
	}
	
	public boolean deleteSinger(int singerId) throws SQLException {
		return singerDAO.deleteSinger(singerId);
	}
	
	// Users
	public boolean addUser(UserDTO user) throws SQLException {
		return userDAO.addUser(user);
	}
	
	public boolean deleteUser(int userId) throws SQLException {
		return userDAO.deleteUser(userId);
	}
	
	// Mapping
	public boolean addMyList(int songId, int userId) throws SQLException {
		return mappingDAO.addMyList(songId, userId);
	}
	public ArrayList<PrintSongDTO> getMyList(int id) throws SQLException{
		return mappingDAO.getMyList(id);
	}
	
	// NewSong
	public ArrayList<PrintSongDTO> getNew() throws SQLException{
		return newsongDAO.getNew();
	}
	public boolean addNewSong(String date) throws SQLException {
		return newsongDAO.addNewSong(date);
	}
	
	// PopularChart
	public boolean updatePopularChart() throws SQLException {
		return popularDAO.updatePopularChart();
	}
	public ArrayList<PrintSongDTO> getPopular() throws SQLException {
		return popularDAO.getPopular();
	}
	public boolean addPopularity(int songId) throws SQLException {
		return popularDAO.addPopularity(songId);
	}
}
