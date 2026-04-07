package br.com.droidchat.data.repository

import br.com.droidchat.model.Chat

interface ChatRepository {

    suspend fun getChats(
        offset: Int, limit: Int
    ): Result<List<Chat>>
}