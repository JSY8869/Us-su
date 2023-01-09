package Us_su.MemoryDiary.handler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class LoginAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException exception) throws IOException, ServletException {

        String errorMessage = "";
        if(exception instanceof UsernameNotFoundException){
            errorMessage = "존재하지 않는 아이디 입니다.";
        } else if(exception instanceof BadCredentialsException) {
            errorMessage = "비밀번호가 일치하지 않습니다.";
        } else {
            errorMessage = "알수없는 이유로 로그인이 안되고 있습니다.";
        }

        super.onAuthenticationFailure(request, response, exception);
    }
}
