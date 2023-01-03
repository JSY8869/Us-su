package Us_su.MemoryDiary.repository;

import Us_su.MemoryDiary.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {

    Optional<User> findByIdentifier(String username);

    Boolean existsByIdentifier(String identifier);

}
