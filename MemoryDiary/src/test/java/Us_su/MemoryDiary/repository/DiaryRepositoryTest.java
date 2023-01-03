package Us_su.MemoryDiary.repository;

import Us_su.MemoryDiary.domain.Diary;
import Us_su.MemoryDiary.domain.RoleType;
import Us_su.MemoryDiary.domain.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.NoSuchElementException;

@SpringBootTest
@Transactional
@Profile("test")
class DiaryRepositoryTest {

    @Autowired
    UserRepository userRepository;
    @Autowired
    DiaryRepository diaryRepository;

    private String testId = "testId12";
    private String testPassword = "testPass12!";
    private LocalDate testDate = LocalDate.now();
    private String testContent = "나는 오늘 학교에 갔다. 그리고 집에 왔다.";

    @BeforeEach
    void beforeEach() {
        userRepository.save(User.builder()
                .identifier(testId)
                .password(testPassword)
                .role(RoleType.USER)
                .score(0L).build());
    }

    @Test
    void getDiaryByUserAndCreatedAt() {
        //given
        diaryRepository.save(Diary.builder()
                .user(userRepository.findByIdentifier(testId).get())
                .createdAt(testDate)
                .isPlayed1(false)
                .isPlayed2(false)
                .id(1L)
                .contents(testContent)
                .build());
        //when
        Diary diary = diaryRepository.getDiaryByUserAndCreatedAt(
                userRepository.findByIdentifier(testId).get(), testDate).get();
        //then
        Assertions.assertThat(diary.getContents()).isEqualTo(testContent);
    }

    @Test
    void deleteByUserAndCreatedAt() {
        //given
        diaryRepository.save(Diary.builder()
                .user(userRepository.findByIdentifier(testId).get())
                .createdAt(testDate)
                .isPlayed1(false)
                .isPlayed2(false)
                .id(1L)
                .contents(testContent)
                .build());
        //when
        diaryRepository.deleteByUserAndCreatedAt(userRepository.findByIdentifier(testId).get(), testDate);
        //then
        Assertions.assertThatThrownBy(() -> diaryRepository.getDiaryByUserAndCreatedAt(
                userRepository.findByIdentifier(testId).get(), testDate
        ).get()).isInstanceOf(Exception.class);
    }
}