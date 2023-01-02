package com.kafeinmevlut.garage.exception;

import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.core.config.Order;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static com.kafeinmevlut.garage.utilities.JacksonUtil.parseObjectAsPrettyString;

/**
 * @author mevlutbeder
 * @created 02/01/2023 04:05
 */
@ControllerAdvice
@Slf4j
@Order(Ordered.HIGHEST_PRECEDENCE)
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        return super.handleMethodArgumentNotValid(ex, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        return super.handleHttpMessageNotReadable(ex, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotWritable(HttpMessageNotWritableException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        return super.handleHttpMessageNotWritable(ex, headers, status, request);
    }

    private ResponseEntity<String> buildResponseString(ApiError apiError) {
        return new ResponseEntity<>(parseObjectAsPrettyString(apiError), apiError.getStatus());
    }

    @ExceptionHandler({
            Exception.class,
    })
    @Nullable
    protected ResponseEntity<String> handleException(Exception ex) {
        log.error(ex.getMessage(), ex);
        return buildResponseString(new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, ex,
                "An unexpected error has been received! Error message : ", ex.getMessage()));
    }

    @ExceptionHandler({
            NullPointerException.class,
    })
    @Nullable
    protected ResponseEntity<String> handleNullPointerException(Exception ex) {
        log.error(ex.getMessage(), ex);
        return buildResponseString(new ApiError(HttpStatus.INTERNAL_SERVER_ERROR,
                "The specified field cannot be empty! ", ex));
    }

}
