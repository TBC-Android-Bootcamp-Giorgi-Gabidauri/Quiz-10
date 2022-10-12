package com.gabo.quiz10.domain.useCases

import com.gabo.quiz10.base.BaseUseCase
import com.gabo.quiz10.data.handleResponse.Resource
import com.gabo.quiz10.domain.model.ChatModelDomain
import com.gabo.quiz10.domain.repository.ChatRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetChatInfoUseCase @Inject constructor(private val chatRepository: ChatRepository) :
    BaseUseCase<Unit, Flow<Resource<List<ChatModelDomain>>>> {
    override suspend fun invoke(params: Unit): Flow<Resource<List<ChatModelDomain>>> {
        return chatRepository.getChatInfo()
    }
}