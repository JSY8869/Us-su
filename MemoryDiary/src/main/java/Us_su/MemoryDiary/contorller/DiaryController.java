package Us_su.MemoryDiary.contorller;

import Us_su.MemoryDiary.domain.Diary;
import Us_su.MemoryDiary.dto.DiaryDto;
import Us_su.MemoryDiary.dto.DiaryRDDto;
import Us_su.MemoryDiary.service.DiaryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@Slf4j
@RequiredArgsConstructor
public class DiaryController {

    private final DiaryService diaryService;

    @PostMapping("/diary/create")
    public ResponseEntity<Void> createDiary(@RequestBody DiaryDto diaryDto) {
        diaryService.saveDiary(diaryDto.getIdentifier(), diaryDto.getCreateAt(), diaryDto.getContent());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/diary/read")
    public String getDiary(@RequestBody DiaryRDDto diaryReadDto) {
        Diary diary = diaryService.getDiary(diaryReadDto.getIdentifier(), diaryReadDto.getCreateAt());
        return diary.getContents();
    }

    @PostMapping("/diary/delete")
    public ResponseEntity<Void> deleteDiary(@RequestBody DiaryRDDto diaryDeleteDto) {
        diaryService.deleteDiary(diaryDeleteDto.getIdentifier(), diaryDeleteDto.getCreateAt());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/diary/update")
    public ResponseEntity<Void> updateDiary(@RequestBody DiaryDto diaryDto) {
        diaryService.updateDiary(diaryDto.getIdentifier(), diaryDto.getCreateAt(), diaryDto.getContent());
        return ResponseEntity.ok().build();
    }
}
