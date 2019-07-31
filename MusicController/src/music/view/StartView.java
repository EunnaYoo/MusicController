package music.view;

import music.controller.Controller;

public class StartView {
	private static Controller controller = Controller.getInstance();
	
	public static void main(String[] args) {
//		controller.getSongList("°¡Áö ¸¶");

//		controller.getSingerList("À±");
		try {
			controller.addSingersFromFile("C:\\Users\\Playdata\\Desktop\\git\\MusicController\\singerList.txt");
			controller.addSongFromFile("C:\\Users\\Playdata\\Desktop\\git\\MusicController\\songLista.txt");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
