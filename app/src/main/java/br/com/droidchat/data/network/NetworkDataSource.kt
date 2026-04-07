package br.com.droidchat.data.network

import br.com.droidchat.data.network.model.AuthRequest
import br.com.droidchat.data.network.model.CreateAccountRequest
import br.com.droidchat.data.network.model.ImageResponse
import br.com.droidchat.data.network.model.PaginatedChatResponse
import br.com.droidchat.data.network.model.PaginationParams
import br.com.droidchat.data.network.model.TokenResponse
import br.com.droidchat.data.network.model.UserResponse

interface NetworkDataSource {

    suspend fun signUp(request: CreateAccountRequest)

    suspend fun signIn(request: AuthRequest): TokenResponse

    suspend fun uploadProfilePicture(filePath: String): ImageResponse

    suspend fun authenticate(token: String): UserResponse

    suspend fun getChats(token: String, paginationParams: PaginationParams): PaginatedChatResponse
}