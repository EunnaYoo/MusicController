package music.view;

import music.controller.Controller;

public class StartView {
	private static Controller controller = Controller.getInstance();
	
	public static void main(String[] args) {
//		controller.getSongList("���� ��");

		//controller.getSingerList("��");
		try {
			controller.addSingersFromFile("C:\\Git\\MusicController\\singerList.txt");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
