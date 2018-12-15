package com.easy.springboot.simpleloginbackend.config

object LoginFilterConfig{
    /**
     * Session 中的当前登录用户的 Key
     */
    val CURRENT_USER_CONTEXT = "currentUser"

    /**
     * 过滤器白名单：包含这些名单中的 url 不需要过滤，直接 Pass
     */
    val FILTER_PASS_URLS = arrayOf("/login", "/login.json", ".js", ".ico")

    /**
     * 登录完成之后重定向到原来的 URL 在 Session 中的 Key
     */
    val LOGIN_REDIRECT_URL = "login_redirect_url"
}