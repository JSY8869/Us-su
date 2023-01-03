package Us_su.MemoryDiary.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class GameDto {
    @JsonProperty("question")
    private String Question;
    @JsonProperty("answer")
    private String answer;
}
