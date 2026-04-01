package br.com.droidchat.data.manager

import android.content.Context
import br.com.droidchat.data.datastore.TokensKeys
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class SecureTokenManagerImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : TokenManager {
    override val accessToken: Flow<String>
        get() = flowOf(CryptoManager.decryptData(context, TokensKeys.ACCESS_TOKEN.name))

    override suspend fun saveAccessToken(accessToken: String) {
        CryptoManager.encryptData(context, TokensKeys.ACCESS_TOKEN.name, accessToken)
    }

    override suspend fun clearAccessToken() {
        CryptoManager.encryptData(context, TokensKeys.ACCESS_TOKEN.name, "")
    }
}