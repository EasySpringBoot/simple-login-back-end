package com.easy.springboot.simpleloginbackend.dao

import com.easy.springboot.simpleloginbackend.entity.User
import org.springframework.data.repository.CrudRepository

interface UserDao : CrudRepository<User, Long> {
    fun findByUsername(username: String): User?
}