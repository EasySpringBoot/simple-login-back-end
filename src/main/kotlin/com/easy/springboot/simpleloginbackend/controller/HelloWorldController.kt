package com.easy.springboot.simpleloginbackend.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController


@RestController
class HelloWorldController {

    @GetMapping(value = ["/hello"])
    fun hello(): String {
        return "Hello World!"
    }

}