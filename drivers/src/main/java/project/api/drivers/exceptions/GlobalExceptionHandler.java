package project.api.drivers.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import project.api.drivers.ultis.ResponseObject;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<ResponseObject> handleNullPointerException(NullPointerException e) {
        ResponseObject<String> responseObject = new ResponseObject<>();
        responseObject.setStatus("error");
        responseObject.setMessage("Null pointer exception: " + e.getMessage());
        return new ResponseEntity<>(responseObject, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ResponseObject> handleIllegalArgumentException(IllegalArgumentException e) {
        ResponseObject<String> responseObject = new ResponseObject<>();
        responseObject.setStatus("error");
        responseObject.setMessage("Illegal argument exception: " + e.getMessage());
        return new ResponseEntity<>(responseObject, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseObject> handleException(Exception e) {
        ResponseObject<String> responseObject = new ResponseObject<>();
        responseObject.setStatus("error");
        responseObject.setMessage(e.getMessage());
        return new ResponseEntity<>(responseObject, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}