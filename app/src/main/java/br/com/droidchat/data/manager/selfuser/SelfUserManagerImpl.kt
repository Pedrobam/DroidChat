package br.com.droidchat.data.manager.selfuser

import android.content.Context
import br.com.droidchat.SelfUser
import br.com.droidchat.data.datastore.selfUserStore
import br.com.droidchat.data.di.IoDispatcher
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SelfUserManagerImpl @Inject constructor(
    @ApplicationContext context: Context,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
): SelfUserManager {

    private val selfUserStore = context.selfUserStore

    override val selfUserFlow: Flow<SelfUser>
        get() = selfUserStore.data

    override suspend fun saveSelfUser(
        id: Int,
        firstName: String,
        lastName: String,
        profilePictureUrl: String,
        username: String
    ) {
        withContext(dispatcher) {
            selfUserStore.updateData {
                it.toBuilder()
                    .setId(id)
                    .setFirstName(firstName)
                    .setLastName(lastName)
                    .setProfilePictureUrl(profilePictureUrl)
                    .setUsername(username)
                    .build()
            }
        }
    }

    override suspend fun clearSelfUser() {
        withContext(dispatcher) {
            selfUserStore.updateData {
                it.toBuilder()
                    .clearId()
                    .clearFirstName()
                    .clearLastName()
                    .clearProfilePictureUrl()
                    .clearUsername()
                    .build()
            }
        }
    }
}