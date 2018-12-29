package com.easy.springboot.simpleloginbackend.filter

import com.easy.springboot.simpleloginbackend.config.LoginFilterConfig
import com.easy.springboot.simpleloginbackend.entity.User
import javax.servlet.*
import javax.servlet.annotation.WebFilter
import javax.servlet.http.HttpServletRequest

@WebFilter(urlPatterns = ["/*"])
class LoginFilter : Filter {

    override fun init(filterConfig: FilterConfig) {
        println("===> LoginFilter init")
    }

    override fun doFilter(request: ServletRequest, response: ServletResponse, chain: FilterChain) {
        println("===> LoginFilter doFilter")
        val requestURL = (request as HttpServletRequest).requestURL.toString()
        // 1.判断是否需要鉴权
        if (isNeedAuth(requestURL, request)) {
            // 2.执行用户登陆状态鉴权
            doAuthenticationFilter(request, response, chain)
            chain.doFilter(request, response)
        } else {
            chain.doFilter(request, response)
        }
    }

    /**
     * 该请求 URL ： 不在资源白名单 && 没有被过滤过
     */
    private fun isNeedAuth(requestURL: String, request: ServletRequest) = !isEscapeUrls(requestURL)

    private fun doAuthenticationFilter(request: ServletRequest, response: ServletResponse, chain: FilterChain) {
        val httpServletRequest = request as HttpServletRequest
        val sessionUser = getSessionUser(httpServletRequest)
        // 如果当前 session 中不存在该用户（ 用户未登录 ）
        if (sessionUser == null) {
            // 跳转登陆页面
            redirectLogin(request, response)
        }
    }

    /**
     * 跳转登陆
     */
    private fun redirectLogin(request: ServletRequest, response: ServletResponse) {
        val httpServletRequest = request as HttpServletRequest
        var toURL = httpServletRequest.requestURL.toString()
        // 查询参数处理
        val queryString = httpServletRequest.queryString
        if (queryString != "") {
            toURL += "?$queryString"
        }
        // 将用户请求的 URL 存入 Session 中，用于登陆成功之后跳转
        httpServletRequest.session.setAttribute(LoginFilterConfig.LOGIN_REDIRECT_URL, toURL)
        httpServletRequest.getRequestDispatcher("/login").forward(request, response)
    }

    /**
     * 是否白名单
     */
    private fun isEscapeUrls(requestURI: String): Boolean {
        LoginFilterConfig.FILTER_PASS_URLS.iterator().forEach {
            if (requestURI.indexOf(it) >= 0) {
                return true
            }
        }
        return false
    }

    /**
     * 获取当前 Session 中是否有该用户
     */
    private fun getSessionUser(httpServletRequest: HttpServletRequest): User? {
        return httpServletRequest.session.getAttribute(LoginFilterConfig.CURRENT_USER_CONTEXT) as? User
    }


    override fun destroy() {
        println("===> LoginFilter destroy")
    }

}
