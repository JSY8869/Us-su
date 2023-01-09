package Us_su.MemoryDiary.repository.place;

import Us_su.MemoryDiary.domain.Place;

import java.util.List;

public interface PlaceRepository {

    Place findByPlace(String keyword);

    void SaveAll(List<String> keyword);

    List<String> findAll();
}
