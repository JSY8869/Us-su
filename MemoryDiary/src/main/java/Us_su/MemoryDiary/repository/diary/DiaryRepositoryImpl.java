package Us_su.MemoryDiary.repository.diary;

import Us_su.MemoryDiary.domain.Diary;
import Us_su.MemoryDiary.domain.User;
import Us_su.MemoryDiary.exception.CustomException;
import Us_su.MemoryDiary.exception.ErrorCode;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@RequiredArgsConstructor
public class DiaryRepositoryImpl implements DiaryRepository{

    private final DiaryJpaRepository diaryJpaRepository;

    @Override
    public void save(User user, LocalDate createdAt, String contents) {
        diaryJpaRepository.save(Diary.builder()
                .user(user)
                .createdAt(createdAt)
                .contents(contents)
                .isPlayed1(false)
                .isPlayed2(false)
                .build());
    }

    @Override
    public Diary getDiaryByUserAndCreatedAt(User user, LocalDate createdAt) {
        return diaryJpaRepository.getDiaryByUserAndCreatedAt(user, createdAt)
                .orElseThrow(() -> new CustomException(ErrorCode.DIARY_NOT_FOUND));
    }

    @Override
    public void deleteByUserAndCreatedAt(User user, LocalDate createdAt) {
        diaryJpaRepository.deleteByUserAndCreatedAt(user, createdAt);
    }
}
