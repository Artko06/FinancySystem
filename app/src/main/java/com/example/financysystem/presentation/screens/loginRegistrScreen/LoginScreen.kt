package com.example.financysystem.presentation.screens.loginRegistrScreen

import android.util.Log
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
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VpnKey
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.domain.useCase.UserRoleUseCases.StartUserUseCases
import com.example.domain.useCase.roles.StartUserRole
import com.example.financysystem.presentation.navigarionScreen.NavHelper
import com.example.financysystem.presentation.screens.components.AuthButton
import com.example.financysystem.presentation.screens.components.BubbleAnimation
import com.example.financysystem.presentation.screens.components.HeaderBackground
import com.example.financysystem.presentation.screens.components.TextEntry
import com.example.financysystem.presentation.screens.loginRegistrScreen.event.LoginEvent
import com.example.financysystem.presentation.screens.loginRegistrScreen.viewModel.LoginViewModel
import com.example.financysystem.ui.theme.gray
import com.example.financysystem.ui.theme.green
import com.example.financysystem.ui.theme.white
import com.example.financysystem.ui.theme.whiteGray

@Composable
fun LoginScreen(
    onLoginSuccessNavigation: () -> Unit,
    onNavigateToRegisterScreen: () -> Unit,
    loginViewModel: LoginViewModel = hiltViewModel()
) {
    NavHelper(
        shouldNavigate = { loginViewModel.loginState.value.isSuccessfullyLoggedIn },
        toNavigate = { onLoginSuccessNavigation() }
    )

    val stateViewModel = loginViewModel.loginState.collectAsState().value

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(white)
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
                text = "Login",
                color = white,
                fontWeight = FontWeight.SemiBold
            )
        }
        LoginContainer(
            emailValue = {
                Log.d("LoginScreen", "Email changed: ${loginViewModel.loginState.value.email}")
                stateViewModel.email
            },
            passwordValue = {
                loginViewModel.loginState.value.password
            },
            buttonEnabled = {
                loginViewModel.loginState.value.isInputValid
            },
            onEmailChanged = { newEmail ->
                loginViewModel.onEvent(LoginEvent.onEmailInputChange(newValue = newEmail))
            },
            onPasswordChanged = { newPassword ->
                loginViewModel.onEvent(LoginEvent.onPasswordInputChange(newValue = newPassword))
            },
            onLoginButtonClick = { loginViewModel.onEvent(LoginEvent.onLoginClick) },
            isPasswordShown = {
                loginViewModel.loginState.value.isPasswordShown
            },
            onTrailingPasswordIconClick = {
                loginViewModel.onEvent(LoginEvent.onToggleVisualTransformation)
            },
            errorHint = {
                loginViewModel.loginState.value.errorMessageInput
            },
            isLoading = {
                loginViewModel.loginState.value.isLoading
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
            bubbleColor1 = green,
            bubbleColor2 = gray,
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
                .align(Alignment.BottomCenter)
        )
        Row(
            modifier = Modifier
                .padding(bottom = 10.dp)
                .align(Alignment.BottomCenter),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(text = "No account yet?")
            Text(
                "Register",
                modifier = Modifier
                    .padding(start = 5.dp)
                    .clickable {
                        onNavigateToRegisterScreen()
                    },
                color = green,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
fun LoginContainer(
    emailValue: () -> String,
    passwordValue: () -> String,
    buttonEnabled: () -> Boolean,
    onEmailChanged: (String) -> Unit,
    onPasswordChanged: (String) -> Unit,
    onLoginButtonClick: () -> Unit,
    isPasswordShown: () -> Boolean,
    onTrailingPasswordIconClick: () -> Unit,
    errorHint: () -> String?,
    isLoading: () -> Boolean,
    modifier: Modifier = Modifier
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
            textValue = emailValue(),
            textColor = gray,
            cursorColor = green,
            onValueChanged = onEmailChanged,
            trailingIcon = null,
            onTrailingIconClick = null,
            leadingIcon = Icons.Default.Email
        )
        TextEntry(
            modifier = Modifier
                .fillMaxWidth(),
            description = "Password",
            hint = "Enter password",
            textValue = passwordValue(),
            textColor = gray,
            cursorColor = green,
            onValueChanged = onPasswordChanged,
            trailingIcon = Icons.Default.Visibility,
            onTrailingIconClick = {
                onTrailingPasswordIconClick()
            },
            leadingIcon = Icons.Default.VpnKey,
            visualTransformation = if (isPasswordShown()) {
                VisualTransformation.None
            } else PasswordVisualTransformation(),
            keyboardType = KeyboardType.Password
        )
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            AuthButton(
                text = "Login",
                backgroundColor = green,
                contentColor = white,
                enabled = buttonEnabled(),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(45.dp)
                    .shadow(5.dp, RoundedCornerShape(15.dp)),
                isLoading = isLoading(),
                onButtonClick = onLoginButtonClick
            )
            Text(text = errorHint() ?: "")
        }
    }
}