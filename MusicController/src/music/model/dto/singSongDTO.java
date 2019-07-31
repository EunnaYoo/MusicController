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
		builder.append("곡번호 : ");
		builder.append(songId);
		builder.append(" , 곡제목 : ");
		builder.append(songName);
		builder.append(" , 가수이름 : ");
		builder.append(singerName);
		builder.append(" , 발매일 : ");
		builder.append(releaseDate);
		return builder.toString();
	}
}
