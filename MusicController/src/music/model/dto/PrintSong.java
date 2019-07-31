package music.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data@AllArgsConstructor
public class PrintSong {
	private int id;
	private String name;
	private String singer;
	private String date;
	
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("제목 : ");
		builder.append(name);
		builder.append(" , 가수 : ");
		builder.append(singer);
		builder.append(" , 발매일 : ");
		builder.append(date);
		return builder.toString();
		
	}
}
