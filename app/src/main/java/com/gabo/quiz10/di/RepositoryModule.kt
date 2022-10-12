package com.gabo.quiz10.di

import com.gabo.quiz10.data.repository.ChatRepositoryImpl
import com.gabo.quiz10.domain.repository.ChatRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindChatRepository(chatRepositoryImpl: ChatRepositoryImpl): ChatRepository
}