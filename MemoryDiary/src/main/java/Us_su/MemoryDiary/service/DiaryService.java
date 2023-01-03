package Us_su.MemoryDiary.service;

import Us_su.MemoryDiary.domain.Diary;
import Us_su.MemoryDiary.domain.User;
import Us_su.MemoryDiary.repository.DiaryRepository;
import Us_su.MemoryDiary.repository.UserRepository;
import jakarta.persistence.NoResultException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@Slf4j
@RequiredArgsConstructor
public class DiaryService {

    private final DiaryRepository diaryRepository;
    private final UserRepository userRepository;

    public void saveDiary(String identifier, LocalDate createdAt, String contents) {
        diaryRepository.save(Diary.builder()
                .user(getUser(identifier))
                .createdAt(createdAt)
                .contents(contents)
                .isPlayed1(false)
                .isPlayed2(false)
                .build());
    }

    public Diary getDiary(String identifier, LocalDate selectedDate) {
        return diaryRepository.getDiaryByUserAndCreatedAt(getUser(identifier), selectedDate).orElseThrow(() -> new NoResultException());
    }

    public void deleteDiary(String identifier, LocalDate selectedDate) {
        diaryRepository.deleteByUserAndCreatedAt(getUser(identifier), selectedDate);
    }

    public void updateDiary(String identifier, LocalDate selectedDate, String contents) {
        diaryRepository.getDiaryByUserAndCreatedAt(
                getUser(identifier), selectedDate).orElseThrow(() -> new NoResultException())
                .setContents(contents);
    }

    private User getUser(String identifier) {
        return userRepository.findByIdentifier(identifier).orElseThrow(() -> new IllegalArgumentException());
    }
}
