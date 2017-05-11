package wb3.core.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason="Something blew up on our side, sorry.")
public class InternalServerException extends RuntimeException {
}
