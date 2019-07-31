package music.controller;

import java.util.ArrayList;
import java.util.Scanner;

import music.model.Service;
import music.model.dto.PrintSong;
import music.model.dto.SingerDTO;
import music.model.dto.SongDTO;
import music.view.EndView;

public class Controller {
	private static Controller instance = new Controller();
	private Controller() {};
	public static Controller getInstance() {
		return instance;
	}
	
	private static Service service = Service.getInstance();

	static Scanner sc = new Scanner(System.in);
	
	//곡목 검색
	public static void getSongList(String name) {
		try {
			ArrayList<SongDTO> songList = service.getSongList(name);
			EndView.showSongList(songList);
			EndView.message("곡 번호를 입력해주세요");
			SongDTO aim = songList.get(sc.nextInt()-1);
			EndView.watchMovie(aim.getName(), aim.getSinger());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void printSongList(String name) {
		try {
			ArrayList<PrintSong> songList = service.printSongList(name);
			EndView.showSongList(songList);
			EndView.message("곡 번호를 입력해주세요");
			PrintSong aim = songList.get(sc.nextInt()-1);
			EndView.watchMovie(aim.getName(), aim.getSinger());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//가수검색
	public static void getSingerList(String name) {
		try {
			ArrayList<SingerDTO> singerList = service.getSingerList(name);
			EndView.showSongList(singerList);
			EndView.message("곡 목록을 보고 싶은 가수의 번호를 선택해 주세요");
			SingerDTO aim = singerList.get(sc.nextInt()-1);
			
			ArrayList<PrintSong> songList = service.getSongListBySinger(aim.getId());
			EndView.showSongList(songList);
			
			EndView.message("곡 번호를 입력해주세요");
			PrintSong aim2 = songList.get(sc.nextInt()-1);
			EndView.watchMovie(aim2.getName(), aim2.getSinger());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void addSong(SongDTO song) {
		try {
			boolean result = service.addSong(song);
			if(result ==false) {
				EndView.failView("저장실패");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void addSongFromFile(String f) throws Exception{
		service.addSongsFromFile(f);
	}
	public void addSingersFromFile(String f) throws Exception{
		service.addSingersFromFile(f);
	}
	
	public String getNameFromId(int id) throws Exception{
		return service.getNameFromId(id);
	}
}
