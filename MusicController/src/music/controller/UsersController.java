package music.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import org.apache.log4j.Logger;

import music.model.dto.PrintSongDTO;
import music.model.dto.SingerDTO;
import music.service.Service;
import music.view.EndView;

public class UsersController {
	
	private static Logger logger = Logger.getLogger(UsersController.class.toString());
	
	private static UsersController instance = new UsersController();
	private UsersController(){};
	public static UsersController getInstance() {
		return instance;
	}

	private static Service service = Service.getInstance();

	static Scanner sc = new Scanner(System.in);

	// ��� �˻�
	public void printSongList(String name, String logInId) {
		logger.info("printSongList "+name);
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
				logger.info("listen to "+aim.getId());
				service.addPopularity(aim.getId());
				EndView.watchMovie(aim.getName(), aim.getSinger());
			} else if (req == 2) {
				logger.info("add "+aim.getId()+" into my list");
				service.addMyList(aim.getId(), logInId);
			} else if (req == 3) {
				logger.info("listen and add "+aim.getId()+" into my list");
				service.addPopularity(aim.getId());
				service.addMyList(aim.getId(), logInId);
				EndView.watchMovie(aim.getName(), aim.getSinger());
			} else {
				EndView.failView("�߸��� �Է°��Դϴ�.");
			}
			
		} catch (Exception e) {
			logger.warn(e);
			e.printStackTrace();
		}
	}

	// �����˻�
	public void getSingerList(String name) {
		logger.info("getSingerList with name "+name);
		try {
			ArrayList<SingerDTO> singerList = service.getSingerList(name);
			EndView.showSongList(singerList);
			
			EndView.Message("�� ����� ���� ���� ������ ��ȣ�� ������ �ּ���");
			SingerDTO aim = singerList.get(sc.nextInt() - 1);
			logger.info("select singer "+aim.getId());
			
			ArrayList<PrintSongDTO> songList = service.getSongListBySinger(aim.getId());
			EndView.showSongList(songList);
			
			ArrayList<Integer> indexList = new ArrayList<>();
			for (PrintSongDTO eachSong : songList) {
				indexList.add(eachSong.getId());
			}
			
			EndView.Message("�� ��ȣ�� �Է����ּ���");
			PrintSongDTO aim2 = songList.get(indexList.indexOf(sc.nextInt()));
			
			logger.info("select song "+aim2);
			
			service.addPopularity(aim2.getId());
			EndView.watchMovie(aim2.getName(), aim2.getSinger());
		} catch (Exception e) {
			logger.warn(e);
			e.printStackTrace();
		}
	}

	public void addMyList(int songId, String userId) throws SQLException {
		boolean result = service.addMyList(songId, userId);
		logger.info("add "+songId+" into my list of "+userId);
		if (result) {
			logger.warn("success to add");
			EndView.successView("�߰� ����");
		} else {
			logger.warn("succeed to add");
			EndView.failView("�߰� ����");
		}
	}

	public void getMyList(String id) {
		try {
			logger.info(id + "try to watch his/her list");
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
				
				logger.info("select song "+aim.getId());
				service.addPopularity(aim.getId());
				EndView.watchMovie(aim.getName(), aim.getSinger());
			} else if (req == 2) {
				return ;
			} else {
				logger.warn("fail to find song");
				EndView.failView("�߸��� �Է°��Դϴ�.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void getNew() {
		logger.info("getNew");
		try {
			ArrayList<PrintSongDTO> songList = service.getNew();
			EndView.showSongList(songList);
			
			EndView.Message("�� ��ȣ�� �Է����ּ���");
			PrintSongDTO aim = songList.get(sc.nextInt() - 1);
			
			EndView.Message("1.���\t2.�� ��Ͽ� �߰�\t3.�� ��Ͽ� �߰� �� ���");
			
			int req = sc.nextInt();
			if (req == 1) {
				logger.info("select song "+aim.getId());
				EndView.watchMovie(aim.getName(), aim.getSinger());
			} else if (req == 2) {
				EndView.Message("ID�� �Է����ּ���");
				String userId=sc.next();
				logger.info("song "+aim.getId()+" into list of "+userId);
				service.addMyList(aim.getId(), userId);
			} else if (req == 3) {
				EndView.Message("ID�� �Է����ּ���");
				String userId=sc.next();
				service.addMyList(aim.getId(), userId);
				
				logger.info("song "+aim.getId()+" into list of "+userId);

				EndView.watchMovie(aim.getName(), aim.getSinger());
			} else {
				logger.warn("fail to find song");
				EndView.failView("�߸��� �Է°��Դϴ�.");
			}
			
		} catch (SQLException e) {
			logger.warn("fail to get new songs");
			EndView.failView("�ֽ���Ʈ �˻� ����");
			e.printStackTrace();
		}
	}

	public void getPopular() {
		logger.info("getPopular");
		try {
			ArrayList<PrintSongDTO> songList = service.getPopular();
			EndView.showSongList(songList);
			
			EndView.Message("�� ��ȣ�� �Է����ּ���");
			PrintSongDTO aim = songList.get(sc.nextInt() - 1);
			
			EndView.Message("1.���\t2.�� ��Ͽ� �߰�\t3.�� ��Ͽ� �߰� �� ���");
			
			int req = sc.nextInt();
			if (req == 1) {
				logger.info("select song "+aim.getId());
				EndView.watchMovie(aim.getName(), aim.getSinger());
			} else if (req == 2) {
				EndView.Message("ID�� �Է����ּ���");
				String userId=sc.next();
				logger.info("song "+aim.getId()+" into list of "+userId);
				service.addMyList(aim.getId(), userId);
			} else if (req == 3) {
				EndView.Message("ID�� �Է����ּ���");
				String userId=sc.next();
				service.addMyList(aim.getId(), userId);
				
				logger.info("song "+aim.getId()+" into list of "+userId);

				EndView.watchMovie(aim.getName(), aim.getSinger());
			} else {
				logger.warn("fail to find song");
				EndView.failView("�߸��� �Է°��Դϴ�.");
			}
			
		} catch (SQLException e) {
			logger.warn("fail to find song");
			e.printStackTrace();
		}
	}
}
