package com.example.financysystem.presentation.screens.userScreen.screen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.DeleteForever
import androidx.compose.material.icons.filled.PermDeviceInformation
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
import com.example.financysystem.presentation.screens.userScreen.event.AdminUserEvent
import com.example.financysystem.presentation.screens.userScreen.screen.contentClientUserScreen.adminUser.AdminUserActionLogScreen
import com.example.financysystem.presentation.screens.userScreen.screen.contentClientUserScreen.adminUser.AdminUserDeleterScreen
import com.example.financysystem.presentation.screens.userScreen.screen.contentClientUserScreen.adminUser.AdminUserProfileScreen
import com.example.financysystem.presentation.screens.userScreen.state.AdminUserState
import com.example.financysystem.presentation.screens.userScreen.state.contentState.AdminSelectedContent
import com.example.financysystem.presentation.screens.userScreen.viewModel.AdminUserViewModel
import com.example.financysystem.ui.theme.gray
import com.example.financysystem.ui.theme.green
import com.example.financysystem.ui.theme.white

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AdminUserMainScreen(
    adminUserViewModel: AdminUserViewModel = hiltViewModel()
) {
    val navItems = listOf(
        BottomNavItem(label = "Профиль", icon = Icons.Filled.AccountCircle),
        BottomNavItem(label = "Логи", icon = Icons.Filled.PermDeviceInformation),
        BottomNavItem(label = "Удаление", icon = Icons.Filled.DeleteForever),
    )

    val adminUserState = adminUserViewModel.adminUserState.collectAsState().value

    Scaffold(
        bottomBar = {
            BottomNavigationBar(
                items = navItems,
                modifier = Modifier
                    .navigationBarsPadding()
                    .height(75.dp),
                selectedNavItem = adminUserState.adminSelectedContent.selectedContent,
                onSelectNavItem = { index ->
                    val selectedEnum =
                        AdminSelectedContent.entries.find { it.selectedContent == index }!!

                    adminUserViewModel.onEvent(
                        event = AdminUserEvent.onContentWindowChange(selectedEnum)
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
                    text = navItems[adminUserState.adminSelectedContent.selectedContent].label,
                    color = white,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }

        ContentScreen(
            modifier = Modifier.padding(top = 160.dp),
            adminUserState = adminUserState,
            onDeleteAllBankAccounts = {
                adminUserViewModel.onEvent(AdminUserEvent.OnDeleteAllBankAccounts)
            },
            onDeleteAllSalaryProjects = {
                adminUserViewModel.onEvent(AdminUserEvent.OnDeleteAllSalaryProjects)
            }
        )

    }
}

@Composable
fun ContentScreen(
    adminUserState: AdminUserState,
    modifier: Modifier = Modifier,
    onDeleteAllBankAccounts: () -> Unit,
    onDeleteAllSalaryProjects: () -> Unit,
) {
    when (adminUserState.adminSelectedContent) {
        AdminSelectedContent.PROFILE -> AdminUserProfileScreen(
            modifier = modifier,
            firstName = adminUserState.firstName,
            lastName = adminUserState.lastName,
            surName = adminUserState.surName,
            phone = adminUserState.phone,
            email = adminUserState.email,
        )

        AdminSelectedContent.LOGS -> AdminUserActionLogScreen(
            modifier = modifier,
            adminUserState = adminUserState
        )

        AdminSelectedContent.DELETER -> AdminUserDeleterScreen(
            modifier = modifier,
            onDeleteAllBankAccounts = onDeleteAllBankAccounts,
            onDeleteAllSalaryProjects = onDeleteAllSalaryProjects
        )
    }
}
