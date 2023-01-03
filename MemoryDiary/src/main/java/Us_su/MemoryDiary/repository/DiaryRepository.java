package Us_su.MemoryDiary.repository;

import Us_su.MemoryDiary.domain.Diary;
import Us_su.MemoryDiary.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface DiaryRepository extends JpaRepository<Diary, Long> {

    Optional<Diary> getDiaryByUserAndCreatedAt(User user, LocalDate date);

    void deleteByUserAndCreatedAt(User user, LocalDate createdAt);
}
