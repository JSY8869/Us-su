package Us_su.MemoryDiary.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDate;

@Data
public class DiaryRDDto {
    @JsonProperty("identifier")
    private String identifier;
    @JsonProperty("createdAt")
    private LocalDate createAt;
}
