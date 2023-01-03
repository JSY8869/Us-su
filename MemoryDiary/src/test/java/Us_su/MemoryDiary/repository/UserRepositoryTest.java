package Us_su.MemoryDiary.repository;

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
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Profile("test")
@Transactional
class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    private String testId = "testId12";
    private String testPassword = "testPass12!";

    @BeforeEach
    void beforeEach() {
        userRepository.save(User.builder()
                .identifier(testId)
                .password(testPassword)
                .role(RoleType.USER)
                .score(0L).build());
    }

    @Test
    void findByIdentifier() {
        //given
        //when
        Optional<User> user = userRepository.findByIdentifier(testId);
        //then
        Assertions.assertThat(user.get().getIdentifier()).isEqualTo(testId);
    }

    @Test
    void existsByIdentifier() {
        //given
        //when
        Boolean exists = userRepository.existsByIdentifier(testId);
        //then
        Assertions.assertThat(exists).isTrue();
    }
}