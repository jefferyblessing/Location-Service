package wb3.core.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(
        value = HttpStatus.BAD_REQUEST,
        reason="The request object was not correctly formatted.  You either supplied the wrong request object or you did not supply all required attributes.")
public class MalformedRequest extends RuntimeException {

    public MalformedRequest(String message){
        super(message);
    }

    public MalformedRequest() { }
}