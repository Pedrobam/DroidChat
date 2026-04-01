package br.com.droidchat.data.repository

import br.com.droidchat.model.CreateAccountDomain
import br.com.droidchat.model.ImageDomain
import br.com.droidchat.model.TokenDomain

interface AuthRepository {

    suspend fun signUp(createAccountDomain: CreateAccountDomain): Result<Unit>

    suspend fun signIn(username: String, password: String): Result<Unit>

    suspend fun uploadProfilePicture(filePath: String): Result<ImageDomain>
}