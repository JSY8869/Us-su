package Us_su.MemoryDiary.contorller;

import Us_su.MemoryDiary.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
public class LoginController {

    private final UserService userService;

    @GetMapping("/login")
    public ResponseEntity<Void> login(
            @RequestParam(name = "error", required = false) String error,
            @RequestParam(name = "exception", required = false) String exception){
        log.info("error={}", error);
        log.info("exception={}", exception);
        return ResponseEntity.ok().build();
    }
}
