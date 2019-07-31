package music.view;

import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class EndView {

	public static void watchMovie(String song, String singer) {

		Document doc = null;

		String address = "https://www.youtube.com/results?search_query=";
		try {
			doc = Jsoup.connect(address + song + "+" + singer).get();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// +노래방으로 검색할까?????????
		String finalAddress = doc.select("h3 > a").get(0).attr("href");
		try {
			Runtime.getRuntime().exec(new String[] { "cmd", "/c", "start chrome https://www.youtube.com" + finalAddress });
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void showSongList(ArrayList songList) {
		int length = songList.size();
		if (length != 0) {
			for (int index = 0; index < length; index++) {
				System.out.println("목록 " + (index + 1) + " - " + songList.get(index));
			}
		} else {
			System.out.println("없엉");
		}
	}

	public static void failView(String s) {
		System.out.println(s);
	}

	public static void message(String m) {
		System.out.println(m);
	}
}
