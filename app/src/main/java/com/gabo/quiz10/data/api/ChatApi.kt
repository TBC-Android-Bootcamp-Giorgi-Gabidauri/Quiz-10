package com.gabo.quiz10.data.api

import com.gabo.quiz10.constants.CHAT_END_POINT
import com.gabo.quiz10.data.dto.ChatDto
import retrofit2.Response
import retrofit2.http.GET

interface ChatApi {
    @GET(CHAT_END_POINT)
    suspend fun getChatInfo(): Response<List<ChatDto>>
}