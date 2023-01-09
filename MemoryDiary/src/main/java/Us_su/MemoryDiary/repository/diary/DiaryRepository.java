package Us_su.MemoryDiary.repository.diary;

import Us_su.MemoryDiary.domain.Diary;
import Us_su.MemoryDiary.domain.User;

import java.time.LocalDate;

public interface DiaryRepository {
    void save(User user, LocalDate createdAt, String contents);
    Diary getDiaryByUserAndCreatedAt(User user, LocalDate createdAt);

    void deleteByUserAndCreatedAt(User user, LocalDate createdAt);
}
