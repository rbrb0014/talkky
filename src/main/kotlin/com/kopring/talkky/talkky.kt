package com.kopring.talkky

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling

@EnableScheduling
@SpringBootApplication
class RealTimeChatApplication

fun main(args: Array<String>) {
    runApplication<RealTimeChatApplication>(*args)
}