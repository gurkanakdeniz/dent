package com.dent.bff.infrastructure.interceptor;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.dent.bff.infrastructure.common.model.SessionKey;
import com.dent.bff.infrastructure.common.util.JsonUtil;
import com.dent.bff.infrastructure.common.util.RequestUtil;
import com.dent.bff.infrastructure.common.util.Session;

@Aspect
@Component
public class RequestInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(RequestInterceptor.class);

    @Before("(@annotation(org.springframework.web.bind.annotation.GetMapping) || "
            + "@annotation(org.springframework.web.bind.annotation.PostMapping) || "
            + "@annotation(org.springframework.web.bind.annotation.RequestMapping)) && "
            + "execution(public * *(..)) && " + "within(*..controller..*)")
    public void before(final JoinPoint joinPoint) throws Throwable {
        Session.instance().put(SessionKey.DURATION, new Date().getTime());
    }

    @AfterReturning(pointcut = "(@annotation(org.springframework.web.bind.annotation.ExceptionHandler) || "
            + "(within(*..controller..*) && (@annotation(org.springframework.web.bind.annotation.GetMapping) || "
            + "@annotation(org.springframework.web.bind.annotation.PostMapping) || "
            + "@annotation(org.springframework.web.bind.annotation.RequestMapping)))) && "
            + "execution(public * *(..))", returning = "response")
    public void after(final JoinPoint joinPoint, final Object response) throws Throwable {
        log(response, joinPoint.toLongString());
        Session.instance().clear();
    }

    private void log(Object object, String jpString) {
        try {
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
                    .getRequest();
            String req = RequestUtil.extractRequestPayload(request);
            String headers = RequestUtil.extractRequestHeaders(request);
            String response = JsonUtil.toJson(object);
            long duration = new Date().getTime() - (Long) Session.instance().get(SessionKey.DURATION);

            String log = "\n\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n" + RequestUtil.extractPath(request)
                    + "\n-------- Request -------- \n" + jpString + "\n" + headers + "\n" + req
                    + "-------- Request --------";
            log += "\n\n-------- Response -------- \n" + jpString + "\n" + response + "\n" + duration
                    + "\n-------- Response --------\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n";
            logger.info(log);
        } catch (Throwable e) {
            // ignore
        }
    }

}
