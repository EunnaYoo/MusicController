package music.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import org.apache.log4j.Logger;

import music.model.dto.PrintSongDTO;
import music.model.dto.SingerDTO;
import music.model.dto.SongDTO;
import music.model.dto.UserDTO;
import music.service.Service;
import music.view.EndView;

public class AdminController {
	
	private static Logger logger = Logger.getLogger(AdminController.class.toString());
	private static AdminController instance = new AdminController();
	private AdminController(){};
	public static AdminController getInstance() {
		return instance;
	}
	
	private static Service service = Service.getInstance();

	static Scanner sc = new Scanner(System.in);
	
	//곡목 검색
	public void printSongList(String name) {

		try {
			ArrayList<PrintSongDTO> songList = service.printSongList(name);
			EndView.showSongList(songList);

			ArrayList<Integer> indexList = new ArrayList<>();
			for (PrintSongDTO eachSong : songList) {
				indexList.add(eachSong.getId());
			}

			EndView.Message("곡 번호를 입력해주세요");
			PrintSongDTO aim = songList.get(indexList.indexOf(sc.nextInt()));

			EndView.Message("1.듣기\t2.삭제\t0.종료");
			
			int req = sc.nextInt();
			if (req == 1) {
				EndView.watchMovie(aim.getName(), aim.getSinger());
			} else if(req==2){
				service.deleteSong(aim.getId());
			} else if(req==0) {
				return ;
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

			EndView.Message("곡 번호를 입력해주세요");
			PrintSongDTO aim2 = songList.get(sc.nextInt() - 1);
			EndView.watchMovie(aim2.getName(), aim2.getSinger());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

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

	public void addSongFromFile(String f) {
		try {
			service.addSongsFromFile(f);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void addSinger(SingerDTO singer) {
		
		try {
			boolean result = service.addSinger(singer);
			
			if (result == false) {
				EndView.failView("저장실패");
			}
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

	public void addUser(UserDTO user) {
		
		boolean result;
		
		try {
			result = service.addUser(user);
			
			if (result) {
				EndView.successView("등록되었습니다.");
			} else {
				EndView.failView("등록실패");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


	public void addNewSong(String date) {
		
		boolean result;
		
		try {
			result = service.addNewSong(date);
			
			if (result) {
				EndView.successView("최신차트 갱신 성공");
			} else {
				EndView.failView("최신차트 갱신 실패");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void updatePopularChart() {
		
		try {
			boolean result = service.updatePopularChart();
			
			if (result) {
				EndView.successView("인기차트 갱신");
			} else {
				EndView.failView("인기차트 갱신 실패");
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
			} else {
				EndView.failView("잘못된 입력값입니다.");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
