package music.view;

import java.sql.SQLException;

import music.controller.UsersController;
import music.model.dto.UserDTO;

public class StartView {
	private static UsersController controller = UsersController.getInstance();
	
	public static void main(String[] args) {
//		controller.printSongList("���� ��");
		
		controller.getMyList(1);
//		controller.addUser(new UserDTO(1,"�ּ���","0000"));
//		controller.getSingerList("��");
//		controller.addSingersFromFile("C:\\Users\\Playdata\\Desktop\\git\\MusicController\\singerList.txt");
//		controller.addSongFromFile("C:\\Users\\Playdata\\Desktop\\git\\MusicController\\songLista.txt");
		
		
	}
}
