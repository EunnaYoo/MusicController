package music.view;

import music.controller.Controller;

public class StartView {
	private static Controller controller = Controller.getInstance();
	
	public static void main(String[] args) {
//		controller.getSongList("°¡Áö ¸¶");

//		controller.getSingerList("À±");
		try {
			controller.addSingersFromFile("C:\\0.encore\\01.java\\step11_jsoupTest\\singerList.txt");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
