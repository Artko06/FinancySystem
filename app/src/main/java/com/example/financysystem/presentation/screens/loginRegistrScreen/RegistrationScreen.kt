package com.example.financysystem.presentation.screens.loginRegistrScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.RemoveRedEye
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VpnKey
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.financysystem.presentation.navigarionScreen.NavHelper
import com.example.financysystem.presentation.screens.components.AuthButton
import com.example.financysystem.presentation.screens.components.BubbleAnimation
import com.example.financysystem.presentation.screens.components.HeaderBackground
import com.example.financysystem.presentation.screens.components.TextEntry
import com.example.financysystem.presentation.screens.loginRegistrScreen.event.LoginEvent
import com.example.financysystem.presentation.screens.loginRegistrScreen.event.RegistrationEvent
import com.example.financysystem.presentation.screens.loginRegistrScreen.viewModel.RegistrationViewModel
import com.example.financysystem.ui.theme.gray
import com.example.financysystem.ui.theme.green
import com.example.financysystem.ui.theme.white
import com.example.financysystem.ui.theme.whiteGray
import com.example.financysystem.ui.theme.whiteGrayOrange

@Composable
fun RegistrationScreen(
    onRegistrationSuccessNavigation: () -> Unit,
    onNavigateToLoginScreen: () -> Unit,
    registrationViewModel: RegistrationViewModel = hiltViewModel()
) {
    NavHelper(
        shouldNavigate = { registrationViewModel.regState.value.isSuccessfullyRegistered },
        toNavigate = { onRegistrationSuccessNavigation() }
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(white),
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp),
            contentAlignment = Alignment.Center
        ) {
            HeaderBackground(
                leftColor = green,
                rightColor = gray,
                modifier = Modifier
                    .fillMaxSize()
            )
            Text(
                text = "Register",
                color = white,
                fontWeight = FontWeight.SemiBold
            )
        }
        RegisterContainer(
            emailValue = {
                registrationViewModel.regState.value.emailInput
            },
            passwordValue = {
                registrationViewModel.regState.value.passwordInput
            },
            passwordRepeatedValue = {
                registrationViewModel.regState.value.passwordRepeatedInput
            },
            buttonEnabled = {
                registrationViewModel.regState.value.isInputValid
            },
            onEmailChanged = { newEmail ->
                registrationViewModel.onEvent(RegistrationEvent.onEmailInputChange(newValue = newEmail))
            },
            onPasswordChanged = { newPassword ->
                registrationViewModel.onEvent(RegistrationEvent.onPasswordInputChange(newValue = newPassword))
            },
            onPasswordRepeatedChanged = { newPasswordRepeated ->
                registrationViewModel.onEvent(RegistrationEvent.onPasswordInputChange(newValue = newPasswordRepeated))
            },
            onButtonClick = { registrationViewModel.onEvent(RegistrationEvent.onRegisterClick) },
            isPasswordShown = {
                registrationViewModel.regState.value.isPasswordShown
            },
            isPasswordRepeatedShown = {
                registrationViewModel.regState.value.isPasswordRepeatedShown
            },
            onTrailingPasswordIconClick = {
                registrationViewModel.onEvent(RegistrationEvent.onToggleVisualTransformationPassword)
            },
            onTrailingPasswordRepeatedIconClick = {
                registrationViewModel.onEvent(RegistrationEvent.onToggleVisualTransformationPasswordRepeated)
            },
            errorHint = {
                registrationViewModel.regState.value.errorMessageInput
            },
            isLoading = {
                registrationViewModel.regState.value.isLoading
            },
            modifier = Modifier
                .padding(top = 200.dp)
                .fillMaxWidth(0.9f)
                .shadow(5.dp, RoundedCornerShape(15.dp))
                .background(whiteGray, RoundedCornerShape(15.dp))
                .padding(10.dp, 15.dp, 10.dp, 5.dp)
                .align(Alignment.TopCenter)
        )
        BubbleAnimation(
            bubbleColor1 = gray,
            bubbleColor2 = green,
            modifier = Modifier
                .fillMaxWidth()
                .height(220.dp)
                .align(Alignment.BottomCenter),
        )
        Row(
            modifier = Modifier
                .padding(bottom = 10.dp)
                .align(Alignment.BottomCenter),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(text = "Already have an account?")
            Text(
                modifier = Modifier
                    .padding(start = 5.dp)
                    .clickable {
                        onNavigateToLoginScreen()
                    },
                text = "Login",
                color = green,
                fontWeight = FontWeight.Bold,
            )
        }
    }
}

@Composable
fun RegisterContainer(
    emailValue: () -> String,
    passwordValue: () -> String,
    passwordRepeatedValue: () -> String,
    buttonEnabled: () -> Boolean,
    onEmailChanged: (String) -> Unit,
    onPasswordChanged: (String) -> Unit,
    onPasswordRepeatedChanged: (String) -> Unit,
    onButtonClick: () -> Unit,
    isPasswordShown: () -> Boolean,
    isPasswordRepeatedShown: () -> Boolean,
    onTrailingPasswordIconClick: () -> Unit,
    onTrailingPasswordRepeatedIconClick: () -> Unit,
    errorHint: () -> String?,
    isLoading: () -> Boolean,
    modifier: Modifier = Modifier,
) {

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(15.dp)
    ) {
        TextEntry(
            modifier = Modifier
                .fillMaxWidth(),
            description = "Email address",
            hint = "KOXAN@bsuir.by",
            leadingIcon = Icons.Default.Email,
            textValue = emailValue(),
            textColor = gray,
            cursorColor = green,
            onValueChanged = onEmailChanged,
            trailingIcon = null,
            onTrailingIconClick = null
        )

        TextEntry(
            modifier = Modifier
                .fillMaxWidth(),
            description = "Password",
            hint = "Enter Password",
            leadingIcon = Icons.Default.VpnKey,
            textValue = passwordValue(),
            textColor = gray,
            cursorColor = green,
            onValueChanged = onPasswordChanged,
            keyboardType = KeyboardType.Password,
            visualTransformation = if (isPasswordShown()) {
                VisualTransformation.None
            } else PasswordVisualTransformation(),
            trailingIcon = Icons.Default.Visibility,
            onTrailingIconClick = {
                onTrailingPasswordIconClick()
            }
        )

        TextEntry(
            modifier = Modifier
                .fillMaxWidth(),
            description = "Password repeated",
            hint = "Enter Password repeated",
            leadingIcon = Icons.Default.VpnKey,
            textValue = passwordRepeatedValue(),
            textColor = gray,
            cursorColor = green,
            onValueChanged = onPasswordRepeatedChanged,
            keyboardType = KeyboardType.Password,
            visualTransformation = if (isPasswordRepeatedShown()) {
                VisualTransformation.None
            } else PasswordVisualTransformation(),
            trailingIcon = Icons.Default.Visibility,
            onTrailingIconClick = {
                onTrailingPasswordRepeatedIconClick()
            }
        )
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            AuthButton(
                text = "Register",
                backgroundColor = green,
                contentColor = white,
                enabled = buttonEnabled(),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(45.dp)
                    .shadow(5.dp, RoundedCornerShape(15.dp)),
                onButtonClick = onButtonClick,
                isLoading = isLoading()
            )
            Text(text = errorHint() ?: "")
        }
    }
}