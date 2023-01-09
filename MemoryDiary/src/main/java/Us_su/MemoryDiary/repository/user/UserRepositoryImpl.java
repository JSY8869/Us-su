package Us_su.MemoryDiary.repository.user;

import Us_su.MemoryDiary.domain.User;
import Us_su.MemoryDiary.dto.RegisterForm;
import Us_su.MemoryDiary.exception.CustomException;
import Us_su.MemoryDiary.exception.ErrorCode;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository{

    private final UserJpaRepository userJpaRepository;

    @Override
    public User findByIdentifier(String username) {
        return userJpaRepository.findByIdentifier(username)
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));
    }

    @Override
    public Boolean existsByIdentifier(String identifier) {
        return userJpaRepository.existsByIdentifier(identifier);
    }

    @Override
    public void save(RegisterForm form) {
        userJpaRepository.save(form.toUser());
    }
}
