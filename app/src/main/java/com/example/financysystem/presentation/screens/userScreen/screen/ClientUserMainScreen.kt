package com.example.financysystem.presentation.screens.userScreen.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBalanceWallet
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Factory
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.financysystem.presentation.screens.components.BottomNavItem
import com.example.financysystem.presentation.screens.components.BottomNavigationBar
import com.example.financysystem.presentation.screens.components.HeaderBackground
import com.example.financysystem.presentation.screens.userScreen.event.ClientUserEvent
import com.example.financysystem.presentation.screens.userScreen.screen.contentClientUserScreen.clientUser.ClientUserBankAccountScreen
import com.example.financysystem.presentation.screens.userScreen.screen.contentClientUserScreen.clientUser.ClientUserProfileScreen
import com.example.financysystem.presentation.screens.userScreen.screen.contentClientUserScreen.clientUser.ClientUserSalaryProjectScreen
import com.example.financysystem.presentation.screens.userScreen.state.ClientUserState
import com.example.financysystem.presentation.screens.userScreen.state.contentState.ClientSelectedContent
import com.example.financysystem.presentation.screens.userScreen.viewModel.ClientUserViewModel
import com.example.financysystem.ui.theme.gray
import com.example.financysystem.ui.theme.green
import com.example.financysystem.ui.theme.white

@Composable
fun ClientUserMainScreen(
    clientUserViewModel: ClientUserViewModel = hiltViewModel()
)
{
    val navItems = listOf(
        BottomNavItem(label = "Профиль", icon = Icons.Filled.AccountCircle),
        BottomNavItem(label = "Счета", icon =  Icons.Filled.AccountBalanceWallet),
        BottomNavItem(label = "Проекты", icon = Icons.Filled.Factory),
    )

    val clientUserState = clientUserViewModel.clientUserState.collectAsState().value

    Scaffold(
        bottomBar = {
            BottomNavigationBar(
                items = navItems,
                modifier = Modifier.navigationBarsPadding().height(75.dp),
                selectedNavItem = clientUserState.clientSelectedContent.selectedContent,
                onSelectNavItem = { index ->
                    val selectedEnum = ClientSelectedContent.entries.find { it.selectedContent == index }!!

                    clientUserViewModel.onEvent(
                        event = ClientUserEvent.onContentWindowChange(selectedEnum)
                    )
                }
            )
        }
    ) { paddingValues ->

        Box(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background)
                .fillMaxSize()
        )
        {
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
                    text = navItems[clientUserState.clientSelectedContent.selectedContent].label,
                    color = white,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }

        ContentScreen(
            modifier = Modifier.padding(top = 200.dp),
            clientUserState = clientUserState
        )

    }
}

@Composable
fun ContentScreen(
    clientUserState: ClientUserState,
    modifier: Modifier = Modifier
)
{
    when(clientUserState.clientSelectedContent){
        ClientSelectedContent.PROFILE -> ClientUserProfileScreen(
            modifier = modifier,
            firstName = clientUserState.firstName,
            lastName = clientUserState.lastName,
            surName = clientUserState.surName,
            phone = clientUserState.phone,
            email = clientUserState.email,
        )
        ClientSelectedContent.BANK_ACCOUNT -> ClientUserBankAccountScreen(modifier = modifier)
        ClientSelectedContent.SALARY_PROJECT -> ClientUserSalaryProjectScreen(modifier = modifier)
    }
}






