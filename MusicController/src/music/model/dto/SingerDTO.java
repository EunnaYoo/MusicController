package music.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data@NoArgsConstructor@AllArgsConstructor
public class SingerDTO {
	
	private int id;
	private String name;
	
	public String toString() {
		
		StringBuilder builder = new StringBuilder();
		
		builder.append("�̸� : ");
		builder.append(name);
		
		return builder.toString();
	}
}
