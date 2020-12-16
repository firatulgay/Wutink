package com.fulgay.bilenyum.utils.handlers;

import com.fulgay.bilenyum.commons.EnumErrorMessage;
import com.fulgay.bilenyum.commons.ErrorDto;
import org.apache.log4j.Logger;
import org.hibernate.JDBCException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.persistence.PersistenceException;

@ControllerAdvice
public class GeneralExceptionHandler extends ResponseEntityExceptionHandler {

    private static final Logger LOG = Logger.getLogger(GeneralExceptionHandler.class);

    @ExceptionHandler(value = { JDBCException.class})
    protected ResponseEntity<ErrorDto> handleMyException(JDBCException ex, WebRequest request) {
        ErrorDto bodyOfResponse = new ErrorDto();

        bodyOfResponse.setMessage(EnumErrorMessage.USER_COULDNT_SAVE.getDisplayValue());
        bodyOfResponse.setCode("1000");
        LOG.error(ex.getMessage());

        return new ResponseEntity<ErrorDto>(bodyOfResponse,HttpStatus.CONFLICT);
    }

    @ExceptionHandler(value = { PersistenceException.class})
    protected ResponseEntity<ErrorDto> handleMyException(PersistenceException ex, WebRequest request) {
        ErrorDto bodyOfResponse = new ErrorDto();

        bodyOfResponse.setMessage(ex.getMessage());
        bodyOfResponse.setCode("1000");
        LOG.error(ex.getMessage());

        return new ResponseEntity<ErrorDto>(bodyOfResponse,HttpStatus.BAD_REQUEST);
    }
}
