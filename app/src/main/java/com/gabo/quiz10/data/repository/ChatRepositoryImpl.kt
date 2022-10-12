package com.gabo.quiz10.data.repository

import com.gabo.quiz10.data.api.ChatApi
import com.gabo.quiz10.data.handleResponse.HandleResponse
import com.gabo.quiz10.data.handleResponse.Resource
import com.gabo.quiz10.data.transformers.toDomainModel
import com.gabo.quiz10.domain.model.ChatModelDomain
import com.gabo.quiz10.domain.repository.ChatRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ChatRepositoryImpl @Inject constructor(
    private val api: ChatApi,
) : ChatRepository, HandleResponse {
    override suspend fun getChatInfo(): Flow<Resource<List<ChatModelDomain>>> = flow {
        when (val result = handleResponse { api.getChatInfo() }) {
            is Resource.Success -> emit(Resource.Success(result.data?.map { it.toDomainModel() }))
            is Resource.Error -> emit(Resource.Error(result.errorMsg))
        }
    }
}