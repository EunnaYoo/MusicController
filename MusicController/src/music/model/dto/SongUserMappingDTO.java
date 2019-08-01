package music.model.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SongUserMappingDTO {
	
	private int song_id;
	private int user_id;
}
