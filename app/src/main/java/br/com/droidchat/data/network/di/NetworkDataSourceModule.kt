package br.com.droidchat.data.network.di

import br.com.droidchat.data.network.NetworkDataSource
import br.com.droidchat.data.network.NetworkDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface NetworkDataSourceModule {

    @Binds
    @Singleton
    fun bindNetworkDataSource(impl: NetworkDataSourceImpl): NetworkDataSource
}