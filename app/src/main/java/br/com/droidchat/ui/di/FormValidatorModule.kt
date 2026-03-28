package br.com.droidchat.ui.di

import br.com.droidchat.ui.feature.signin.SignInFormState
import br.com.droidchat.ui.feature.signin.SignInFormValidator
import br.com.droidchat.ui.feature.signup.SignUpFormState
import br.com.droidchat.ui.feature.signup.SignUpFormValidator
import br.com.droidchat.ui.validator.FormValidator
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface FormValidatorModule {

    @Binds
    fun bindSignInFormValidator(impl: SignInFormValidator): FormValidator<SignInFormState>

    @Binds
    fun bindSignUpFormValidator(impl: SignUpFormValidator): FormValidator<SignUpFormState>
}