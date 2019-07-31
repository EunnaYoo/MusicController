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
		builder.append("���� : ");
		builder.append(name);
		builder.append(" , ���� : ");
		builder.append(singer);
		builder.append(" , �߸��� : ");
		builder.append(date);
		return builder.toString();
		
	}
}