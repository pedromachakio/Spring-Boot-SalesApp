package pedromachakio.com.github.rest.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import pedromachakio.com.github.exception.BusinessLogicException;
import pedromachakio.com.github.rest.ApiErrors;

@RestControllerAdvice
public class ApplicationControllerAdvice {

    @ExceptionHandler(BusinessLogicException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrors handleBusinessLogicException(BusinessLogicException businessLogicException) {
        String errorMessage = businessLogicException.getMessage();
        return new ApiErrors(errorMessage);
    }
}
