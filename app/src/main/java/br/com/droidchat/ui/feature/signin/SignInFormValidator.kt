package br.com.droidchat.ui.feature.signin

import br.com.droidchat.R
import br.com.droidchat.ui.validator.EmailValidator
import br.com.droidchat.ui.validator.FormValidator
import br.com.droidchat.ui.validator.PasswordValidator

class SignInFormValidator : FormValidator<SignInFormState> {

    override fun validate(formState: SignInFormState): SignInFormState {
        val isEmailValid = EmailValidator.isValid(formState.email)
        val isPasswordValid = PasswordValidator.isValid(formState.password)

        val hasError = listOf(
            isEmailValid,
            isPasswordValid,
        ).any { !it }

        return formState.copy(
            emailError = if (!isEmailValid) R.string.error_message_email_invalid else null,
            passwordError = if (!isPasswordValid) R.string.error_message_password_invalid else null,
            hasError = hasError
        )
    }

}