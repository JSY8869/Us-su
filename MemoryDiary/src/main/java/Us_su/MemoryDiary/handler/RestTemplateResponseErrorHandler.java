package Us_su.MemoryDiary.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.IOException;

public class RestTemplateResponseErrorHandler implements ResponseErrorHandler {

    @Override
    public boolean hasError(ClientHttpResponse response) throws IOException {
        return response.getStatusCode() == HttpStatus.BAD_REQUEST
                || response.getStatusCode() == HttpStatus.INTERNAL_SERVER_ERROR;
    }

    @Override
    public void handleError(ClientHttpResponse response) throws IOException {
    }
}
