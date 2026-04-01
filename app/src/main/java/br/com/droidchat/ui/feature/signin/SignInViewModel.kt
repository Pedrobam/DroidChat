package br.com.droidchat.ui.feature.signin

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.droidchat.data.repository.AuthRepository
import br.com.droidchat.model.NetworkException
import br.com.droidchat.ui.validator.FormValidator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val formValidator: FormValidator<SignInFormState>,
    private val authRepository: AuthRepository,
) : ViewModel() {

    var formState by mutableStateOf(SignInFormState())
        private set

    private val _signInActionFlow = MutableSharedFlow<SignInAction>()
    val signInActionFlow = _signInActionFlow.asSharedFlow()


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
        if (isValidForm()) {
            formState = formState.copy(isLoading = true)
            viewModelScope.launch {
                authRepository.signIn(
                    username = formState.email,
                    password = formState.password
                ).fold(
                    onSuccess = {
                        formState = formState.copy(isLoading = false)
                        _signInActionFlow.emit(SignInAction.Success)
                    },
                    onFailure = {
                        formState = formState.copy(isLoading = false)
                        val error = if (it is NetworkException.ApiException) {
                            SignInAction.Error.UnauthorizedError
                        } else {
                            SignInAction.Error.GenericError
                        }
                        _signInActionFlow.emit(error)
                    }
                )
            }
        }
    }

    private fun isValidForm(): Boolean {
        return !formValidator.validate(formState).also {
            formState = it
        }.hasError
    }

    sealed interface SignInAction {
        data object Success : SignInAction
        sealed interface Error : SignInAction {
            data object GenericError : Error
            data object UnauthorizedError : Error
        }
    }
}