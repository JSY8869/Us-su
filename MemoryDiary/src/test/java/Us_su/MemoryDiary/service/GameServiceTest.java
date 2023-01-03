//package Us_su.MemoryDiary.service;
//
//import Us_su.MemoryDiary.domain.RoleType;
//import Us_su.MemoryDiary.domain.User;
//import Us_su.MemoryDiary.repository.UserRepository;
//import org.junit.jupiter.api.*;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.time.LocalDate;
//
//@SpringBootTest
//@Transactional
//@TestInstance(TestInstance.Lifecycle.PER_CLASS)
//class GameServiceTest {
//
//    @Autowired
//    private GameService gameService;
//    @Autowired
//    private UserRepository userRepository;
//    @Autowired
//    private DiaryService diaryService;
//
//    private final static LocalDate testDate = LocalDate.of(2022,12,22);
//    private final static User testId = User.builder()
//            .score(0L)
//            .identifier("testId")
//            .password("123456")
//            .role(RoleType.USER)
//            .build();

//    @BeforeAll
//    void beforeAll() {
//        userRepository.save(testId);
//
//        diaryService.saveDiary(1L, testDate, "나는 학교에 갔다. 그리고 영화관에 갔다.");
//    }

//    @Test
//    void getGame1(){
//        gameService.getGame1QnA(1L, testDate);
//    }
//
//    @Test
//    void getGame2(){
//        gameService.getGame2QnA(1L, testDate);
//    }
//}