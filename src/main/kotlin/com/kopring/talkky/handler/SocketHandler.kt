package com.kopring.talkky.handler

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import com.kopring.talkky.data.Message
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import org.springframework.web.socket.CloseStatus
import org.springframework.web.socket.TextMessage
import org.springframework.web.socket.WebSocketSession
import org.springframework.web.socket.handler.TextWebSocketHandler

@Component
class SocketHandler(
    private val sessionList: ArrayList<WebSocketSession> = ArrayList(),
    private val messageList: ArrayList<Message> = ArrayList(),
): TextWebSocketHandler() {
    override fun handleTextMessage(session: WebSocketSession, textMessage: TextMessage) {
        sessionList.forEach { webSocketSession ->
            if (webSocketSession.isOpen) {
                webSocketSession.sendMessage(TextMessage(textMessage.payload))

                val mapper = ObjectMapper().registerModule(KotlinModule.Builder().build())
                val serialized = mapper.readValue(textMessage.payload, Message::class.java)

                messageList.add(serialized)
            }
        }
    }

    override fun afterConnectionEstablished(session: WebSocketSession) {
        sessionList.add(session)

        messageList.forEach { message ->
            session.sendMessage(TextMessage(message.toJson()))
        }
    }

    override fun afterConnectionClosed(session: WebSocketSession, status: CloseStatus) {
        sessionList.remove(session)
    }

    @Scheduled(cron = "0 0 6 * * ?")
    fun clearSystem() {
        sessionList.forEach { it.close() }
        sessionList.clear()
        messageList.clear()
    }
}