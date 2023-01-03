package Us_su.MemoryDiary.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "users")
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
@AllArgsConstructor
@Builder @Getter
public class User {

    @Id
    @Column(name = "identifier", nullable = false, unique = true)
    private String identifier;

    @Column(name = "password", nullable = false)
    private String password;

    @Enumerated
    @Column(name = "role", nullable = false)
    private RoleType role;

    @Column(name = "score", nullable = false)
    private Long score;

    public void setScore(Long score) {
        this.score = score;
    }
}
