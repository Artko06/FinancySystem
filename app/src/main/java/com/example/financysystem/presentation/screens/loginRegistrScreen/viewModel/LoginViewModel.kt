package com.example.financysystem.presentation.screens.loginRegistrScreen.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.models.auth_reg.LoginInputValidationType
import com.example.domain.useCase.UserRoleUseCases.StartUserUseCases
import com.example.financysystem.presentation.screens.loginRegistrScreen.event.LoginEvent
import com.example.financysystem.presentation.screens.loginRegistrScreen.state.LoginState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val startUserUseCases: StartUserUseCases
) : ViewModel() {
    private val _loginState = MutableStateFlow(LoginState())
    val loginState = _loginState.asStateFlow()

    fun onEvent(event: LoginEvent) {
        when (event) {
            is LoginEvent.onEmailInputChange -> {
                _loginState.update { it.copy(
                    email = event.newValue
                ) }
            }

            LoginEvent.onLoginClick -> {
                login()
            }

            is LoginEvent.onPasswordInputChange -> {
                _loginState.update { it.copy(
                    password = event.newValue
                ) }
            }

            LoginEvent.onSendCodeToEmail -> {
                processInputValidationType(
                loginInputValidationType = startUserUseCases.validateLoginInputUseCase.invoke(
                    email = _loginState.value.email,
                    password = _loginState.value.password
                    )
                )
            }

            LoginEvent.onToggleVisualTransformation -> {
                _loginState.update { it.copy(
                    isPasswordShown = !it.isPasswordShown
                ) }
            }

            is LoginEvent.onTypeOfUserInputChange -> {
                _loginState.update { it.copy(
                    typeOfUser = event.typeOfUser
                ) }
            }

        }
    }

    private fun login() {
        viewModelScope.launch {
            val emailResult = startUserUseCases.validateEmailUseCase.invoke(
                email = _loginState.value.email
            )

            if (!emailResult){
                _loginState.update { it.copy(isSuccessfullyLoggedIn = false) }
                _loginState.update{ it.copy(errorMessageLoginProcess = "Incorrect email") }
            } else{
                val loginResult = startUserUseCases.validatePasswordUseCase.invoke(
                    email = _loginState.value.email,
                    password = _loginState.value.password
                )

                if(loginResult){
                    _loginState.update { it.copy(isSuccessfullyLoggedIn = true) }
                } else{
                    _loginState.update { it.copy(isSuccessfullyLoggedIn = false) }
                    _loginState.update{ it.copy(errorMessageLoginProcess = "Incorrect password") }
                }
            }
        }
    }

    private fun processInputValidationType(loginInputValidationType: LoginInputValidationType) {
        _loginState.update {
            when (loginInputValidationType) {
                LoginInputValidationType.EmptyField -> it.copy(
                    errorMessageInput = "Empty fields left",
                    isInputValid = false
                )
                LoginInputValidationType.NoEmail -> it.copy(
                    errorMessageInput = "No valid email",
                    isInputValid = false
                )
                LoginInputValidationType.Valid -> it.copy(
                    errorMessageInput = null,
                    isInputValid = true
                )
            }
        }
    }
}