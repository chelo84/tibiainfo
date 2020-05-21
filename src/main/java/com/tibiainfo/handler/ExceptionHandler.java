package com.tibiainfo.handler;

import com.tibiainfo.exception.NotFoundException;
import com.tibiainfo.model.dto.ErrorDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@Slf4j
@ControllerAdvice
@RestController
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(NotFoundException.class)
    public final ResponseEntity<Object> handleNotFoundException(Exception ex, HttpServletRequest request) {
        ErrorDTO errorDetails = buildErrorDetails(
                ex,
                request,
                HttpStatus.NOT_FOUND
        );
        log.error(ex.getMessage(), ex);
        return new ResponseEntity(errorDetails, HttpStatus.NOT_FOUND);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleExceptions(Exception ex, HttpServletRequest request) {
        ErrorDTO errorDetails = buildErrorDetails(
                ex,
                request,
                HttpStatus.INTERNAL_SERVER_ERROR
        );
        log.error(ex.getMessage(), ex);
        return new ResponseEntity(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ErrorDTO buildErrorDetails(Exception ex, HttpServletRequest request, HttpStatus httpStatus) {
        log.error(ex.getMessage(), ex);
        return ErrorDTO.builder()
                .timestamp(LocalDateTime.now())
                .status(httpStatus.value())
                .error(httpStatus.getReasonPhrase())
                .message(ex.getMessage())
                .path(request.getRequestURI())
                .build();
    }

}
