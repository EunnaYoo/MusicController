package music.view;

import music.controller.AdminController;

public class StartView {

	public static void main(String[] args) {

		AdminController controller = AdminController.getInstance();
		
		controller.addSingersFromFile("C:\\0.encore\\01.Java\\singerList.txt");
		//controller.addSongFromFile("C:\\0.encore\\01.Java\\songLista.txt");

//		controller.getSongList("���� ��");
//		controller.getSingerList("��");
	}
}
