package music.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import music.model.Service;
import music.model.dto.PrintSong;
import music.model.dto.SingerDTO;
import music.model.dto.SongDTO;
import music.model.dto.UserDTO;
import music.view.EndView;

public class UsersController {
	private static UsersController instance = new UsersController();
	private UsersController() {};
	public static UsersController getInstance() {
		return instance;
	}
	
	private static Service service = Service.getInstance();

	static Scanner sc = new Scanner(System.in);
	
	//곡목 검색
//	public static void getSongList(String name) {
//		try {
//			ArrayList<SongDTO> songList = service.getSongList(name);
//			EndView.showSongList(songList);
//			EndView.message("곡 번호를 입력해주세요");
//			SongDTO aim = songList.get(sc.nextInt()-1);
//			EndView.watchMovie(aim.getName(), aim.getSinger());
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
	
	public void printSongList(String name) {
		try {
			ArrayList<PrintSong> songList = service.printSongList(name);
			EndView.showSongList(songList);
			ArrayList<Integer> indexList = new ArrayList<>();
			for (PrintSong eachSong : songList) {
				indexList.add(eachSong.getId());
			}
			EndView.message("곡 번호를 입력해주세요");
			PrintSong aim = songList.get(indexList.indexOf(sc.nextInt()));
			EndView.message("1.듣기 2.내 목록에 추가 3.내 목록에 추가 후 듣기");
			int req = sc.nextInt();
			if(req == 1) {
				service.addPopularity(aim.getId());
				EndView.watchMovie(aim.getName(), aim.getSinger());
			}else if(req ==2) {
				EndView.message("ID를 입력해주세요");
				service.addMyList(aim.getId(), sc.nextInt());
			}else if(req == 3) {
				EndView.message("ID를 입력해주세요");
				service.addPopularity(aim.getId());
				service.addMyList(aim.getId(), sc.nextInt());
				EndView.watchMovie(aim.getName(), aim.getSinger());
			}else {
				EndView.failView("1, 2, 3 중에서 입력하라고...");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//가수검색
	public void getSingerList(String name) {
		try {
			ArrayList<SingerDTO> singerList = service.getSingerList(name);
			EndView.showSongList(singerList);
			EndView.message("곡 목록을 보고 싶은 가수의 번호를 선택해 주세요");
			SingerDTO aim = singerList.get(sc.nextInt()-1);
			
			ArrayList<PrintSong> songList = service.getSongListBySinger(aim.getId());
			EndView.showSongList(songList);
			
			EndView.message("곡 번호를 입력해주세요");
			PrintSong aim2 = songList.get(sc.nextInt()-1);
			service.addPopularity(aim2.getId());
			EndView.watchMovie(aim2.getName(), aim2.getSinger());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void addUser(UserDTO user){
		boolean result;
		try {
			result = service.addUser(user);
			if(result) {
				EndView.successView("등록되었습니다.");
			}else {
				EndView.failView("등록실패");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void addMyList(int songId, int userId) throws SQLException {
		boolean result =  service.addMyList(songId, userId);;
		if(result) {
			EndView.successView("추가 성공");
		}else {
			EndView.failView("추가 실패");
		}
	}
	
	public void getMyList(int id){
		try {
			ArrayList<PrintSong> result = service.getMyList(id);
			EndView.showSongList(result);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	

	public void getNew() {
		try {
			ArrayList<PrintSong> songList = service.getNew();
			
			EndView.showSongList(songList);
			EndView.message("곡 번호를 입력해주세요");
			PrintSong aim = songList.get(sc.nextInt()-1);
			EndView.message("1.듣기 2.내 목록에 추가 3.내 목록에 추가 후 듣기");
			int req = sc.nextInt();
			if(req == 1) {
				EndView.watchMovie(aim.getName(), aim.getSinger());
			}else if(req ==2) {
				EndView.message("ID를 입력해주세요");
				service.addMyList(aim.getId(), sc.nextInt());
			}else if(req == 3) {
				EndView.message("ID를 입력해주세요");
				service.addMyList(aim.getId(), sc.nextInt());
				EndView.watchMovie(aim.getName(), aim.getSinger());
			}else {
				EndView.failView("1, 2, 3 중에서 입력하라고...");
			}
		} catch (SQLException e) {
			EndView.failView("최신차트 검색 실패");
			e.printStackTrace();
		}
	}
	public void getPopular() {
		try {
			ArrayList<PrintSong> songList =  service.getPopular();
			EndView.showSongList(songList);
			EndView.message("곡 번호를 입력해주세요");
			PrintSong aim = songList.get(sc.nextInt()-1);
			EndView.message("1.듣기 2.내 목록에 추가 3.내 목록에 추가 후 듣기");
			int req = sc.nextInt();
			if(req == 1) {
				EndView.watchMovie(aim.getName(), aim.getSinger());
			}else if(req ==2) {
				EndView.message("ID를 입력해주세요");
				service.addMyList(aim.getId(), sc.nextInt());
			}else if(req == 3) {
				EndView.message("ID를 입력해주세요");
				service.addMyList(aim.getId(), sc.nextInt());
				EndView.watchMovie(aim.getName(), aim.getSinger());
			}else {
				EndView.failView("1, 2, 3 중에서 입력하라고...");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
