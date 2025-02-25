package com.example.financysystem.presentation.screens.userScreen.screen.contentClientUserScreen.clientUser

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.domain.models.bank.bankAccount.creditBankAccount.CreditBankAccount
import com.example.domain.models.bank.bankAccount.standartBankAccount.StandardBankAccount
import com.example.financysystem.presentation.screens.components.BankAccountItem
import com.example.financysystem.presentation.screens.components.DropDownMenu
import com.example.financysystem.presentation.screens.components.RadioButtonGroup
import com.example.financysystem.presentation.screens.userScreen.state.clientUserState.ClientUserState
import com.example.financysystem.presentation.screens.userScreen.state.clientUserState.TypeBankAccount

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ClientUserBankAccountScreen(
    modifier: Modifier = Modifier,
    clientUserState: ClientUserState,
    onSelectBank: (Int) -> Unit,
    onToggleMenuBank: () -> Unit,
    onSelectTypeBankAccount: (String) -> Unit,
    onAddBankAccount: () -> Unit
)
{

    Column {
        LazyColumn(
            modifier = Modifier.then(modifier).fillMaxWidth().fillMaxHeight(0.3f),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        )
        {
            if (clientUserState.standardBankAccounts.isEmpty()){
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
                            creditLastDate = null
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
                            creditLastDate = account.creditLastDate
                        )
                }
            }

        }

        Spacer(modifier = Modifier.height(6.dp))

        Text(text = "Добавить счёт: ", fontSize = 22.sp, textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth())

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            DropDownMenu(
                isOpenMenu = clientUserState.isOpenMenuBank,
                selectedIndex = clientUserState.selectedIndexBank,
                items = clientUserState.banks.map { it.name },
                onSelect = onSelectBank,
                onToggleMenu = onToggleMenuBank,
                modifier = Modifier.weight(1f)
            )

            RadioButtonGroup(
                options = listOf(TypeBankAccount.STANDARD.name, TypeBankAccount.CREDIT.name),
                selectedOption = clientUserState.selectedTypeBankAccount.name,
                onOptionSelected = onSelectTypeBankAccount,
                modifier = Modifier.weight(1f)
            )
        }

        Button(
            onClick = { onAddBankAccount() },
            modifier = Modifier
        ) {
            Text("Добавить счёт")
        }
    }

}