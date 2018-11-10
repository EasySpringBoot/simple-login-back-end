package com.easy.springboot.simpleloginbackend

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SimpleLoginBackEndApplication

fun main(args: Array<String>) {
    runApplication<SimpleLoginBackEndApplication>(*args)
}
