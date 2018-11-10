package com.easy.springboot.simpleloginbackend.result

class Result<T>(
        var data: T? = null,
        var success: Boolean = false,
        var msg: String = ""
)