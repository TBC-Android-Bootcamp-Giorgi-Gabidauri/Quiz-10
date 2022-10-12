package com.gabo.quiz10.data.dto

import com.google.gson.annotations.SerializedName

data class ChatDto(
    val id: Int,
    val email: String,
    @SerializedName("first_name")
    val firstName: String,
    @SerializedName("last_name")
    val lastName: String,
    val avatar: String,
    @SerializedName("message_type")
    val messageType: String,
    @SerializedName("last_message")
    val lastMessage: String?,
    @SerializedName("unrea_message")
    val unreadMessage: Int,
    @SerializedName("is_typing")
    val isTyping: Boolean,
    @SerializedName("updated_date")
    val updatedDate: Long?
)