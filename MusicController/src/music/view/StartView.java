package music.view;

import music.controller.UsersController;

public class StartView {

	public static void main(String[] args) {

		UsersController controller = UsersController.getInstance();
		
//		controller.addSingersFromFile("C:\\0.encore\\01.Java\\singerList.txt");
//		controller.addSongFromFile("C:\\0.encore\\01.Java\\songLista.txt");

		controller.getSongList("���� ��");
		controller.getSingerList("��");
	}
}
