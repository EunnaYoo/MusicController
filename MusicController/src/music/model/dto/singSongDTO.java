package music.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class singSongDTO {
	
	private int songId;
	private String songName;
	private String singerName;
	private String releaseDate;
	
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("���ȣ : ");
		builder.append(songId);
		builder.append(" , ������ : ");
		builder.append(songName);
		builder.append(" , �����̸� : ");
		builder.append(singerName);
		builder.append(" , �߸��� : ");
		builder.append(releaseDate);
		return builder.toString();
	}
}
