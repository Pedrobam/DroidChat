package br.com.droidchat.data.repository

import android.util.Log
import br.com.droidchat.data.di.IoDispatcher
import br.com.droidchat.data.manager.TokenManager
import br.com.droidchat.data.network.NetworkDataSource
import br.com.droidchat.data.network.model.AuthRequest
import br.com.droidchat.data.network.model.CreateAccountRequest
import br.com.droidchat.model.CreateAccountDomain
import br.com.droidchat.model.ImageDomain
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val networkDataSource: NetworkDataSource,
    private val tokenManager: TokenManager,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : AuthRepository {

    init {
        GlobalScope.launch(ioDispatcher) {
            Log.d("AuthRepositoryImpl", "init: ${tokenManager.accessToken.first()}")
        }
    }

    override suspend fun signUp(createAccountDomain: CreateAccountDomain): Result<Unit> {
        return withContext(ioDispatcher) {
            runCatching {
                networkDataSource.signUp(
                    request = CreateAccountRequest(
                        firstName = createAccountDomain.firstName,
                        lastName = createAccountDomain.lastName,
                        username = createAccountDomain.username,
                        password = createAccountDomain.password,
                        profilePictureId = createAccountDomain.profilePictureId
                    )
                )
            }
        }
    }

    override suspend fun signIn(username: String, password: String): Result<Unit> {
        return withContext(ioDispatcher) {
            runCatching {
                val tokenResponse = networkDataSource.signIn(
                    request = AuthRequest(
                        username = username,
                        password = password
                    )
                )
                tokenManager.saveAccessToken(tokenResponse.token)
            }
        }
    }

    override suspend fun uploadProfilePicture(filePath: String): Result<ImageDomain> {
        return withContext(ioDispatcher) {
            runCatching {
                val imageResponse = networkDataSource.uploadProfilePicture(filePath)
                ImageDomain(
                    id = imageResponse.id,
                    name = imageResponse.name,
                    type = imageResponse.type,
                    url = imageResponse.url
                )
            }
        }
    }
}