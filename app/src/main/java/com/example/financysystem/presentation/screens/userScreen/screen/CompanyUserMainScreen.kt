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
import androidx.compose.material.icons.filled.AccountBalanceWallet
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.AddBusiness
import androidx.compose.material.icons.filled.Factory
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
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
import com.example.financysystem.presentation.screens.userScreen.event.CompanyUserEvent
import com.example.financysystem.presentation.screens.userScreen.screen.contentClientUserScreen.companyUser.AddingSalaryProjectDialog
import com.example.financysystem.presentation.screens.userScreen.screen.contentClientUserScreen.companyUser.CompanyUserBankAccountScreen
import com.example.financysystem.presentation.screens.userScreen.screen.contentClientUserScreen.companyUser.CompanyUserProfileScreen
import com.example.financysystem.presentation.screens.userScreen.screen.contentClientUserScreen.companyUser.CompanyUserSalaryProjectScreen
import com.example.financysystem.presentation.screens.userScreen.state.CompanyUserState
import com.example.financysystem.presentation.screens.userScreen.state.contentState.CompanySelectedContent
import com.example.financysystem.presentation.screens.userScreen.viewModel.CompanyUserViewModel
import com.example.financysystem.ui.theme.gray
import com.example.financysystem.ui.theme.green
import com.example.financysystem.ui.theme.white

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CompanyUserMainScreen(
    companyUserViewModel: CompanyUserViewModel = hiltViewModel()
)
{
    val navItems = listOf(
        BottomNavItem(label = "Профиль", icon = Icons.Filled.AccountCircle),
        BottomNavItem(label = "Счета", icon = Icons.Filled.AccountBalanceWallet),
        BottomNavItem(label = "Проекты", icon = Icons.Filled.Factory),
    )

    val companyUserState = companyUserViewModel.companyUserState.collectAsState().value

    Scaffold(
        bottomBar = {
            BottomNavigationBar(
                items = navItems,
                modifier = Modifier.navigationBarsPadding().height(75.dp),
                selectedNavItem = companyUserState.companySelectedContent.selectedContent,
                onSelectNavItem = { index ->
                    val selectedEnum = CompanySelectedContent.entries.find { it.selectedContent == index }!!

                    companyUserViewModel.onEvent(
                        event = CompanyUserEvent.onContentWindowChange(selectedEnum)
                    )
                }
            )
        },
        floatingActionButton = {
            if(companyUserState.companySelectedContent == CompanySelectedContent.SALARY_PROJECT){
                FloatingActionButton(
                    onClick = {
                        when(companyUserState.companySelectedContent){
                            CompanySelectedContent.BANK_ACCOUNT -> {}
                            CompanySelectedContent.SALARY_PROJECT -> {
                                companyUserViewModel.onEvent(CompanyUserEvent.OnOpenAddingSalaryProject)
                            }

                            CompanySelectedContent.PROFILE -> {}
                        }
                    }
                ) {
                    Icon(
                        imageVector = Icons.Filled.AddBusiness,
                        contentDescription = "Add Button"
                    )
                }
            }
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
                    text = navItems[companyUserState.companySelectedContent.selectedContent].label,
                    color = white,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }

        ContentScreen(
            modifier = Modifier.padding(top = 160.dp),
            companyUserState = companyUserState,
            onCreateTransfer = {
                companyUserViewModel.onEvent(CompanyUserEvent.OnCreateTransfer)
            },
            onChangeToCardId = { cardId ->
                companyUserViewModel.onEvent(CompanyUserEvent.OnChangeToCardId(cardId))
            },
            onChangeTransferSum = { sum ->
                companyUserViewModel.onEvent(CompanyUserEvent.OnChangeTransferSum(sum))
            },
            onChangeFromCardId = { cardId ->
                companyUserViewModel.onEvent(CompanyUserEvent.OnChangeFromCardId(cardId))
            },
            onShowTransferDialog = { cardId ->
                companyUserViewModel.onEvent(CompanyUserEvent.OnShowTransferDialog(cardId))
            },
            onShowBankAccountDialog = { cardId ->
                companyUserViewModel.onEvent(CompanyUserEvent.OnShowBankAccountDialog(cardId))
            },
            onChangeStatusBankAccount = { cardId ->
                companyUserViewModel.onEvent(CompanyUserEvent.OnChangeStatusBankAccount(cardId))
            }
        )

        if (companyUserState.companySelectedContent == CompanySelectedContent.SALARY_PROJECT &&
            companyUserState.isOpenDialogAddingSalaryProject){
            AddingSalaryProjectDialog(
                modifier = Modifier,
                onOpenDialogAddingSalaryProject = {
                    companyUserViewModel.onEvent(CompanyUserEvent.OnOpenAddingSalaryProject)
                },
                onChangeInfoSalaryProject = { info ->
                    companyUserViewModel.onEvent(CompanyUserEvent.OnChangeInfoSalaryProject(info))
                },
                onChangeSumSalaryProject = { sum ->
                    companyUserViewModel.onEvent(CompanyUserEvent.OnChangeSumSalaryProject(sum))
                },
                onAddSalaryProject = {
                    companyUserViewModel.onEvent(CompanyUserEvent.OnAddSalaryProject)
                },
                infoSalaryProject = companyUserState.infoSalaryProject,
                sumSalaryProject = companyUserState.sumSalaryProject,
                errorInputSalaryProject = companyUserState.errorInputSalaryProject
            )
        }

    }
}

@Composable
fun ContentScreen(
    companyUserState: CompanyUserState,
    modifier: Modifier = Modifier,
    onShowBankAccountDialog: (Int) -> Unit,
    onShowTransferDialog: (Int) -> Unit,
    onChangeStatusBankAccount: (Int) -> Unit,
    onChangeTransferSum: (String) -> Unit,
    onCreateTransfer: () -> Unit,
    onChangeToCardId: (String) -> Unit,
    onChangeFromCardId: (String) -> Unit
)
{
    when(companyUserState.companySelectedContent){
        CompanySelectedContent.PROFILE -> CompanyUserProfileScreen(
            modifier = modifier,
            firstName = companyUserState.firstName,
            lastName = companyUserState.lastName,
            surName = companyUserState.surName,
            phone = companyUserState.phone,
            email = companyUserState.email,
        )

        CompanySelectedContent.BANK_ACCOUNT -> CompanyUserBankAccountScreen(
            modifier = modifier,
            companyUserState = companyUserState,
            onShowBankAccountDialog = onShowBankAccountDialog,
            onShowTransferDialog = onShowTransferDialog,
            onChangeStatusBankAccount = onChangeStatusBankAccount,
            onChangeTransferSum = onChangeTransferSum,
            onCreateTransfer = onCreateTransfer,
            onChangeFromCardId = onChangeFromCardId,
            onChangeToCardId = onChangeToCardId
        )

        CompanySelectedContent.SALARY_PROJECT -> CompanyUserSalaryProjectScreen(
            modifier = modifier,
            companyUserState = companyUserState
        )

    }
}