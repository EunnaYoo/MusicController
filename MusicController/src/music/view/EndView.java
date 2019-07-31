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

		// +�뷡������ �˻��ұ�?????????
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
				System.out.println("��� " + (index + 1) + " - " + songList.get(index));
			}
		} else {
			System.out.println("����");
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
//		String song="ģ���󵵵ɰɱ׷���";
//		String singer="�Ź�";
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
//		watchMovie("����� ������ �־��ٸ�", "������");
//	}
}
