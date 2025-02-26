package com.example.financysystem.presentation.screens.userScreen.screen.contentClientUserScreen.clientUser

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.domain.models.bank.bankAccount.creditBankAccount.CreditBankAccount
import com.example.domain.models.bank.bankAccount.standartBankAccount.StandardBankAccount
import com.example.financysystem.presentation.screens.components.BankAccountItem
import com.example.financysystem.presentation.screens.userScreen.state.clientUserState.ClientUserState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ClientUserBankAccountScreen(
    modifier: Modifier = Modifier,
    clientUserState: ClientUserState,
    onShowBankAccountDialog: (Int) -> Unit,
    onChangeStatusBankAccount: (Int) -> Unit
)
{
    Column {
        LazyColumn(
            modifier = Modifier.then(modifier).fillMaxWidth().fillMaxHeight(0.4f),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        )
        {
            if (clientUserState.standardBankAccounts.isEmpty() && clientUserState.creditBankAccounts.isEmpty()){
                item {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ){
                        Text(text = "Нет открытых счетов", fontSize = 24.sp)
                    }
                }
            }

            items(clientUserState.standardBankAccounts) { account ->
                when (account) {
                    is StandardBankAccount ->
                        BankAccountItem(
                            modifier = Modifier,
                            firstName = account.baseBankAccount.baseUser.firstName,
                            lastName = account.baseBankAccount.baseUser.lastName,
                            surName = account.baseBankAccount.baseUser.surName,
                            bankName = account.baseBankAccount.bank.name,
                            accountType = "Cтандартный",
                            cardId = account.baseBankAccount.id.toString(),
                            balance = account.baseBankAccount.balance.toString(),
                            statusBankAccount = account.baseBankAccount.statusBankAccount.toString(),
                            countMonthsCredit = null,
                            statusCreditBid = null,
                            creditLastDate = null,
                            creditTotalSum = null,
                            interestRate = null,
                            onShowBankAccountDialog = onShowBankAccountDialog,
                            isOpenDialog = clientUserState.isOpenDialogBankAccount,
                            idOpenDialog = clientUserState.idOpenDialogBankAccount.toString(),
                            onChangeStatusBankAccount = onChangeStatusBankAccount
                        )
                }
            }

            items(clientUserState.creditBankAccounts) { account ->
                when (account) {
                    is CreditBankAccount ->
                        BankAccountItem(
                            modifier = Modifier,
                            firstName = account.baseBankAccount.baseUser.firstName,
                            lastName = account.baseBankAccount.baseUser.lastName,
                            surName = account.baseBankAccount.baseUser.surName,
                            bankName = account.baseBankAccount.bank.name,
                            accountType = "Кредитный",
                            cardId = account.baseBankAccount.id.toString(),
                            balance = account.baseBankAccount.balance.toString(),
                            statusBankAccount = account.baseBankAccount.statusBankAccount.toString(),
                            countMonthsCredit = account.countMonthsCredit.toString(),
                            statusCreditBid = account.statusCreditBid.toString(),
                            creditLastDate = account.creditLastDate,
                            creditTotalSum = account.creditTotalSum.toString(),
                            interestRate = account.interestRate.toString(),
                            onShowBankAccountDialog = onShowBankAccountDialog,
                            isOpenDialog = clientUserState.isOpenDialogBankAccount,
                            idOpenDialog = clientUserState.idOpenDialogBankAccount.toString(),
                            onChangeStatusBankAccount = onChangeStatusBankAccount
                        )
                }
            }

        }
    }

}