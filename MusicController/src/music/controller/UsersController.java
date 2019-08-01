package music.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;
import music.model.Service;
import music.model.dto.SingerDTO;
import music.model.dto.SongDTO;
import music.model.dto.singSongDTO;
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
	public void getSongList(String name) {
		try {
			ArrayList<singSongDTO> songList = service.getSongList(name);
			EndView.showSongList(songList);
			EndView.message("�� ��ȣ�� �Է����ּ���");
			singSongDTO aim = songList.get(sc.nextInt()-1);
			EndView.watchMovie(aim.getSongName(), aim.getSingerName());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// ������ �뷡 �˻�
	public void getSingerList(String name) {
		try {
			ArrayList<SingerDTO> singerList = service.getSingerList(name);
			EndView.showSongList(singerList);
			EndView.message("�� ����� ���� ���� ������ ��ȣ�� ������ �ּ���");
			SingerDTO aim = singerList.get(sc.nextInt()-1);
			
			ArrayList<singSongDTO> songList = service.getSongListBySinger(aim.getId());
			EndView.showSongList(service.getSongListBySinger(aim.getId()));
			
			EndView.message("�� ��ȣ�� �Է����ּ���");
			singSongDTO aim2 = songList.get(sc.nextInt()-1);
			EndView.watchMovie(aim2.getSongName(), aim2.getSongName());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
