package com.dent.bff.controller.login;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.dent.bff.controller.common.BaseController;
import com.dent.bff.controller.login.model.RequestLogin;
import com.dent.bff.controller.login.model.ResponseLogin;
import com.dent.bff.infrastructure.common.model.AuthRole;
import com.dent.bff.infrastructure.common.model.ExceptionModel;
import com.dent.bff.infrastructure.common.model.User;
import com.dent.bff.infrastructure.common.util.JwtUtil;

import io.swagger.annotations.Api;

@RestController
@Api(tags = "Login Management")
public class LoginController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<ResponseLogin> login(HttpServletRequest httpRequest, HttpServletResponse httpResponse,
            @RequestBody RequestLogin request) {
        ResponseLogin response = new ResponseLogin();
        response.setStatus(Boolean.FALSE);
        response.setUsername(request.getUsername());

        // INFO:GA: ldap kontrolu burada yapilmali
        if ("42".equals(request.getPassword())) {
            if ("yoda".equalsIgnoreCase(request.getUsername())) {
                response.setRole(AuthRole.ADMIN.getCode());
                response.setStatus(Boolean.TRUE);
            } else if ("jedi".equalsIgnoreCase(request.getUsername())) {
                response.setRole(AuthRole.USER.getCode());
                response.setStatus(Boolean.TRUE);
            } else if ("padawan".equalsIgnoreCase(request.getUsername())) {
                response.setRole(AuthRole.API.getCode());
                response.setStatus(Boolean.TRUE);
            }
        }

        if (response.getStatus() != null && response.getStatus().booleanValue()) {
            jwtUtil.auth(new User(request.getUsername(), AuthRole.fromCode(response.getRole())), httpResponse);
            return new ResponseEntity<ResponseLogin>(response, HttpStatus.OK);
        } 
        
        return new ResponseEntity<ResponseLogin>(response, HttpStatus.UNAUTHORIZED);
    }

}
