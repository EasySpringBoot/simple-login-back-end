package com.easy.springboot.simpleloginbackend.controller

import com.easy.springboot.simpleloginbackend.config.LoginFilterConfig
import com.easy.springboot.simpleloginbackend.dao.UserDao
import com.easy.springboot.simpleloginbackend.entity.User
import com.easy.springboot.simpleloginbackend.result.Result
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import javax.servlet.http.HttpSession


@RestController
class LoginController {
@Autowired
lateinit var userDao: UserDao

@PostMapping(value = ["/login.json"])
fun login(@RequestParam("username") username: String, @RequestParam("password") password: String, httpSession: HttpSession): Result<String> {

    val user = userDao.findByUsername(username)
    return if (null == user) {
        Result(data = "username=${username},password=${password}", success = false, msg = "用户名不存在")
    } else if (user.username == username && user.password == password) {
        setSession(user, httpSession)
        Result(data = "username=${username},password=${password}", success = true, msg = "登陆成功")
    } else {
        Result(data = "username=${username},password=${password}", success = false, msg = "密码错误")
    }
}

@PostMapping(value = ["/logout.json"])
fun logout(httpSession: HttpSession) {
    httpSession.invalidate()
}

private fun setSession(user: User, httpSession: HttpSession) {
    httpSession.setAttribute(LoginFilterConfig.CURRENT_USER_CONTEXT, user)
}

}