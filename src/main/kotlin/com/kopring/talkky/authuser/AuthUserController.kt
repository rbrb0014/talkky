package com.kopring.talkky.authuser

import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class AuthUserController(
    private val authUserService: AuthUserService
) {
    @GetMapping("/auth/user/{userName}")
    @CrossOrigin(origins = ["*"])
    fun isUserNameDuplicated(@PathVariable userName: String) =
        authUserService.isUserNameDuplicated(userName)
}