package com.kopring.talkky.authuser

import org.springframework.stereotype.Service

@Service
class AuthUserService(
    private val userNameList: MutableList<String> = mutableListOf()
) {
    fun isUserNameDuplicated(userName: String): Boolean {
        return if (userNameList.contains(userName)) {
            true
        } else {
            userNameList.add(userName)
            false
        }
    }
}