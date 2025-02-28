package com.example.financysystem.presentation.screens.userScreen.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Factory
import androidx.compose.material.icons.filled.MonetizationOn
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
import com.example.financysystem.presentation.screens.userScreen.event.OperatorUserEvent
import com.example.financysystem.presentation.screens.userScreen.screen.contentClientUserScreen.operatorUser.OperatorUserProfileScreen
import com.example.financysystem.presentation.screens.userScreen.screen.contentClientUserScreen.operatorUser.OperatorUserTransfersScreen
import com.example.financysystem.presentation.screens.userScreen.state.OperatorUserState
import com.example.financysystem.presentation.screens.userScreen.state.contentState.OperatorSelectedContent
import com.example.financysystem.presentation.screens.userScreen.viewModel.OperatorUserViewModel
import com.example.financysystem.ui.theme.gray
import com.example.financysystem.ui.theme.green
import com.example.financysystem.ui.theme.white


@Composable
fun OperatorUserMainScreen(
    operatorUserViewModel: OperatorUserViewModel = hiltViewModel()
)
{
    val navItems = listOf(
        BottomNavItem(label = "Профиль", icon = Icons.Filled.AccountCircle),
        BottomNavItem(label = "Переводы", icon =  Icons.Filled.MonetizationOn),
        BottomNavItem(label = "Проекты", icon = Icons.Filled.Factory),
    )

    val operatorUserState = operatorUserViewModel.operatorUserState.collectAsState().value

    Scaffold(
        bottomBar = {
            BottomNavigationBar(
                items = navItems,
                modifier = Modifier.navigationBarsPadding().height(75.dp),
                selectedNavItem = operatorUserState.operatorSelectedContent.selectedContent,
                onSelectNavItem = { index ->
                    val selectedEnum = OperatorSelectedContent.entries.find { it.selectedContent == index }!!

                    operatorUserViewModel.onEvent(
                        event = OperatorUserEvent.onContentWindowChange(selectedEnum)
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
                    text = navItems[operatorUserState.operatorSelectedContent.selectedContent].label,
                    color = white,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }

        ContentScreen(
            modifier = Modifier.padding(top = 200.dp),
            operatorUserState = operatorUserState,
            onCancelTransfer = { transferId ->
                operatorUserViewModel.onEvent(OperatorUserEvent.OnCancelTransfer(transferId)) }
        )

    }
}

@Composable
fun ContentScreen(
    operatorUserState: OperatorUserState,
    modifier: Modifier = Modifier,
    onCancelTransfer: (Int) -> Unit
)
{
    when(operatorUserState.operatorSelectedContent){
        OperatorSelectedContent.PROFILE -> OperatorUserProfileScreen(
            modifier = modifier,
            firstName = operatorUserState.firstName,
            lastName = operatorUserState.lastName,
            surName = operatorUserState.surName,
            phone = operatorUserState.phone,
            email = operatorUserState.email,
        )
        OperatorSelectedContent.TRANSFERS -> {
            OperatorUserTransfersScreen(
                modifier = modifier,
                operatorUserState = operatorUserState,
                onCancelTransfer = onCancelTransfer
            )
        }
        OperatorSelectedContent.SALARY_PROJECT -> OperatorUserTransfersScreen(
            modifier = modifier,
            operatorUserState = operatorUserState,
            onCancelTransfer = onCancelTransfer
        )
    }
}