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
	
	//��� �˻�
//	public static void getSongList(String name) {
//		try {
//			ArrayList<SongDTO> songList = service.getSongList(name);
//			EndView.showSongList(songList);
//			EndView.message("�� ��ȣ�� �Է����ּ���");
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
			EndView.message("�� ��ȣ�� �Է����ּ���");
			PrintSong aim = songList.get(indexList.indexOf(sc.nextInt()));
			EndView.message("1.��� 2.�� ��Ͽ� �߰� 3.�� ��Ͽ� �߰� �� ���");
			int req = sc.nextInt();
			if(req == 1) {
				service.addPopularity(aim.getId());
				EndView.watchMovie(aim.getName(), aim.getSinger());
			}else if(req ==2) {
				EndView.message("ID�� �Է����ּ���");
				service.addMyList(aim.getId(), sc.nextInt());
			}else if(req == 3) {
				EndView.message("ID�� �Է����ּ���");
				service.addPopularity(aim.getId());
				service.addMyList(aim.getId(), sc.nextInt());
				EndView.watchMovie(aim.getName(), aim.getSinger());
			}else {
				EndView.failView("1, 2, 3 �߿��� �Է��϶��...");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//�����˻�
	public void getSingerList(String name) {
		try {
			ArrayList<SingerDTO> singerList = service.getSingerList(name);
			EndView.showSongList(singerList);
			EndView.message("�� ����� ���� ���� ������ ��ȣ�� ������ �ּ���");
			SingerDTO aim = singerList.get(sc.nextInt()-1);
			
			ArrayList<PrintSong> songList = service.getSongListBySinger(aim.getId());
			EndView.showSongList(songList);
			
			EndView.message("�� ��ȣ�� �Է����ּ���");
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
				EndView.successView("��ϵǾ����ϴ�.");
			}else {
				EndView.failView("��Ͻ���");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void addMyList(int songId, int userId) throws SQLException {
		boolean result =  service.addMyList(songId, userId);;
		if(result) {
			EndView.successView("�߰� ����");
		}else {
			EndView.failView("�߰� ����");
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
			EndView.message("�� ��ȣ�� �Է����ּ���");
			PrintSong aim = songList.get(sc.nextInt()-1);
			EndView.message("1.��� 2.�� ��Ͽ� �߰� 3.�� ��Ͽ� �߰� �� ���");
			int req = sc.nextInt();
			if(req == 1) {
				EndView.watchMovie(aim.getName(), aim.getSinger());
			}else if(req ==2) {
				EndView.message("ID�� �Է����ּ���");
				service.addMyList(aim.getId(), sc.nextInt());
			}else if(req == 3) {
				EndView.message("ID�� �Է����ּ���");
				service.addMyList(aim.getId(), sc.nextInt());
				EndView.watchMovie(aim.getName(), aim.getSinger());
			}else {
				EndView.failView("1, 2, 3 �߿��� �Է��϶��...");
			}
		} catch (SQLException e) {
			EndView.failView("�ֽ���Ʈ �˻� ����");
			e.printStackTrace();
		}
	}
	public void getPopular() {
		try {
			ArrayList<PrintSong> songList =  service.getPopular();
			EndView.showSongList(songList);
			EndView.message("�� ��ȣ�� �Է����ּ���");
			PrintSong aim = songList.get(sc.nextInt()-1);
			EndView.message("1.��� 2.�� ��Ͽ� �߰� 3.�� ��Ͽ� �߰� �� ���");
			int req = sc.nextInt();
			if(req == 1) {
				EndView.watchMovie(aim.getName(), aim.getSinger());
			}else if(req ==2) {
				EndView.message("ID�� �Է����ּ���");
				service.addMyList(aim.getId(), sc.nextInt());
			}else if(req == 3) {
				EndView.message("ID�� �Է����ּ���");
				service.addMyList(aim.getId(), sc.nextInt());
				EndView.watchMovie(aim.getName(), aim.getSinger());
			}else {
				EndView.failView("1, 2, 3 �߿��� �Է��϶��...");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
