package Us_su.MemoryDiary.repository;

import Us_su.MemoryDiary.domain.RoleType;
import Us_su.MemoryDiary.domain.User;
import Us_su.MemoryDiary.repository.user.UserJpaRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@SpringBootTest
@Profile("test")
@Transactional
class UserRepositoryTest {

    @Autowired
    UserJpaRepository userRepository;

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