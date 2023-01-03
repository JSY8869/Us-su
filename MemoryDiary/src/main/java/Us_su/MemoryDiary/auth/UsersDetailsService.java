package Us_su.MemoryDiary.auth;

import Us_su.MemoryDiary.domain.User;
import Us_su.MemoryDiary.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsersDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByIdentifier(username)
                .orElseThrow(() -> new UsernameNotFoundException("존재하지 않는 아이디 입니다."));
        return new UsersDetails(user);
    }
}
