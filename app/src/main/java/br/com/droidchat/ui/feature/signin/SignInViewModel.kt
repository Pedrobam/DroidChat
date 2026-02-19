package br.com.droidchat.ui.feature.signin

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import br.com.droidchat.ui.validator.FormValidator

class SignInViewModel(
    private val formValidator: FormValidator<SignInFormState>
) : ViewModel() {

    var formState by mutableStateOf(SignInFormState())
        private set

    fun onEventForm(event: SignInFormEvent) {
        when (event) {
            is SignInFormEvent.EmailChanged -> {
                formState = formState.copy(email = event.email, emailError = null)
            }

            is SignInFormEvent.PasswordChanged -> {
                formState = formState.copy(password = event.password, passwordError = null)
            }

            SignInFormEvent.Submit -> {
                doSignIn()
            }
        }
    }

    private fun doSignIn() {
//        resetFormErrorState()
        if (isValidForm()) {
            formState = formState.copy(isLoading = true)
        }
    }

    private fun isValidForm(): Boolean {
        return !formValidator.validate(formState).also {
            formState = it
        }.hasError
    }

    private fun resetFormErrorState() {
        formState = formState.copy(
            emailError = null,
            passwordError = null
        )

    }
}