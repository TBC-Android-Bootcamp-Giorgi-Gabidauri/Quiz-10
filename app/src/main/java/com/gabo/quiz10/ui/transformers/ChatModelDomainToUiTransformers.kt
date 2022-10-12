package com.gabo.quiz10.ui.transformers

import com.gabo.quiz10.ui.model.MessageType
import com.gabo.quiz10.domain.model.ChatModelDomain
import com.gabo.quiz10.ui.model.ChatModelUi

fun ChatModelDomain.toModelUi() = ChatModelUi(
    id,
    email,
    firstName,
    lastName,
    avatar,
    when (messageType) {
        MessageType.Attachment.name.lowercase() -> MessageType.Text
        MessageType.Voice.name.lowercase() -> MessageType.Text
        else -> MessageType.Text
    },
    lastMessage,
    unreadMessage,
    isTyping,
    updatedDate
)