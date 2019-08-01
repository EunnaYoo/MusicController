package music.model;

import java.sql.SQLException;
import java.util.ArrayList;

import music.model.dto.PrintSong;
import music.model.dto.SingerDTO;
import music.model.dto.SongDTO;
import music.model.dto.UserDTO;

public class Service {
	private static Service instance = new Service();
	private Service() {};
	public static Service getInstance() {
		return instance;
	}
	
	private SongDAO songDAO = SongDAO.getInstance();
	private SingerDAO singerDAO = SingerDAO.getInstance();
	private UserDAO userDAO = UserDAO.getInstance();
	private ChartDAO chartDAO = ChartDAO.getInstance();
	
	public ArrayList<SongDTO> getSongList(String name) throws SQLException{
		return songDAO.getSongs(name);
	}
	
	public ArrayList<PrintSong> printSongList(String name) throws SQLException{
		return songDAO.printSongs(name);
	}
	
	public boolean addSong(SongDTO song) throws SQLException {
		return songDAO.addSong(song);
	}
	public void addSongsFromFile(String f) throws Exception{
		songDAO.addSongsFromFile(f);
	}
	public ArrayList<PrintSong> getSongListBySinger(int id) throws SQLException{
		return songDAO.getSongsBySinger(id);
	}
	
	
	////////////////////////////////Singer
	public ArrayList<SingerDTO> getSingerList(String name) throws SQLException{
		return singerDAO.getSingers(name);
	}
	
	public void addSingersFromFile(String f) throws Exception{
		singerDAO.addSingersFromFile(f);
	}
	
	public boolean addUser(UserDTO user) throws SQLException {
		return userDAO.addUser(user);
	}
	
	
	
	//////////즐겨찾기
	public boolean addMyList(int songId, int userId) throws SQLException {
		return chartDAO.addMyList(songId, userId);
	}
	public ArrayList<PrintSong> getMyList(int id) throws SQLException{
		return chartDAO.getMyList(id);
	}
	
	/////// 차트 업데이트
	public ArrayList<PrintSong> getNew() throws SQLException{
		return songDAO.getNew();
	}
}
