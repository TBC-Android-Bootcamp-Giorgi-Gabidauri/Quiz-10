package com.gabo.quiz10.ui.model

data class ChatModelUi(
    val id: Int,
    val email: String,
    val firstName: String,
    val lastName: String,
    val avatar: String,
    val messageType: MessageType,
    val lastMessage: String?,
    val unreadMessage: Int,
    val isTyping: Boolean,
    val updatedDate: Long?
)
