package com.gabo.quiz10.domain.model

data class ChatModelDomain(
    val id: Int,
    val email: String,
    val firstName: String,
    val lastName: String,
    val avatar: String,
    val messageType: String,
    val lastMessage: String?,
    val unreadMessage: Int,
    val isTyping: Boolean,
    val updatedDate: Long?
)