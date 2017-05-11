package wb3.core.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason="We could not find what you are looking for.")
public class ResourceNotFound extends RuntimeException {

    public ResourceNotFound(String message){
        super(message);
    }

    public ResourceNotFound() {

    }
}


