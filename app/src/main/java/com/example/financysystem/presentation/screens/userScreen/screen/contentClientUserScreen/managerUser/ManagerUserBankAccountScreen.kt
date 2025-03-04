package com.example.financysystem.presentation.screens.userScreen.screen.contentClientUserScreen.managerUser

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.domain.models.bank.bankAccount.companyBankAccount.CompanyBankAccount
import com.example.domain.models.bank.bankAccount.creditBankAccount.CreditBankAccount
import com.example.domain.models.bank.bankAccount.creditBankAccount.StatusCreditBid
import com.example.domain.models.bank.bankAccount.standartBankAccount.StandardBankAccount
import com.example.financysystem.presentation.screens.userScreen.state.ManagerUserState

@Composable
fun ManagerUserBankAccountScreen(
    modifier: Modifier = Modifier,
    managerUserState: ManagerUserState,
    onShowBankAccountDialog: (Int) -> Unit,
    onChangeStatusBankAccount: (Int) -> Unit,
    onChangeStatusCreditBankAccount: (Int, StatusCreditBid) -> Unit
) {
    Column {
        LazyColumn(
            modifier = Modifier.then(modifier).fillMaxWidth().fillMaxHeight(0.4f),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        )
        {
            if (managerUserState.standardBankAccounts.isEmpty() ||
                managerUserState.creditBankAccounts.isEmpty() ||
                managerUserState.companyBankAccounts.isEmpty())
            {
                item {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = "Нет открытых счетов", fontSize = 24.sp)
                    }
                }
            }

            items(managerUserState.standardBankAccounts) { account ->
                when (account) {
                    is StandardBankAccount ->
                        BankAccountItemForManager(
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
                            isOpenInfoDialog = managerUserState.isOpenDialogBankAccount,
                            idOpenInfoDialog = managerUserState.idOpenDialogBankAccount.toString(),
                            onShowBankAccountDialog = onShowBankAccountDialog,
                            onChangeStatusBankAccount = onChangeStatusBankAccount,
                        )
                }
            }

            items(managerUserState.creditBankAccounts) { account ->
                when (account) {
                    is CreditBankAccount -> {
                        BankAccountItemForManager(
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
                            isOpenInfoDialog = managerUserState.isOpenDialogBankAccount,
                            idOpenInfoDialog = managerUserState.idOpenDialogBankAccount.toString(),
                            onChangeStatusBankAccount = onChangeStatusBankAccount
                        )

                        if (account.statusCreditBid == StatusCreditBid.WAITING) {
                            Row(
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Button(
                                    onClick = {
                                        onChangeStatusCreditBankAccount(
                                            account.id,
                                            StatusCreditBid.ACCEPTED
                                        )
                                    },
                                    modifier = Modifier.weight(1f)
                                ) {
                                    Text(
                                        text = "Одобрить"
                                    )
                                }

                                Button(
                                    onClick = {
                                        onChangeStatusCreditBankAccount(
                                            account.id,
                                            StatusCreditBid.REJECTED
                                        )
                                    },
                                    modifier = Modifier.weight(1f)
                                ) {
                                    Text(
                                        text = "Отклонить"
                                    )
                                }
                            }
                        }

                    }
                }
            }


            items(managerUserState.companyBankAccounts) { account ->
                when (account) {
                    is CompanyBankAccount ->
                        BankAccountItemForManager(
                            modifier = Modifier,
                            firstName = account.baseBankAccount.baseUser.firstName,
                            lastName = account.baseBankAccount.baseUser.lastName,
                            surName = account.baseBankAccount.baseUser.surName,
                            bankName = account.baseBankAccount.bank.name,
                            accountType = "Корпорационный",
                            cardId = account.baseBankAccount.id.toString(),
                            balance = account.baseBankAccount.balance.toString(),
                            statusBankAccount = account.baseBankAccount.statusBankAccount.toString(),
                            countMonthsCredit = null,
                            statusCreditBid = null,
                            creditLastDate = null,
                            creditTotalSum = null,
                            interestRate = null,
                            onShowBankAccountDialog = onShowBankAccountDialog,
                            isOpenInfoDialog = managerUserState.isOpenDialogBankAccount,
                            idOpenInfoDialog = managerUserState.idOpenDialogBankAccount.toString(),
                            onChangeStatusBankAccount = onChangeStatusBankAccount)
                }
            }
        }


    }
}