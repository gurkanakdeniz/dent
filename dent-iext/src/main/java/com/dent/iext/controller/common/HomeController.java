package com.dent.iext.controller.common;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;

@RestController
@Api(tags = "Home")
public class HomeController extends BaseController {

    
    @GetMapping
    public Object home() {
        Object obj = "jedi";

        return obj;
    }

}
