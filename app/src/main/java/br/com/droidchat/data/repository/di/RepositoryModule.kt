package br.com.droidchat.data.repository.di

import br.com.droidchat.data.repository.AuthRepository
import br.com.droidchat.data.repository.AuthRepositoryImpl
import br.com.droidchat.data.repository.ChatRepository
import br.com.droidchat.data.repository.ChatRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface RepositoryModule {

    @Binds
    fun bindAuthRepository(impl: AuthRepositoryImpl): AuthRepository

    @Binds
    fun bindChatRepository(impl: ChatRepositoryImpl): ChatRepository

}