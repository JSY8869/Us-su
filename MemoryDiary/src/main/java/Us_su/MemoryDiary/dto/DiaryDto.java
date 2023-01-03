package Us_su.MemoryDiary.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDate;

@Data
public class DiaryDto {
    @JsonProperty("identifier")
    private String identifier;
    @JsonProperty("createdAt")
    private LocalDate createAt;
    @JsonProperty("content")
    private String content;
}
