package Us_su.MemoryDiary.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
@AllArgsConstructor
@Table(name = "Diary")
@Builder @Getter
public class Diary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "diary_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "identifier", nullable = false)
    private User user;

    @Column(name = "created_at", nullable = false)
    private LocalDate createdAt;

    @Column(name = "contents", nullable = false)
    private String contents;

    @Column(name = "is_palyed_1", nullable = false)
    private Boolean isPlayed1;

    @Column(name = "is_palyed_2", nullable = false)
    private Boolean isPlayed2;

    public void setIsPlayed(Boolean isPlayed1, Boolean isPlayed2) {
        this.isPlayed1 = isPlayed1;
        this.isPlayed2 = isPlayed2;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }
}
