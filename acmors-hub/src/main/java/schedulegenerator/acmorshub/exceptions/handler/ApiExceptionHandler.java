package schedulegenerator.acmorshub.exceptions.handler;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import schedulegenerator.acmorshub.exceptions.ResourceAlreadyExsistsException;
import schedulegenerator.acmorshub.exceptions.ResourceNotFoundExeption;
import schedulegenerator.acmorshub.exceptions.response.ErrorMessage;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(ResourceAlreadyExsistsException.class)
    public ResponseEntity<ErrorMessage> resourceAlreadyExsists(ResourceAlreadyExsistsException ex, HttpServletRequest request) {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .contentType(MediaType.APPLICATION_JSON)
                .body( new ErrorMessage(request, HttpStatus.NOT_FOUND, ex.getMessage()));
    }

    @ExceptionHandler(ResourceNotFoundExeption.class)
    public ResponseEntity<ErrorMessage> resourceNotFound(ResourceNotFoundExeption ex, HttpServletRequest request) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .contentType(MediaType.APPLICATION_JSON)
                .body( new ErrorMessage(request, HttpStatus.NOT_FOUND, ex.getMessage()));
    }
}
