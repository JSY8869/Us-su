package Us_su.MemoryDiary.service;

import Us_su.MemoryDiary.domain.User;
import Us_su.MemoryDiary.dto.RegisterForm;
import Us_su.MemoryDiary.repository.UserRepository;
import jakarta.persistence.NoResultException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void createUser(RegisterForm form){
        userRepository.save(form.toUser(passwordEncoder));
    }

    public Boolean isDuplicateUser(String identifier){
        return userRepository.existsByIdentifier(identifier);
    }

    public User findByIdentifier(String identifier) {
        return userRepository.findByIdentifier(identifier).orElseThrow(() -> new NoResultException());
    }
}
