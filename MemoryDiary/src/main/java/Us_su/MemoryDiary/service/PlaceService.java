package Us_su.MemoryDiary.service;

import Us_su.MemoryDiary.domain.Place;
import Us_su.MemoryDiary.repository.PlaceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class PlaceService {

    private PlaceRepository placeRepository;

    public void addPlace(List<String> places) {
        ArrayList<Place> placeList = new ArrayList<>();
        for (String place : places) {
            placeList.add(Place.builder()
                    .place(place)
                    .build());
        }
        placeRepository.saveAll(placeList);
    }

    public List<Place> getAllPlace() {
        return placeRepository.findAll();
    }

    public Place getPlace(String keyword) {
        return placeRepository.findByPlace(keyword).orElseThrow(() -> new IllegalArgumentException());
    }
}
