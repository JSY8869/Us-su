package Us_su.MemoryDiary.service;

import Us_su.MemoryDiary.domain.Diary;
import Us_su.MemoryDiary.dto.GameDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class GameService {

    private final DiaryService diaryService;
    private final UserService userService;
    private final static String requestUrl1 = "http://192.168.35.6:8081/diary/Game/{sentence}";
    private final static String requestUrl2 = "http://192.168.35.6:8081/diary/Game2/{sentence}";
    private final static HttpEntity<?> requestMessage = new HttpEntity<>(new LinkedMultiValueMap<>(), new HttpHeaders());
    private final static RestTemplate restTemplate = new RestTemplate();

    public GameDto getGame1QnA(String identifier, LocalDate selectedDate) throws IllegalAccessException {
        if (getIsPlayedGame1(identifier, selectedDate)) {
            throw new IllegalAccessException();
        }
        GameDto response = restTemplate.postForObject(
                requestUrl1, requestMessage, GameDto.class, diaryService.getDiary(identifier, selectedDate).getContents());
        log.info("question = {}", response.getQuestion());
        log.info("answer = {}", response.getAnswer());
        return response;
    }

    public List<String> getGame2QnA(String identifier, LocalDate selectedDate) throws IllegalAccessException {
        if (getIsPlayedGame2(identifier, selectedDate)) {
            throw new IllegalAccessException();
        }
        List<String> response = restTemplate.postForObject(
                requestUrl2, requestMessage, List.class, diaryService.getDiary(identifier, selectedDate).getContents());
        log.info("answer = {}", response);
        return response;
    }
    @Transactional
    public void game1Clear(String identifier, LocalDate createdAt,Long score) {
        userService.findByIdentifier(identifier).setScore(score);
        Diary game1Diary = diaryService.getDiary(identifier, createdAt);
        game1Diary.setIsPlayed(true, game1Diary.getIsPlayed2());
    }
    @Transactional
    public void game2Clear(String identifier, LocalDate createdAt,Long score) {
        userService.findByIdentifier(identifier).setScore(score);
        Diary game2Diary = diaryService.getDiary(identifier, createdAt);
        game2Diary.setIsPlayed(game2Diary.getIsPlayed1(), true);
    }

    public Boolean getIsPlayedGame1(String identifier, LocalDate selectedDate) {
        return diaryService.getDiary(identifier, selectedDate).getIsPlayed1();
    }
    public Boolean getIsPlayedGame2(String identifier, LocalDate selectedDate) {
        return diaryService.getDiary(identifier, selectedDate).getIsPlayed2();
    }
}
