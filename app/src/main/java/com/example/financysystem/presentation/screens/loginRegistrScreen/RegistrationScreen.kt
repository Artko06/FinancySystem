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
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.SwitchAccount
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material.icons.filled.VpnKey
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
import com.example.financysystem.presentation.screens.loginRegistrScreen.event.RegistrationEvent
import com.example.financysystem.presentation.screens.loginRegistrScreen.viewModel.RegistrationViewModel
import com.example.financysystem.ui.theme.gray
import com.example.financysystem.ui.theme.green
import com.example.financysystem.ui.theme.redOrange
import com.example.financysystem.ui.theme.white
import com.example.financysystem.ui.theme.whiteGray

@Composable
fun RegistrationScreen(
    onRegistrationSuccessNavigation: () -> Unit,
    onNavigateToLoginScreen: () -> Unit,
    registrationViewModel: RegistrationViewModel = hiltViewModel()
) {
    val stateViewModel = registrationViewModel.regState.collectAsState().value

    NavHelper(
        shouldNavigate = { stateViewModel.isSuccessfullyRegistered },
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
                text = "Registration",
                color = white,
                fontWeight = FontWeight.SemiBold
            )
        }
        RegisterContainer(
            emailValue = {
                stateViewModel.emailInput
            },
            passwordValue = {
                stateViewModel.passwordInput
            },
            passwordRepeatedValue = {
                stateViewModel.passwordRepeatedInput
            },
            phoneValue = {
                stateViewModel.phoneInput
            },
            firstNameValue = {
                stateViewModel.firstNameInput
            },
            lastNameValue = {
                stateViewModel.lastNameInput
            },
            surNameValue = {
                stateViewModel.surNameInput
            },
            seriesPassportValue = {
                stateViewModel.seriesPassportInput
            },
            numberPassportValue = {
                stateViewModel.numberPassportInput
            },
            identityNumberValue = {
                stateViewModel.identityNumberInput
            },
            onEmailChanged = { newEmail ->
                registrationViewModel.onEvent(RegistrationEvent.onEmailInputChange(newValue = newEmail))
            },
            onPasswordChanged = { newPassword ->
                registrationViewModel.onEvent(RegistrationEvent.onPasswordInputChange(newValue = newPassword))
            },
            onPasswordRepeatedChanged = { newPasswordRepeated ->
                registrationViewModel.onEvent(
                    RegistrationEvent.onPasswordRepeatedInputChange(
                        newValue = newPasswordRepeated
                    )
                )
            },
            onPhoneInputChanged = { newPhone ->
                registrationViewModel.onEvent(RegistrationEvent.onPhoneInputChange(newValue = newPhone))
            },
            onFirstNameChanged = { newFirstName ->
                registrationViewModel.onEvent(RegistrationEvent.onFirstNameInputChange(newValue = newFirstName))
            },
            onLastNameChanged = { newLastName ->
                registrationViewModel.onEvent(RegistrationEvent.onLastNameInputChange(newValue = newLastName))
            },
            onSurNameChanged = { newSurName ->
                registrationViewModel.onEvent(RegistrationEvent.onSurNameInputChange(newValue = newSurName))
            },
            onSeriesPassportChanged = { newSeriesPassport ->
                registrationViewModel.onEvent(RegistrationEvent.onSeriesPassportInputChange(newValue = newSeriesPassport))
            },
            onNumberPassportChanged = { newNumberPassport ->
                registrationViewModel.onEvent(RegistrationEvent.onNumberPassportInputChange(newValue = newNumberPassport))
            },
            onIdentityNumberChanged = { newIdentityNumber ->
                registrationViewModel.onEvent(RegistrationEvent.onIdentityNumberInputChange(newValue = newIdentityNumber))
            },
            onButtonClick = { registrationViewModel.onEvent(RegistrationEvent.onRegisterClick) },
            isPasswordShown = {
                stateViewModel.isPasswordShown
            },
            isPasswordRepeatedShown = {
                stateViewModel.isPasswordRepeatedShown
            },
            onTrailingPasswordIconClick = {
                registrationViewModel.onEvent(RegistrationEvent.onToggleVisualTransformationPassword)
            },
            onTrailingPasswordRepeatedIconClick = {
                registrationViewModel.onEvent(RegistrationEvent.onToggleVisualTransformationPasswordRepeated)
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
            bubbleColor1 = gray,
            bubbleColor2 = green,
            modifier = Modifier
                .fillMaxWidth()
                .height(220.dp)
                .align(Alignment.BottomCenter),
        )
        Row(
            modifier = Modifier
                .padding(bottom = 20.dp)
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
    phoneValue: () -> String,
    firstNameValue: () -> String,
    lastNameValue: () -> String,
    surNameValue: () -> String,
    seriesPassportValue: () -> String,
    numberPassportValue: () -> String,
    identityNumberValue: () -> String,
    onEmailChanged: (String) -> Unit,
    onPasswordChanged: (String) -> Unit,
    onPasswordRepeatedChanged: (String) -> Unit,
    onPhoneInputChanged: (String) -> Unit,
    onFirstNameChanged: (String) -> Unit,
    onLastNameChanged: (String) -> Unit,
    onSurNameChanged: (String) -> Unit,
    onSeriesPassportChanged: (String) -> Unit,
    onNumberPassportChanged: (String) -> Unit,
    onIdentityNumberChanged: (String) -> Unit,
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
        Column(
            modifier = Modifier
                .fillMaxHeight(0.4f)
                .verticalScroll(rememberScrollState())
        ) {
            TextEntry(
                modifier = Modifier
                    .fillMaxWidth(),
                description = "Last name",
                hint = "Иванов",
                leadingIcon = Icons.Default.Person,
                textValue = lastNameValue(),
                keyboardType = KeyboardType.Unspecified,
                textColor = gray,
                cursorColor = green,
                onValueChanged = onLastNameChanged,
                trailingIcon = null,
                onTrailingIconClick = null
            )

            TextEntry(
                modifier = Modifier
                    .fillMaxWidth(),
                description = "First name",
                hint = "Иван",
                leadingIcon = Icons.Default.Person,
                textValue = firstNameValue(),
                keyboardType = KeyboardType.Unspecified,
                textColor = gray,
                cursorColor = green,
                onValueChanged = onFirstNameChanged,
                trailingIcon = null,
                onTrailingIconClick = null
            )

            TextEntry(
                modifier = Modifier
                    .fillMaxWidth(),
                description = "Surname",
                hint = "Иванович",
                leadingIcon = Icons.Default.Person,
                textValue = surNameValue(),
                keyboardType = KeyboardType.Unspecified,
                textColor = gray,
                cursorColor = green,
                onValueChanged = onSurNameChanged,
                trailingIcon = null,
                onTrailingIconClick = null
            )

            TextEntry(
                modifier = Modifier
                    .fillMaxWidth(),
                description = "Phone",
                hint = "+375331235566",
                leadingIcon = Icons.Default.Phone,
                textValue = phoneValue(),
                keyboardType = KeyboardType.Phone,
                textColor = gray,
                cursorColor = green,
                onValueChanged = onPhoneInputChanged,
                trailingIcon = null,
                onTrailingIconClick = null
            )

            TextEntry(
                modifier = Modifier
                    .fillMaxWidth(),
                description = "Series passport",
                hint = "HB",
                leadingIcon = Icons.Default.AccountBox,
                textValue = seriesPassportValue(),
                keyboardType = KeyboardType.Unspecified,
                textColor = gray,
                cursorColor = green,
                onValueChanged = onSeriesPassportChanged,
                trailingIcon = null,
                onTrailingIconClick = null
            )

            TextEntry(
                modifier = Modifier
                    .fillMaxWidth(),
                description = "Number passport",
                hint = "1234567",
                leadingIcon = Icons.Default.AccountBox,
                textValue = numberPassportValue(),
                keyboardType = KeyboardType.Unspecified,
                textColor = gray,
                cursorColor = green,
                onValueChanged = onNumberPassportChanged,
                trailingIcon = null,
                onTrailingIconClick = null
            )

            TextEntry(
                modifier = Modifier
                    .fillMaxWidth(),
                description = "Identity number passport",
                hint = "7637905A001PB6",
                leadingIcon = Icons.Default.SwitchAccount,
                textValue = identityNumberValue(),
                keyboardType = KeyboardType.Unspecified,
                textColor = gray,
                cursorColor = green,
                onValueChanged = onIdentityNumberChanged,
                trailingIcon = null,
                onTrailingIconClick = null
            )

            TextEntry(
                modifier = Modifier
                    .fillMaxWidth(),
                description = "Email address",
                hint = "KOXAN@bsuir.by",
                leadingIcon = Icons.Default.Email,
                textValue = emailValue(),
                textColor = gray,
                keyboardType = KeyboardType.Email,
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
                trailingIcon = if (isPasswordShown()) Icons.Default.Visibility
                else Icons.Default.VisibilityOff,
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
                trailingIcon = if (isPasswordRepeatedShown()) Icons.Default.Visibility
                else Icons.Default.VisibilityOff,
                onTrailingIconClick = {
                    onTrailingPasswordRepeatedIconClick()
                }
            )
        }
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            AuthButton(
                text = "Registration",
                backgroundColor = green,
                contentColor = white,
                enabled = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(45.dp)
                    .shadow(5.dp, RoundedCornerShape(15.dp)),
                onButtonClick = onButtonClick,
                isLoading = isLoading()
            )
            Text(
                text = errorHint() ?: "",
                color = redOrange
                )
        }
    }
}