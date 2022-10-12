package com.gabo.quiz10.data.transformers

import com.gabo.quiz10.data.dto.ChatDto
import com.gabo.quiz10.domain.model.ChatModelDomain

fun ChatDto.toDomainModel() = ChatModelDomain(
    id,
    email,
    firstName,
    lastName,
    avatar,
    messageType,
    lastMessage,
    unreadMessage,
    isTyping,
    updatedDate
)