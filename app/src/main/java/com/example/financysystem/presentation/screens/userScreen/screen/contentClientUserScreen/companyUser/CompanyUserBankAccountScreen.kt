package com.example.financysystem.presentation.screens.userScreen.screen.contentClientUserScreen.companyUser

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.domain.models.bank.bankAccount.companyBankAccount.CompanyBankAccount
import com.example.financysystem.presentation.screens.userScreen.state.CompanyUserState

@Composable
fun CompanyUserBankAccountScreen(
    modifier: Modifier = Modifier,
    companyUserState: CompanyUserState,
    onShowBankAccountDialog: (Int) -> Unit,
    onShowTransferDialog: (Int) -> Unit,
    onChangeStatusBankAccount: (Int) -> Unit,
    onChangeTransferSum: (String) -> Unit,
    onCreateTransfer: () -> Unit,
    onChangeToCardId: (String) -> Unit,
    onChangeFromCardId: (String) -> Unit
) {
    Column {
        LazyColumn(
            modifier = Modifier.then(modifier).fillMaxWidth().fillMaxHeight(0.4f),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        )
        {
            if (companyUserState.companyBankAccounts.isEmpty())
            {
                item {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = "Нет cчетов у комании", fontSize = 24.sp)
                    }
                }
            }

            items(companyUserState.companyBankAccounts) { account ->
                when (account) {
                    is CompanyBankAccount ->
                        BankAccountItemForCompany(
                            modifier = Modifier,
                            firstName = account.baseBankAccount.baseUser.firstName,
                            lastName = account.baseBankAccount.baseUser.lastName,
                            surName = account.baseBankAccount.baseUser.surName,
                            bankName = account.baseBankAccount.bank.name,
                            accountType = "Корпорационный",
                            cardId = account.baseBankAccount.id.toString(),
                            balance = account.baseBankAccount.balance.toString(),
                            statusBankAccount = account.baseBankAccount.statusBankAccount.toString(),
                            onShowBankAccountDialog = onShowBankAccountDialog,
                            isOpenInfoDialog = companyUserState.isOpenDialogBankAccount,
                            idOpenInfoDialog = companyUserState.idOpenDialogBankAccount.toString(),
                            onChangeStatusBankAccount = onChangeStatusBankAccount,
                            onCreateTransfer = onCreateTransfer,
                            onChangeToCardId = onChangeToCardId,
                            onChangeFromCardId = onChangeFromCardId,
                            onChangeTransferSum = onChangeTransferSum,
                            onShowTransferDialog = onShowTransferDialog,
                            isOpenTransferDialog = companyUserState.isOpenTransferDialog,
                            idOpenTransferDialog = companyUserState.idOpenTransferDialog.toString(),
                            transferSum = companyUserState.inputTransferSum,
                            toCardId = companyUserState.inputToCardId,
                            errorTransfer = companyUserState.errorCreateTransfer
                        )
                }



            }
        }
    }
}