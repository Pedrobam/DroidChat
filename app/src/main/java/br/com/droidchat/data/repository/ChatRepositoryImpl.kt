package br.com.droidchat.data.repository

import br.com.droidchat.data.di.IoDispatcher
import br.com.droidchat.data.manager.selfuser.SelfUserManager
import br.com.droidchat.data.manager.token.TokenManager
import br.com.droidchat.data.mapper.toDomainModel
import br.com.droidchat.data.network.NetworkDataSource
import br.com.droidchat.data.network.model.PaginationParams
import br.com.droidchat.model.Chat
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ChatRepositoryImpl @Inject constructor(
    private val networkDataSource: NetworkDataSource,
    private val tokenManager: TokenManager,
    private val selfUserManager: SelfUserManager,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : ChatRepository {

    override suspend fun getChats(
        offset: Int,
        limit: Int
    ): Result<List<Chat>> {
        return withContext(ioDispatcher) {
            runCatching {
                val token = tokenManager.accessToken.firstOrNull() ?: ""
                val paginatedChatResponse = networkDataSource.getChats(
                    token = token,
                    paginationParams = PaginationParams(
                        offset = offset.toString(),
                        limit = limit.toString()
                    )
                )

                val selfUser = selfUserManager.selfUserFlow.firstOrNull()
                paginatedChatResponse.toDomainModel(selfUser?.id)
            }
        }
    }
}