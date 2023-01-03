package Us_su.MemoryDiary.contorller;

import Us_su.MemoryDiary.service.PlaceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
public class PlaceController {

    private final PlaceService placeService;

    @PostMapping("/save/place")
    public ResponseEntity<Void> savePlace(@RequestBody List<String> places) {
        placeService.addPlace(places);
        return ResponseEntity.ok().build();
    }
}
