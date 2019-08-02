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

	// ��� �˻�
	public void printSongList(String name, String logInId) {
		
		try {
			ArrayList<PrintSongDTO> songList = service.printSongList(name);
			EndView.showSongList(songList);
			
			ArrayList<Integer> indexList = new ArrayList<>();
			for (PrintSongDTO eachSong : songList) {
				indexList.add(eachSong.getId());
			}
			
			EndView.Message("�� ��ȣ�� �Է����ּ���");
			PrintSongDTO aim = songList.get(indexList.indexOf(sc.nextInt()));
			
			EndView.Message("1.���\t2.�� ��Ͽ� �߰�\t3.�� ��Ͽ� �߰� �� ���");
			
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

			ArrayList<Integer> indexList = new ArrayList<>();
			for (PrintSongDTO eachSong : songList) {
				indexList.add(eachSong.getId());
			}
			
			EndView.Message("�� ��ȣ�� �Է����ּ���");
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
			EndView.successView("�߰� ����");
		} else {
			EndView.failView("�߰� ����");
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
			
			
			EndView.Message("1.���\t2.����");
			
			int req = sc.nextInt();
			if (req == 1) {
				EndView.Message("�� ��ȣ�� �Է����ּ���");
				PrintSongDTO aim = songList.get(indexList.indexOf(sc.nextInt()));
				service.addPopularity(aim.getId());
				EndView.watchMovie(aim.getName(), aim.getSinger());
			} else if (req == 2) {
				return ;
			} else {
				EndView.failView("�߸��� �Է°��Դϴ�.");
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
			} else if (req == 2) {
				EndView.Message("ID�� �Է����ּ���");
				service.addMyList(aim.getId(), sc.next());
			} else if (req == 3) {
				EndView.Message("ID�� �Է����ּ���");
				service.addMyList(aim.getId(), sc.next());
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
			} else if (req == 2) {
				EndView.Message("ID�� �Է����ּ���");
				service.addMyList(aim.getId(), sc.next());
			} else if (req == 3) {
				EndView.Message("ID�� �Է����ּ���");
				service.addMyList(aim.getId(), sc.next());
				EndView.watchMovie(aim.getName(), aim.getSinger());
			} else {
				EndView.failView("�߸��� �Է°��Դϴ�.");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
