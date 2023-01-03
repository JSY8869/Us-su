package Us_su.MemoryDiary.repository;

import Us_su.MemoryDiary.domain.Place;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PlaceRepository extends JpaRepository<Place, Long> {

    Optional<Place> findByPlace(String keyword);
}
