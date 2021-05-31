package com.fulgay.wutink.utils.handlers;

import com.fulgay.wutink.commons.notificationMessages.EnumErrorMessage;
import com.fulgay.wutink.commons.notificationMessages.GlobalMessages;
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
    protected ResponseEntity<GlobalMessages> handleMyException(JDBCException ex, WebRequest request) {
        GlobalMessages bodyOfResponse = new GlobalMessages();

        bodyOfResponse.setErrorMessage(EnumErrorMessage.USER_COULDNT_SAVE.getValue());
        LOG.error(ex.getMessage(),ex);

        return new ResponseEntity<GlobalMessages>(bodyOfResponse,HttpStatus.CONFLICT);
    }

    @ExceptionHandler(value = { PersistenceException.class})
    protected ResponseEntity<GlobalMessages> handleMyException(PersistenceException ex, WebRequest request) {
        GlobalMessages bodyOfResponse = new GlobalMessages();

        bodyOfResponse.setErrorMessage(ex.getMessage());
        LOG.error(ex.getMessage(),ex);

        return new ResponseEntity<GlobalMessages>(bodyOfResponse,HttpStatus.BAD_REQUEST);
    }
}
