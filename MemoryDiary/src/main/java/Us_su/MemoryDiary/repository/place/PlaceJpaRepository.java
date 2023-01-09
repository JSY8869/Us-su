package Us_su.MemoryDiary.repository.place;

import Us_su.MemoryDiary.domain.Place;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PlaceJpaRepository extends JpaRepository<Place, Long> {

    Optional<Place> findByPlace(String keyword);
    @Query("select p.place from Place p")
    List<String> findAllPlace();
}
