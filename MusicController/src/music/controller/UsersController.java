package music.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import music.model.dto.PrintSongDTO;
import music.model.dto.SingerDTO;
import music.service.Service;
import music.view.EndView;

public class UsersController {

	private static UsersController instance = new UsersController();
	private UsersController(){};
	public static UsersController getInstance() {
		return instance;
	}

	private static Service service = Service.getInstance();

	static Scanner sc = new Scanner(System.in);

	// 곡목 검색
	public void printSongList(String name, String logInId) {
		
		try {
			ArrayList<PrintSongDTO> songList = service.printSongList(name);
			EndView.showSongList(songList);
			
			ArrayList<Integer> indexList = new ArrayList<>();
			for (PrintSongDTO eachSong : songList) {
				indexList.add(eachSong.getId());
			}
			
			EndView.Message("곡 번호를 입력해주세요");
			PrintSongDTO aim = songList.get(indexList.indexOf(sc.nextInt()));
			
			EndView.Message("1.듣기\t2.내 목록에 추가\t3.내 목록에 추가 후 듣기");
			
			int req = sc.nextInt();
			if (req == 1) {
				service.addPopularity(aim.getId());
				EndView.watchMovie(aim.getName(), aim.getSinger());
			} else if (req == 2) {
				service.addMyList(aim.getId(), logInId);
			} else if (req == 3) {
				service.addPopularity(aim.getId());
				service.addMyList(aim.getId(), logInId);
				EndView.watchMovie(aim.getName(), aim.getSinger());
			} else {
				EndView.failView("잘못된 입력값입니다.");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 가수검색
	public void getSingerList(String name) {
		
		try {
			ArrayList<SingerDTO> singerList = service.getSingerList(name);
			EndView.showSongList(singerList);
			
			EndView.Message("곡 목록을 보고 싶은 가수의 번호를 선택해 주세요");
			SingerDTO aim = singerList.get(sc.nextInt() - 1);

			ArrayList<PrintSongDTO> songList = service.getSongListBySinger(aim.getId());
			EndView.showSongList(songList);

			ArrayList<Integer> indexList = new ArrayList<>();
			for (PrintSongDTO eachSong : songList) {
				indexList.add(eachSong.getId());
			}
			
			EndView.Message("곡 번호를 입력해주세요");
			PrintSongDTO aim2 = songList.get(indexList.indexOf(sc.nextInt()));
			
			service.addPopularity(aim2.getId());
			EndView.watchMovie(aim2.getName(), aim2.getSinger());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void addMyList(int songId, String userId) throws SQLException {
		boolean result = service.addMyList(songId, userId);
		;
		if (result) {
			EndView.successView("추가 성공");
		} else {
			EndView.failView("추가 실패");
		}
	}

	public void getMyList(String id) {
		try {
			ArrayList<PrintSongDTO> songList = service.getMyList(id);
			EndView.showSongList(songList);
			ArrayList<Integer> indexList = new ArrayList<>();
			for (PrintSongDTO eachSong : songList) {
				indexList.add(eachSong.getId());
			}
			
			
			EndView.Message("1.듣기\t2.종료");
			
			int req = sc.nextInt();
			if (req == 1) {
				EndView.Message("곡 번호를 입력해주세요");
				PrintSongDTO aim = songList.get(indexList.indexOf(sc.nextInt()));
				service.addPopularity(aim.getId());
				EndView.watchMovie(aim.getName(), aim.getSinger());
			} else if (req == 2) {
				return ;
			} else {
				EndView.failView("잘못된 입력값입니다.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void getNew() {
		try {
			ArrayList<PrintSongDTO> songList = service.getNew();
			EndView.showSongList(songList);
			
			EndView.Message("곡 번호를 입력해주세요");
			PrintSongDTO aim = songList.get(sc.nextInt() - 1);
			
			EndView.Message("1.듣기\t2.내 목록에 추가\t3.내 목록에 추가 후 듣기");
			
			int req = sc.nextInt();
			if (req == 1) {
				EndView.watchMovie(aim.getName(), aim.getSinger());
			} else if (req == 2) {
				EndView.Message("ID를 입력해주세요");
				service.addMyList(aim.getId(), sc.next());
			} else if (req == 3) {
				EndView.Message("ID를 입력해주세요");
				service.addMyList(aim.getId(), sc.next());
				EndView.watchMovie(aim.getName(), aim.getSinger());
			} else {
				EndView.failView("잘못된 입력값입니다.");
			}
			
		} catch (SQLException e) {
			EndView.failView("최신차트 검색 실패");
			e.printStackTrace();
		}
	}

	public void getPopular() {
		
		try {
			ArrayList<PrintSongDTO> songList = service.getPopular();
			EndView.showSongList(songList);
			
			EndView.Message("곡 번호를 입력해주세요");
			PrintSongDTO aim = songList.get(sc.nextInt() - 1);
			
			EndView.Message("1.듣기\t2.내 목록에 추가\t3.내 목록에 추가 후 듣기");
			
			int req = sc.nextInt();
			if (req == 1) {
				EndView.watchMovie(aim.getName(), aim.getSinger());
			} else if (req == 2) {
				EndView.Message("ID를 입력해주세요");
				service.addMyList(aim.getId(), sc.next());
			} else if (req == 3) {
				EndView.Message("ID를 입력해주세요");
				service.addMyList(aim.getId(), sc.next());
				EndView.watchMovie(aim.getName(), aim.getSinger());
			} else {
				EndView.failView("잘못된 입력값입니다.");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
