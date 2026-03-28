package br.com.droidchat.model

data class CreateAccountDomain(
    val username:String,
    val password: String,
    val firstName: String,
    val lastName: String,
    val profilePictureId: Int?,
)
