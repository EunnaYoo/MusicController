package music.model;

import java.sql.SQLException;
import java.util.ArrayList;

import music.model.dto.SingerDTO;
import music.model.dto.SongDTO;
import music.model.dto.singSongDTO;

public class Service {
	
	private static Service instance = new Service();
	private Service() {};
	public static Service getInstance() {
		return instance;
	}
	
	private SongDAO songDAO = SongDAO.getInstance();
	private SingerDAO singerDAO = SingerDAO.getInstance();
	
<<<<<<< Updated upstream
	// song
	public ArrayList<SongDTO> getSongList(String name) throws SQLException{
=======
	public ArrayList<singSongDTO> getSongList(String name) throws SQLException{
>>>>>>> Stashed changes
		return songDAO.getSongs(name);
	}
	
	public boolean addSong(SongDTO song) throws SQLException {
		return songDAO.addSong(song);
	}
	
	public void addSongsFromFile(String f) throws Exception{
		songDAO.addSongsFromFile(f);
	}
<<<<<<< Updated upstream
	
	public ArrayList<SongDTO> getSongListBySinger(String name) throws SQLException{
		return songDAO.getSongsBySinger(name);
=======
	public ArrayList<singSongDTO> getSongListBySinger(int id) throws SQLException{
		return songDAO.getSongsBySinger(id);
>>>>>>> Stashed changes
	}
	
	
	// Singer
	public ArrayList<SingerDTO> getSingerList(String name) throws SQLException{
		return singerDAO.getSingers(name);
	}
	
	public void addSingersFromFile(String f) throws Exception{
		singerDAO.addSingersFromFile(f);
	}
	
	
}
