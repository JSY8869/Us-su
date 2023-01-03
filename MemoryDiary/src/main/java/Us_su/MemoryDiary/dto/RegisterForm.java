package Us_su.MemoryDiary.dto;

import Us_su.MemoryDiary.domain.RoleType;
import Us_su.MemoryDiary.domain.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;

@Data
public class RegisterForm {

    @NotBlank(message = "아이디를 작성해주세요.")
    @Pattern(
            regexp = "^[A-Za-z0-9]{6,18}$"
    )
    private String identifier;

    @NotBlank(message = "비밀번호를 작성해주세요.")
    @Pattern(
            regexp = "^.*(?=^.{8,15}$)(?=.*\\d)(?=.*[a-zA-Z])(?=.*[!@#$%^&+=*]).*$"
    )
    private String password;

    public User toUser(PasswordEncoder passwordEncoder) {
        return User.builder()
                .role(RoleType.USER)
                .identifier(identifier)
                .password(passwordEncoder.encode(password))
                .score(-1L)
                .build();
    }
}
