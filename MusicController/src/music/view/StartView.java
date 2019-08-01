package music.view;

import java.sql.SQLException;

import music.controller.Controller;
import music.model.dto.UserDTO;

public class StartView {
	private static Controller controller = Controller.getInstance();
	
	public static void main(String[] args) {
//		controller.printSongList("���� ��");
		
		controller.addUser(new UserDTO(1,"�ּ���","0000"));
//		controller.getSingerList("��");
		try {
			controller.addSingersFromFile("C:\\Users\\Playdata\\Desktop\\git\\MusicController\\singerList.txt");
			controller.addSongFromFile("C:\\Users\\Playdata\\Desktop\\git\\MusicController\\songLista.txt");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
