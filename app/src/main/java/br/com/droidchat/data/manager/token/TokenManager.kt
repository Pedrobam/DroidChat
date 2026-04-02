package br.com.droidchat.data.manager.token

import kotlinx.coroutines.flow.Flow

interface TokenManager {
    val accessToken: Flow<String>

    suspend fun saveAccessToken(accessToken: String)

    suspend fun clearAccessToken()
}

