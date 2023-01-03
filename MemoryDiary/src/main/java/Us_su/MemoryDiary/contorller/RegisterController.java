package Us_su.MemoryDiary.contorller;

import Us_su.MemoryDiary.dto.RegisterForm;
import Us_su.MemoryDiary.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpResponse;

@RestController
@Slf4j
@RequiredArgsConstructor
public class RegisterController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<Void> register(@Validated @RequestBody RegisterForm form, BindingResult bindingResult){
        log.info("form = {}", form.toString());
        if(bindingResult.hasErrors()){
            log.debug("필드 검증 오류={}", bindingResult);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        log.info("중복 확인");
        if(userService.isDuplicateUser(form.getIdentifier())){
            log.debug("아이디 중복 오류 identifier={}", form.getIdentifier());
            bindingResult.rejectValue("identifier", "duplicatedUser", "아이디가 중복됩니다.");
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        log.info("에러 없음");
        userService.createUser(form);

        return ResponseEntity.ok().build();
    }
}
