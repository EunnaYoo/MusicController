package music.view;

import music.controller.AdminController;
import music.controller.UsersController;

public class StartView {
	private static AdminController controller = AdminController.getInstance();
	private static UsersController controller2 = UsersController.getInstance();
	
	public static void main(String[] args) {
		controller2.printSongList("가지 마");
//		controller.updatePopularChart();
//		controller.getMyList(1);
//		controller.addUser(new UserDTO(1,"최성국","0000"));
//		controller.getSingerList("나");
//		controller.addSingersFromFile("C:\\Users\\Playdata\\Desktop\\git\\MusicController\\singerList.txt");
//		controller.addSongFromFile("C:\\Users\\Playdata\\Desktop\\git\\MusicController\\songLista.txt");
//		controller2.getPopular();
//		controller.addNewSong("2019.05");
//		controller2.getNew();
	}
}
