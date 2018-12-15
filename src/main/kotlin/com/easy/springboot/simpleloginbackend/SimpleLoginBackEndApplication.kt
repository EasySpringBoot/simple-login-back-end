package com.easy.springboot.simpleloginbackend

import com.easy.springboot.simpleloginbackend.dao.UserDao
import com.easy.springboot.simpleloginbackend.entity.User
import com.easy.springboot.simpleloginbackend.filter.LoginFilter
import org.springframework.boot.ApplicationRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration
import org.springframework.boot.builder.SpringApplicationBuilder
import org.springframework.boot.web.servlet.ServletComponentScan
import org.springframework.context.support.beans

@SpringBootApplication(exclude = [ErrorMvcAutoConfiguration::class])// exclude, 有异常不会找默认error页面了，而是直接输出字符串
@ServletComponentScan(basePackageClasses = [LoginFilter::class])
class SimpleLoginBackEndApplication

fun main(args: Array<String>) {
    SpringApplicationBuilder().initializers(
            beans {
                bean {
                    ApplicationRunner {
                        val userDao = ref<UserDao>()
                        init(userDao)
                    }
                }
            }
    ).sources(SimpleLoginBackEndApplication::class.java).run(*args)

}

fun init(userDao: UserDao) {
    val admin = User()
    admin.id = 1
    admin.username = "admin"
    admin.password = "123456"
    userDao.save(admin)
    val jack = User()
    jack.id = 2
    jack.username = "jack"
    jack.password = "123456"
    userDao.save(jack)
}
