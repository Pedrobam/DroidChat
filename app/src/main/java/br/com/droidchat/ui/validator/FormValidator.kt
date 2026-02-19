package br.com.droidchat.ui.validator

interface FormValidator<FormState> {
    fun validate(formState: FormState): FormState
}