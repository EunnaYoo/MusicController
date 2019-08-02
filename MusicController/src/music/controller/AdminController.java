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
	
	//��� �˻�
	public void printSongList(String name) {

		try {
			ArrayList<PrintSongDTO> songList = service.printSongList(name);
			EndView.showSongList(songList);

			ArrayList<Integer> indexList = new ArrayList<>();
			for (PrintSongDTO eachSong : songList) {
				indexList.add(eachSong.getId());
			}

			EndView.Message("�� ��ȣ�� �Է����ּ���");
			PrintSongDTO aim = songList.get(indexList.indexOf(sc.nextInt()));

			EndView.Message("1.���\t2.����\t0.����");
			
			int req = sc.nextInt();
			if (req == 1) {
				EndView.watchMovie(aim.getName(), aim.getSinger());
			} else if(req==2){
				service.deleteSong(aim.getId());
			} else if(req==0) {
				return ;
			} else {
				EndView.failView("�߸��� �Է°��Դϴ�.");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// �����˻�
	public void getSingerList(String name) {
		
		try {
			ArrayList<SingerDTO> singerList = service.getSingerList(name);
			
			EndView.showSongList(singerList);
			EndView.Message("�� ����� ���� ���� ������ ��ȣ�� ������ �ּ���");
			
			SingerDTO aim = singerList.get(sc.nextInt() - 1);
			ArrayList<PrintSongDTO> songList = service.getSongListBySinger(aim.getId());
			EndView.showSongList(songList);

			EndView.Message("�� ��ȣ�� �Է����ּ���");
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
				EndView.failView("�������");
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
				EndView.failView("�������");
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
				EndView.successView("��ϵǾ����ϴ�.");
			} else {
				EndView.failView("��Ͻ���");
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
				EndView.successView("�ֽ���Ʈ ���� ����");
			} else {
				EndView.failView("�ֽ���Ʈ ���� ����");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void updatePopularChart() {
		
		try {
			boolean result = service.updatePopularChart();
			
			if (result) {
				EndView.successView("�α���Ʈ ����");
			} else {
				EndView.failView("�α���Ʈ ���� ����");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void getNew() {
		try {
			ArrayList<PrintSongDTO> songList = service.getNew();
			EndView.showSongList(songList);
			
			EndView.Message("�� ��ȣ�� �Է����ּ���");
			PrintSongDTO aim = songList.get(sc.nextInt() - 1);
			
			EndView.Message("1.���\t2.�� ��Ͽ� �߰�\t3.�� ��Ͽ� �߰� �� ���");
			
			int req = sc.nextInt();
			if (req == 1) {
				EndView.watchMovie(aim.getName(), aim.getSinger());
			} else {
				EndView.failView("�߸��� �Է°��Դϴ�.");
			}
			
		} catch (SQLException e) {
			EndView.failView("�ֽ���Ʈ �˻� ����");
			e.printStackTrace();
		}
	}
	
	public void getPopular() {

		try {
			ArrayList<PrintSongDTO> songList = service.getPopular();
			EndView.showSongList(songList);

			EndView.Message("�� ��ȣ�� �Է����ּ���");
			PrintSongDTO aim = songList.get(sc.nextInt() - 1);

			EndView.Message("1.���\t2.�� ��Ͽ� �߰�\t3.�� ��Ͽ� �߰� �� ���");

			int req = sc.nextInt();
			if (req == 1) {
				EndView.watchMovie(aim.getName(), aim.getSinger());
			} else {
				EndView.failView("�߸��� �Է°��Դϴ�.");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
