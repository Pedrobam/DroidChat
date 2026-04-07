package br.com.droidchat.data.manager.selfuser

import br.com.droidchat.SelfUser
import kotlinx.coroutines.flow.Flow

interface SelfUserManager {
    val selfUserFlow: Flow<SelfUser>

    suspend fun saveSelfUser(
        id: Int,
        firstName: String,
        lastName: String,
        profilePictureUrl: String,
        username: String
    )

    suspend fun clearSelfUser()

}