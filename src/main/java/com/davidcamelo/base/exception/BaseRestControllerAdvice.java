package com.davidcamelo.base.exception;

import com.davidcamelo.base.dto.ErrorDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.HandlerMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Slf4j
@RestControllerAdvice
public abstract class BaseRestControllerAdvice {

    @ExceptionHandler(value = {ServiceException.class})
    public ResponseEntity<ErrorDTO> handleServiceException(ServiceException ex, HandlerMethod handlerMethod, HttpServletRequest request) {
        log.error(ex.getMessage(), ex);
        String controllerName = handlerMethod.getMethod().getDeclaringClass().toString();
        String methodName = handlerMethod.getMethod().getName();
        ErrorDTO error = new ErrorDTO();
        error.setTimestamp(new Date());
        error.setErrorCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        error.setErrorMessage(ex.getMessage());
        error.setController(controllerName.substring(controllerName.lastIndexOf(".") + 1));
        error.setMethod(methodName);
        error.setPath(request.getRequestURL().toString());
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
