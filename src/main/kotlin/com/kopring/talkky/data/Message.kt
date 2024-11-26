package com.kopring.talkky.data

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule

class Message(
    var time: Long,
    var author: String? = null,
    var message: String? = null
) {
    fun toJson(): String {
        val mapper = ObjectMapper().registerModule(KotlinModule.Builder().build())
        val deserialized = mapper.writeValueAsString(this)
        return deserialized
    }
}