package Us_su.MemoryDiary.service;

import Us_su.MemoryDiary.domain.Place;
import Us_su.MemoryDiary.repository.place.PlaceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class PlaceService {

    private final PlaceRepository placeRepository;

    public void addPlace(List<String> places) {
        placeRepository.SaveAll(places);
    }

    public List<String> getAllPlace() {
        return placeRepository.findAll();
    }

    public Place getPlace(String keyword) {
        return placeRepository.findByPlace(keyword);
    }
}
