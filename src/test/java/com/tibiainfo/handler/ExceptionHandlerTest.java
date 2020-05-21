package com.tibiainfo.handler;

import com.tibiainfo.exception.NotFoundException;
import com.tibiainfo.model.dto.ErrorDTO;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;

import javax.servlet.http.HttpServletRequest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ExceptionHandlerTest {

    ExceptionHandler exceptionHandler = new ExceptionHandler();

    private void assertHandledException(HttpServletRequest request, Exception exception, HttpStatus expectedHttpStatus, ErrorDTO handlerResponse) {
        assertNotNull(handlerResponse);
        assertNotNull(handlerResponse.getTimestamp());
        assertEquals(expectedHttpStatus.value(), handlerResponse.getStatus());
        assertEquals(request.getRequestURI(), handlerResponse.getPath());
        assertEquals(expectedHttpStatus.getReasonPhrase(), handlerResponse.getError());
        assertEquals(exception.getMessage(), handlerResponse.getMessage());
    }

    @Test
    public void testGenericExceptionHandler() {
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        Mockito.when(request.getRequestURI()).thenReturn("/exception");

        Exception exception = new Exception("My Exception");

        HttpStatus expectedHttpStatus = HttpStatus.INTERNAL_SERVER_ERROR;

        ErrorDTO handlerResponse = (ErrorDTO) exceptionHandler.handleExceptions(exception, request).getBody();

        this.assertHandledException(request, exception, expectedHttpStatus, handlerResponse);
    }

    @Test
    public void testNotFoundExceptionHandler() {
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        Mockito.when(request.getRequestURI()).thenReturn("/not-found");

        Exception exception = new NotFoundException("Not Found Exception");

        HttpStatus expectedHttpStatus = HttpStatus.NOT_FOUND;

        ErrorDTO handlerResponse = (ErrorDTO) exceptionHandler.handleNotFoundException(exception, request).getBody();

        this.assertHandledException(request, exception, expectedHttpStatus, handlerResponse);
    }

}
