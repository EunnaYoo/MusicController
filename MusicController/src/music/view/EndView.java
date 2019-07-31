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

//	public static void main(String[] args) {
//		Document doc = null;
//		String song="친구라도될걸그랬어";
//		String singer="거미";
//		String address="https://www.youtube.com/results?search_query=";
//		try {
//			doc = Jsoup.connect(address+song+"+"+singer).get();
////			Runtime.getRuntime().exec(new String[]{"cmd", "/c","start chrome https://www.youtube.com/watch?v=DDByv3z_z_U"});
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		Elements newsHeadlines = doc.select("h3 > a");
//		String finalAddress = doc.select("h3 > a").get(0).attr("href");
//		System.out.println(finalAddress);
//		//		for (Element e: newsHeadlines) {
////			System.out.println(e);
////		}
//		watchMovie("사랑에 연습이 있었다면", "임재현");
//	}
}
