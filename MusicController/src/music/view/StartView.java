package music.view;

import music.controller.Controller;

public class StartView {

	public static void main(String[] args) {

		Controller controller = Controller.getInstance();

		controller.getSongList("���� ��");
		controller.getSingerList("��");
	}
}
