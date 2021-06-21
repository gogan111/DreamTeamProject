package dreamTeam.GlobalException;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class IncorrectMailException extends RuntimeException implements ExceptionMapper {

    public IncorrectMailException() {
        super("Введене некорректный email адрес, повторите попытку");
    }

    public IncorrectMailException(String message) {
        super(message);
    }

    @Override
    public Response toResponse(Throwable throwable) {
        return Response.status(404)
                .entity(throwable.getMessage())
                .type(MediaType.TEXT_PLAIN_TYPE)
                .build();
    }
}
