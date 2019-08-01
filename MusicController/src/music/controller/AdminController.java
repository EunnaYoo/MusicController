package music.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import music.model.Service;
import music.model.dto.SingerDTO;
import music.model.dto.SongDTO;
import music.model.dto.singSongDTO;
import music.view.EndView;

public class AdminController {

	private static AdminController instance = new AdminController();
	private AdminController(){};
	public static AdminController getInstance() {
		return instance;
	}

	private static Service service = Service.getInstance();

	static Scanner sc = new Scanner(System.in);

	// 노래, 가수 데이터 저장
	public void addSongFromFile(String f) {
		try {
			service.addSongsFromFile(f);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void addSingersFromFile(String f) {
		try {
			service.addSingersFromFile(f);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 곡목 검색
	public void getSongList(String name) {
		try {
			ArrayList<singSongDTO> songList = service.getSongList(name);
			EndView.showSongList(songList);
			EndView.message("곡 번호를 입력해주세요");
			singSongDTO aim = songList.get(sc.nextInt() - 1);
			EndView.watchMovie(aim.getSongName(), aim.getSingerName());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// 가수로 노래 검색
	public void getSingerList(String name) {
		try {
			ArrayList<SingerDTO> singerList = service.getSingerList(name);
			EndView.showSongList(singerList);
			EndView.message("곡 목록을 보고 싶은 가수의 번호를 선택해 주세요");
			SingerDTO aim = singerList.get(sc.nextInt() - 1);

			ArrayList<singSongDTO> songList = service.getSongListBySinger(aim.getId());
			EndView.showSongList(service.getSongListBySinger(aim.getId()));

			EndView.message("곡 번호를 입력해주세요");
			singSongDTO aim2 = songList.get(sc.nextInt() - 1);
			EndView.watchMovie(aim2.getSongName(), aim2.getSongName());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// 노래 저장
	public void addSong(SongDTO song) {
		try {
			boolean result = service.addSong(song);
			if (result == false) {
				EndView.failView("저장실패");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
