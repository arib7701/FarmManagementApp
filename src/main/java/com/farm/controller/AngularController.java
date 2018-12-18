package com.farm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AngularController {

    @RequestMapping({"/admin/**", "/login"})
    public String index() {
        return "forward:/index.html";
    }
}
