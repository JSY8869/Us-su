package Us_su.MemoryDiary.contorller;

import Us_su.MemoryDiary.dto.GameClearDto;
import Us_su.MemoryDiary.dto.GameDto;
import Us_su.MemoryDiary.dto.GetGameDto;
import Us_su.MemoryDiary.service.GameService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class GameController {

    private final GameService gameService;

    @GetMapping("/game1")
    public GameDto getGame1(@RequestBody GetGameDto getGameDto) throws IllegalAccessException {
        return gameService.getGame1QnA(getGameDto.getIdentifier(), getGameDto.getCreateAt());
    }

    @GetMapping("/game2")
    public List getGame2(@RequestBody GetGameDto getGameDto) throws IllegalAccessException {
        return gameService.getGame2QnA(getGameDto.getIdentifier(), getGameDto.getCreateAt());
    }

    @PostMapping("/game1")
    public ResponseEntity<Void> clearGame1(@RequestBody GameClearDto gameClearDto) {
        gameService.game1Clear(gameClearDto.getIdentifier(), gameClearDto.getCreateAt(), gameClearDto.getScore());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/game2")
    public ResponseEntity<Void> clearGame2(@RequestBody GameClearDto gameClearDto) {
        gameService.game2Clear(gameClearDto.getIdentifier(), gameClearDto.getCreateAt(), gameClearDto.getScore());
        return ResponseEntity.ok().build();
    }
}
