package com.example.financysystem.presentation.screens.loginRegistrScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material.icons.filled.VpnKey
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
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
import com.example.financysystem.presentation.screens.loginRegistrScreen.viewModel.LoginViewModel
import com.example.financysystem.ui.theme.gray
import com.example.financysystem.ui.theme.green
import com.example.financysystem.ui.theme.redOrange
import com.example.financysystem.ui.theme.white
import com.example.financysystem.ui.theme.whiteGray

@Composable
fun LoginScreen(
    onLoginSuccessNavigation: (String) -> Unit,
    onNavigateToRegisterScreen: () -> Unit,
    loginViewModel: LoginViewModel = hiltViewModel()
) {
    val stateViewModel = loginViewModel.loginState.collectAsState().value

    NavHelper(
        shouldNavigate = { stateViewModel.isSuccessfullyLoggedIn },
        toNavigate = { onLoginSuccessNavigation(stateViewModel.email) }
    )

    Scaffold { paddingValues ->
        Box(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background)
                .fillMaxSize()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(paddingValues.calculateTopPadding() + 75.dp),
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
                    stateViewModel.email
                },
                passwordValue = {
                    stateViewModel.password
                },
                onEmailChanged = { newEmail ->
                    loginViewModel.onEvent(LoginEvent.onEmailInputChange(newValue = newEmail))
                },
                onPasswordChanged = { newPassword ->
                    loginViewModel.onEvent(LoginEvent.onPasswordInputChange(newValue = newPassword))
                },
                onLoginButtonClick = { loginViewModel.onEvent(LoginEvent.onLoginClick) },
                isPasswordShown = {
                    stateViewModel.isPasswordShown
                },
                onTrailingPasswordIconClick = {
                    loginViewModel.onEvent(LoginEvent.onToggleVisualTransformation)
                },
                errorHint = {
                    stateViewModel.errorMessageInput
                },
                isLoading = {
                    stateViewModel.isLoading
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
                    .padding(bottom = 20.dp)
                    .align(Alignment.BottomCenter),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "No account yet?",
                    color = MaterialTheme.colorScheme.onBackground
                )
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
}

@Composable
fun LoginContainer(
    emailValue: () -> String,
    passwordValue: () -> String,
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
        Column(
            modifier = Modifier.fillMaxHeight(0.3f).verticalScroll(rememberScrollState())
        ) {
            TextEntry(
                modifier = Modifier
                    .fillMaxWidth(),
                description = "Email address",
                hint = "KOXAN@bsuir.by",
                textValue = emailValue(),
                keyboardType = KeyboardType.Email,
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
                trailingIcon = if (isPasswordShown()) Icons.Default.Visibility
                else Icons.Default.VisibilityOff,
                onTrailingIconClick = {
                    onTrailingPasswordIconClick()
                },
                leadingIcon = Icons.Default.VpnKey,
                visualTransformation = if (isPasswordShown()) {
                    VisualTransformation.None
                } else PasswordVisualTransformation(),
                keyboardType = KeyboardType.Password
            )
        }
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            AuthButton(
                text = "Login",
                backgroundColor = green,
                contentColor = white,
                enabled = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(45.dp)
                    .shadow(5.dp, RoundedCornerShape(15.dp)),
                isLoading = isLoading(),
                onButtonClick = onLoginButtonClick
            )
            Text(
                text = errorHint() ?: "",
                color = redOrange
                )
        }
    }
}