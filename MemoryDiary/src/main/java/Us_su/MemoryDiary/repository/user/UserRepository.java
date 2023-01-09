package Us_su.MemoryDiary.repository.user;

import Us_su.MemoryDiary.domain.User;
import Us_su.MemoryDiary.dto.RegisterForm;

public interface UserRepository {

    User findByIdentifier(String username);

    Boolean existsByIdentifier(String identifier);

    void save(RegisterForm form);
}
