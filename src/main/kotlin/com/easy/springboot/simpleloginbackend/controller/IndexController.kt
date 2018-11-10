package com.easy.springboot.simpleloginbackend.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping


@Controller
class IndexController {

    @GetMapping(value = ["", "/", "/index.html", "/index.htm"])
    fun index(): String {
        return "index"
    }

}