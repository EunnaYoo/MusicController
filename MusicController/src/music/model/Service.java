package music.model;

import java.sql.SQLException;
import java.util.ArrayList;

import music.model.dto.PrintSong;
import music.model.dto.SingerDTO;
import music.model.dto.SongDTO;

public class Service {
	private static Service instance = new Service();
	private Service() {};
	public static Service getInstance() {
		return instance;
	}
	
	private SongDAO songDAO = SongDAO.getInstance();
	private SingerDAO singerDAO = SingerDAO.getInstance();
	
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
	public String getNameFromId(int id) throws Exception{
		return singerDAO.getNameFromId(id);
	}
}
