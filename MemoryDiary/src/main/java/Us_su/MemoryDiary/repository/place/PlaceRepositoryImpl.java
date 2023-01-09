package Us_su.MemoryDiary.repository.place;

import Us_su.MemoryDiary.domain.Place;
import Us_su.MemoryDiary.exception.CustomException;
import Us_su.MemoryDiary.exception.ErrorCode;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class PlaceRepositoryImpl implements PlaceRepository{

    private final PlaceJpaRepository placeJpaRepository;


    @Override
    public Place findByPlace(String keyword) {
        return placeJpaRepository.findByPlace(keyword)
                .orElseThrow(() -> new CustomException(ErrorCode.PLACE_NOT_FOUND));
    }

    @Override
    public void SaveAll(List<String> places) {
        ArrayList<Place> placeList = new ArrayList<>();
        for (String place : places) {
            placeList.add(Place.builder()
                    .place(place)
                    .build());
        }
        placeJpaRepository.saveAll(placeList);
    }

    @Override
    public List<String> findAll() {
        return placeJpaRepository.findAllPlace();
    }
}
