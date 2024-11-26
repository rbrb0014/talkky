package com.kopring.talkky.handler

import org.springframework.stereotype.Component
import org.springframework.web.socket.CloseStatus
import org.springframework.web.socket.TextMessage
import org.springframework.web.socket.WebSocketSession
import org.springframework.web.socket.handler.TextWebSocketHandler

@Component
class SocketHandler(
    private val sessionList: ArrayList<WebSocketSession> = ArrayList()
): TextWebSocketHandler() {
    override fun handleTextMessage(session: WebSocketSession, message: TextMessage) {
        sessionList.forEach { webSocketSession ->
            if (webSocketSession.isOpen) {
                webSocketSession.sendMessage(TextMessage(message.payload))
            }
        }
    }

    override fun afterConnectionEstablished(session: WebSocketSession) {
        sessionList.add(session)
    }

    override fun afterConnectionClosed(session: WebSocketSession, status: CloseStatus) {
        sessionList.remove(session)
    }
}