package music.view;

import music.controller.Controller;

public class StartView {

	public static void main(String[] args) {

<<<<<<< Updated upstream
		//controller.getSingerList("��");
		try {
			controller.addSingersFromFile("C:\\Git\\MusicController\\singerList.txt");
		} catch (Exception e) {
			e.printStackTrace();
		}
=======
		Controller controller = Controller.getInstance();

		controller.getSongList("���� ��");
		controller.getSingerList("��");
>>>>>>> Stashed changes
	}
}
