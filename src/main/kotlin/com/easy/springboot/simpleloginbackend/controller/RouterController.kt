package com.easy.springboot.simpleloginbackend.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping


@Controller
class RouterController {

    @GetMapping(value = ["", "/", "/login"])
    fun login(): String {
        return "login"
    }

    @GetMapping(value = ["/welcome"])
    fun welcome(): String {
        return "welcome"
    }

}