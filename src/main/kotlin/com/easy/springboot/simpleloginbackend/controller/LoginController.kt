package com.easy.springboot.simpleloginbackend.controller

import com.easy.springboot.simpleloginbackend.result.Result
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController


@RestController
class LoginController {

    @PostMapping(value = ["/login.json"])
    fun login(@RequestParam("username") username: String, @RequestParam("password") password: String): Result<String> {
        return Result(data = "username=${username},password=${password}", success = true, msg = "")
    }

}