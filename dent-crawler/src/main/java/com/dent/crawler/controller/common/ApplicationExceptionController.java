package com.dent.crawler.controller.common;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dent.crawler.infrastructure.common.model.ExceptionModel;
import com.dent.crawler.infrastructure.common.util.RequestUtil;

import springfox.documentation.annotations.ApiIgnore;

@Controller
@ApiIgnore
public class ApplicationExceptionController extends BaseController implements ErrorController {

    private static final Logger logger = LoggerFactory.getLogger(ApplicationExceptionController.class);

    @RequestMapping(value = "/error")
    @ResponseBody
    public ResponseEntity<ExceptionModel> handleError(HttpServletRequest request, HttpServletResponse response) {
        logger.error("handleError -> ", RequestUtil.extractPath(request));

        String code = null;
        try {
            code = (String) request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        } catch (Throwable e) {
            // ignore
        }

        if (code == null) {
            code = "4202";
        }

        String desc = "";
        String text = "";
        HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        try {
            desc = String.valueOf(response.getStatus());
            if (HttpStatus.FORBIDDEN.value() == response.getStatus()) {
                httpStatus = HttpStatus.FORBIDDEN;
            }
        } catch (Throwable e) {
            // ignore
        }

        return new ResponseEntity<ExceptionModel>(new ExceptionModel(code, desc, text), httpStatus);
    }

    @Override
    public String getErrorPath() {
        return "error";
    }

}
