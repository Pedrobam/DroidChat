package br.com.droidchat.data.network.model

import kotlinx.serialization.Serializable

@Serializable
data class CreateAccountRequest(
    val firstName: String,
    val lastName: String,
    val username: String,
    val password: String,
    val profilePictureId: Int?
)
