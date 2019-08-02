package music.view;

import java.util.Scanner;

import music.controller.AdminController;
import music.controller.UsersController;
import music.model.dto.SingerDTO;
import music.model.dto.SongDTO;
import music.model.dto.UserDTO;

public class StartView {
	
	public static void main(String[] args) {
		boolean req = false;
		
		Scanner sc = new Scanner(System.in);
		EndView.Message("id�� �Է����ּ���");
		String logInId = sc.next();
		if(logInId.equals("admin")) {
			AdminController controller = AdminController.getInstance();
			while(!req) {
				EndView.Message("1. �� �˻�\t2. ���� �˻�\t3. �� �߰�\t4. �� ���� �߰�\t"
						+ "5. ���� �߰�\n6.���� ���� �߰�\t7.���� �߰�\t8. �ֽ���Ʈ ����\t9. �α���Ʈ ����\t0. ����");
				EndView.Message("����� ����� ��ȣ�� �Է����ּ���");
				int reqNumber = sc.nextInt();
				if(reqNumber ==1) {
					EndView.Message("�� ���� �Է����ּ���");
					controller.printSongList(sc.next());
				}else if(reqNumber ==2) {
					EndView.Message("�������� �Է����ּ���");
					controller.getSingerList(sc.next());
				}else if(reqNumber ==3) {
					EndView.Message("�� ��ȣ");
					int id=sc.nextInt();
					EndView.Message("���");
					String name=sc.next();
					EndView.Message("������ȣ");
					int singer=sc.nextInt();
					EndView.Message("�߸���(yyyy.mm)");
					String date=sc.next();
					controller.addSong(new SongDTO(id,name,singer,date,0));
				}else if(reqNumber==4) {
					controller.addSongFromFile("C:\\Users\\Playdata\\Desktop\\git\\MusicController\\songLista.txt");
				}else if(reqNumber==5) {
					EndView.Message("���� ��ȣ");
					int id=sc.nextInt();
					EndView.Message("������");
					String name=sc.next();
					controller.addSinger(new SingerDTO(id,name));
				}else if(reqNumber==6) {
					controller.addSingersFromFile("C:\\Users\\Playdata\\Desktop\\git\\MusicController\\singerList.txt");
				}else if(reqNumber==7) {
					EndView.Message("id");
					String id=sc.next();
					EndView.Message("�̸�");
					String name=sc.next();
					EndView.Message("��й�ȣ");
					String password=sc.next();
					controller.addUser(new UserDTO(id,name,password));
				}else if(reqNumber==8) {
					EndView.Message("�߸���(yyyy.mm)");
					String date=sc.next();
					controller.addNewSong(date);
					controller.getNew();
				}else if(reqNumber==9) {
					controller.updatePopularChart();
					controller.getPopular();
				}else if(reqNumber==0){
					req=true;
				}else {
					EndView.failView("�߸��� ��û�Դϴ�");
				}
			}
			
		}else {
			UsersController controller = UsersController.getInstance();
			while(!req) {
				EndView.Message("1. �� �˻�\t2. ���� �˻�\t3. �� ���\t 4. �Ű�\t5. �α���Ʈ\n0. ����");
				int reqNumber=sc.nextInt();
				if(reqNumber==1) {
					EndView.Message("�� ���� �Է����ּ���");
					controller.printSongList(sc.next(),logInId);
				}else if(reqNumber==2) {
					EndView.Message("���� ���� �Է����ּ���");
					controller.getSingerList(sc.next());
				}else if(reqNumber==3) {
					controller.getMyList(logInId);
				}else if(reqNumber==4) {
					controller.getNew();
				}else if(reqNumber==5) {
					controller.getNew();
				}else if(reqNumber==0) {
					req=true;
				}else {
					EndView.Message("�߸��� ��û�Դϴ�");
				}
			}
		}
		
//		controller.updatePopularChart();
//		controller.getMyList(1);
//		controller.addUser(new UserDTO(1,"�ּ���","0000"));
//		controller.getSingerList("��");
//		controller.addSingersFromFile("C:\\Git\\MusicController\\singerList.txt");
//		controller.addSongFromFile("C:\\Git\\MusicController\\songLista.txt");
//		controller2.getPopular();
//		controller.addNewSong("2019.05");
//		controller2.getNew();
//		controller.printSongList("��");
	}
}
