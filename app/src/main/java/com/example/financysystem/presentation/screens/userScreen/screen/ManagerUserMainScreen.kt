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
import com.example.domain.models.bank.bankAccount.creditBankAccount.StatusCreditBid
import com.example.domain.models.salaryProject.SalaryProjectCompany
import com.example.domain.models.salaryProject.StatusJobBid
import com.example.financysystem.presentation.screens.components.BottomNavItem
import com.example.financysystem.presentation.screens.components.BottomNavigationBar
import com.example.financysystem.presentation.screens.components.HeaderBackground
import com.example.financysystem.presentation.screens.userScreen.event.ManagerUserEvent
import com.example.financysystem.presentation.screens.userScreen.screen.contentClientUserScreen.managerUser.ManagerUserBankAccountScreen
import com.example.financysystem.presentation.screens.userScreen.screen.contentClientUserScreen.managerUser.ManagerUserProfileScreen
import com.example.financysystem.presentation.screens.userScreen.screen.contentClientUserScreen.managerUser.ManagerUserSalaryProjectScreen
import com.example.financysystem.presentation.screens.userScreen.screen.contentClientUserScreen.managerUser.ManagerUserTransfersScreen
import com.example.financysystem.presentation.screens.userScreen.state.ManagerUserState
import com.example.financysystem.presentation.screens.userScreen.state.contentState.ManagerSelectedContent
import com.example.financysystem.presentation.screens.userScreen.viewModel.ManagerUserViewModel
import com.example.financysystem.ui.theme.gray
import com.example.financysystem.ui.theme.green
import com.example.financysystem.ui.theme.white


@Composable
fun ManagerUserMainScreen(
    managerUserViewModel: ManagerUserViewModel = hiltViewModel()
)
{
    val navItems = listOf(
        BottomNavItem(label = "Профиль", icon = Icons.Filled.AccountCircle),
        BottomNavItem(label = "Счета", icon =  Icons.Filled.AccountBalanceWallet),
        BottomNavItem(label = "Переводы", icon =  Icons.Filled.MonetizationOn),
        BottomNavItem(label = "Проекты", icon = Icons.Filled.Factory),
    )

    val managerUserState = managerUserViewModel.managerUserState.collectAsState().value

    Scaffold(
        bottomBar = {
            BottomNavigationBar(
                items = navItems,
                modifier = Modifier.navigationBarsPadding().height(75.dp),
                selectedNavItem = managerUserState.managerSelectedContent.selectedContent,
                onSelectNavItem = { index ->
                    val selectedEnum = ManagerSelectedContent.entries.find { it.selectedContent == index }!!

                    managerUserViewModel.onEvent(
                        event = ManagerUserEvent.onContentWindowChange(selectedEnum)
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
                    text = navItems[managerUserState.managerSelectedContent.selectedContent].label,
                    color = white,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }

        ContentScreen(
            modifier = Modifier.padding(top = 160.dp),
            managerUserState = managerUserState,
            onCancelTransfer = { transferId ->
                managerUserViewModel.onEvent(ManagerUserEvent.OnCancelTransfer(transferId))
            },
            onShowBankAccountDialog = { cardId ->
                managerUserViewModel.onEvent(ManagerUserEvent.OnShowBankAccountDialog(cardId))
            },
            onChangeStatusSalaryProject = { salaryProject, status ->
                managerUserViewModel.onEvent(ManagerUserEvent.OnChangeStatusSalaryProject(salaryProject, status))
            },
            onChangeStatusBankAccount = { cardId ->
                managerUserViewModel.onEvent(ManagerUserEvent.OnChangeStatusBankAccount(cardId))
            },
            onChangeStatusCreditBankAccount = { creditId, status ->
                managerUserViewModel.onEvent(ManagerUserEvent.OnChangeStatusCreditBankAccount(creditId, status))
            }
        )

    }
}

@Composable
fun ContentScreen(
    managerUserState: ManagerUserState,
    modifier: Modifier = Modifier,
    onCancelTransfer: (Int) -> Unit,
    onShowBankAccountDialog: (Int) -> Unit,
    onChangeStatusBankAccount: (Int) -> Unit,
    onChangeStatusSalaryProject: (SalaryProjectCompany, StatusJobBid) -> Unit,
    onChangeStatusCreditBankAccount: (Int, StatusCreditBid) -> Unit
)
{
    when(managerUserState.managerSelectedContent){
        ManagerSelectedContent.PROFILE -> ManagerUserProfileScreen(
            modifier = modifier,
            firstName = managerUserState.firstName,
            lastName = managerUserState.lastName,
            surName = managerUserState.surName,
            phone = managerUserState.phone,
            email = managerUserState.email,
        )
        ManagerSelectedContent.BANK_ACCOUNT -> ManagerUserBankAccountScreen(
            modifier = modifier,
            managerUserState = managerUserState,
            onShowBankAccountDialog = onShowBankAccountDialog,
            onChangeStatusBankAccount = onChangeStatusBankAccount,
            onChangeStatusCreditBankAccount = onChangeStatusCreditBankAccount
        )

        ManagerSelectedContent.SALARY_PROJECT -> ManagerUserSalaryProjectScreen(
            modifier = modifier,
            managerUserState = managerUserState,
            onChangeStatusSalaryProject = onChangeStatusSalaryProject
        )

        ManagerSelectedContent.TRANSFERS -> ManagerUserTransfersScreen(
            modifier = modifier,
            managerUserState = managerUserState,
            onCancelTransfer = onCancelTransfer
        )
    }
}