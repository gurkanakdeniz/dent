package com.dent.iext.infrastructure.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dent.iext.infrastructure.common.model.ExceptionModel;

@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ApplicationExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(ApplicationExceptionHandler.class);

    @ExceptionHandler({ Throwable.class })
    @ResponseBody
    public ResponseEntity<ExceptionModel> handleExcetion(final Throwable ex) {
        logger.error("handleExcetion -> ", ex);

        String code = "4201";
        String desc = ex.getMessage();
        String text = null;

        try {
            StackTraceElement[] stackTrace = ex.getStackTrace();
            text = stackTrace[0].toString();
        } catch (Throwable e) {
            // ignore
        }

        return new ResponseEntity<ExceptionModel>(new ExceptionModel(code, desc, text), HttpStatus.BAD_REQUEST);
    }

}
