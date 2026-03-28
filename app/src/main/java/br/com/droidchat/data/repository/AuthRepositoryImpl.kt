package br.com.droidchat.data.repository

import br.com.droidchat.data.di.IoDispatcher
import br.com.droidchat.data.network.NetworkDataSource
import br.com.droidchat.data.network.model.AuthRequest
import br.com.droidchat.data.network.model.CreateAccountRequest
import br.com.droidchat.model.CreateAccountDomain
import br.com.droidchat.model.ImageDomain
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val networkDataSource: NetworkDataSource,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : AuthRepository {
    override suspend fun signUp(createAccountDomain: CreateAccountDomain): Result<Unit> {
        return withContext(dispatcher) {
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

    override suspend fun signIn(username: String, password: String) {
        return withContext(dispatcher) {
            networkDataSource.signIn(
                request = AuthRequest(
                    username = username,
                    password = password
                )
            )
        }
    }

    override suspend fun uploadProfilePicture(filePath: String): Result<ImageDomain> {
        return withContext(dispatcher) {
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