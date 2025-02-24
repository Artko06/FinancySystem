package com.example.financysystem.presentation.screens.loginRegistrScreen.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.models.auth_reg.RegisterInputValidationType
import com.example.domain.models.user.TypeOfUser
import com.example.domain.useCase.UserRoleUseCases.StartUserUseCases
import com.example.financysystem.presentation.screens.loginRegistrScreen.event.RegistrationEvent
import com.example.financysystem.presentation.screens.loginRegistrScreen.state.RegistrationState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegistrationViewModel @Inject constructor(
    private val startUserUseCases: StartUserUseCases
): ViewModel()
{
    private val _regState = MutableStateFlow(RegistrationState())
    val regState = _regState.asStateFlow()

    fun onEvent(event: RegistrationEvent) {
        when(event){
            is RegistrationEvent.onEmailInputChange -> {
                _regState.update { it.copy(
                    emailInput = event.newValue
                )
                }
            }

            is RegistrationEvent.onPasswordInputChange -> {
                _regState.update { it.copy(
                    passwordInput = event.newValue
                )
                }
            }

            is RegistrationEvent.onPasswordRepeatedInputChange -> {
                _regState.update { it.copy(
                    passwordRepeatedInput = event.newValue
                )
                }
            }

            RegistrationEvent.onRegisterClick -> {
                processInputValidationType(
                    registerInputValidationType = startUserUseCases.validateRegisterInputUseCase.invoke(
                        email = _regState.value.emailInput,
                        password = _regState.value.passwordInput,
                        passwordRepeated = _regState.value.passwordRepeatedInput,
                        firstName = _regState.value.firstNameInput,
                        lastName = _regState.value.lastNameInput,
                        surName = _regState.value.surNameInput,
                        phone = _regState.value.phoneInput,
                        identityNumber = _regState.value.identityNumberInput,
                        seriesPassport = _regState.value.seriesPassportInput,
                        numberPassport = _regState.value.numberPassportInput
                    )
                )

                if(_regState.value.isInputValid) Registration()
            }

            RegistrationEvent.onToggleVisualTransformationPassword -> {
                _regState.update { it.copy(
                    isPasswordShown = !it.isPasswordShown
                )
                }
            }

            RegistrationEvent.onToggleVisualTransformationPasswordRepeated -> {
                _regState.update { it.copy(
                    isPasswordRepeatedShown = !it.isPasswordRepeatedShown
                )
                }
            }

            is RegistrationEvent.onFirstNameInputChange -> {
                _regState.update { it.copy(
                    firstNameInput = event.newValue
                )
                }
            }

            is RegistrationEvent.onIdentityNumberInputChange -> {
                _regState.update { it.copy(
                    identityNumberInput = event.newValue
                )
                }
            }

            is RegistrationEvent.onLastNameInputChange -> {
                _regState.update { it.copy(
                    lastNameInput = event.newValue
                )
                }
            }

            is RegistrationEvent.onNumberPassportInputChange -> {
                _regState.update { it.copy(
                    numberPassportInput = event.newValue
                )
                }
            }

            is RegistrationEvent.onPhoneInputChange -> {
                _regState.update { it.copy(
                    phoneInput = event.newValue
                )
                }
            }

            is RegistrationEvent.onSeriesPassportInputChange -> {
                _regState.update { it.copy(
                    seriesPassportInput = event.newValue
                )
                }
            }

            is RegistrationEvent.onSurNameInputChange -> {
                _regState.update { it.copy(
                    surNameInput = event.newValue
                )
                }
            }

        }
    }

    private fun Registration(){
        viewModelScope.launch {
            _regState.update { it.copy(isLoading = true) }
            val emailResult = startUserUseCases.validateEmailUseCase.invoke(
                email = _regState.value.emailInput
            )

            when(emailResult){
                true -> {
                    _regState.update { it.copy(
                        isSuccessfullyRegistered = false,
                        errorMessageInput = "This email is already registered",
                        isLoading = false
                    )
                    }
                }
                false -> {
                    startUserUseCases.insertClientUserUseCase.invoke(
                        email = _regState.value.emailInput,
                        password = _regState.value.passwordInput,
                        firstName = _regState.value.firstNameInput,
                        lastName = _regState.value.lastNameInput,
                        surName = _regState.value.surNameInput,
                        phone = _regState.value.phoneInput,
                        identityNumber = _regState.value.identityNumberInput,
                        seriesPassport = _regState.value.seriesPassportInput,
                        numberPassport = _regState.value.numberPassportInput,
                        typeOfUser = TypeOfUser.ClientUser
                    )

                    _regState.update { it.copy(
                        isSuccessfullyRegistered = true,
                        errorMessageInput = null,
                        isLoading = false
                    )
                    }
                }
            }
        }
    }


    private fun processInputValidationType(registerInputValidationType: RegisterInputValidationType){
        _regState.update {
            when(registerInputValidationType){
                RegisterInputValidationType.EmptyField -> {
                    it.copy(
                        errorMessageInput = "Empty fields",
                        isInputValid = false
                    )
                }

                RegisterInputValidationType.IncorrectEmail -> {
                    it.copy(
                        errorMessageInput = "Incorrect email address",
                        isInputValid = false
                    )
                }

                RegisterInputValidationType.PasswordTooShort -> {
                    it.copy(
                        errorMessageInput = "Password too short",
                        isInputValid = false
                    )
                }

                RegisterInputValidationType.PasswordsDoNotMatch -> {
                    it.copy(
                        errorMessageInput = "Passwords do not match",
                        isInputValid = false
                    )
                }

                RegisterInputValidationType.PasswordLowerCaseMissing -> {
                    it.copy(
                        errorMessageInput = "Password needs to contain at least one lower case char",
                        isInputValid = false
                    )
                }

                RegisterInputValidationType.PasswordUpperCaseMissing -> {
                    it.copy(
                        errorMessageInput = "Password needs to contain at least one upper case char",
                        isInputValid = false
                    )
                }

                RegisterInputValidationType.PasswordNumberMissing -> {
                    it.copy(
                        errorMessageInput = "Password needs to contain at least one number",
                        isInputValid = false
                    )
                }

                RegisterInputValidationType.IncorrectPhone -> {
                    it.copy(
                        errorMessageInput = "Incorrect phone number",
                        isInputValid = false
                    )
                }

                RegisterInputValidationType.Valid -> {
                    it.copy(
                        errorMessageInput = null,
                        isInputValid = true
                    )
                }
            }
        }
    }
}