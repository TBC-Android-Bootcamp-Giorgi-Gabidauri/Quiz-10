package com.gabo.quiz10.domain.repository

import com.gabo.quiz10.data.handleResponse.Resource
import com.gabo.quiz10.domain.model.ChatModelDomain
import kotlinx.coroutines.flow.Flow

interface ChatRepository {
    suspend fun getChatInfo(): Flow<Resource<List<ChatModelDomain>>>
}