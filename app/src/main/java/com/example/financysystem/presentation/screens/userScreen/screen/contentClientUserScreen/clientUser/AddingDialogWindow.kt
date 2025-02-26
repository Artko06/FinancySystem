package com.example.financysystem.presentation.screens.userScreen.screen.contentClientUserScreen.clientUser

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialogDefaults
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.domain.models.bank.Bank
import com.example.financysystem.presentation.screens.components.DropDownMenu
import com.example.financysystem.presentation.screens.components.RadioButtonGroup
import com.example.financysystem.presentation.screens.userScreen.state.clientUserState.MonthCountCredit
import com.example.financysystem.presentation.screens.userScreen.state.clientUserState.SumForCredit
import com.example.financysystem.presentation.screens.userScreen.state.clientUserState.TypeBankAccount
import com.example.financysystem.ui.theme.redOrange

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddingDialogWindow(
    modifier: Modifier = Modifier,
    onOpenDialogWindow: () -> Unit,
    onSelectBank: (Int) -> Unit,
    onToggleMenuBank: () -> Unit,
    onSelectTypeBankAccount: (TypeBankAccount) -> Unit,
    selectedTypeBankAccount: TypeBankAccount,
    onAddBankAccount: () -> Unit,
    onSelectSumForCredit: (SumForCredit) -> Unit,
    onSelectMountForCredit: (MonthCountCredit) -> Unit,
    selectedSumForCredit: SumForCredit,
    selectedMonthCountCredit: MonthCountCredit,
    selectIndexBank: Int,
    isOpenMenuBank: Boolean,
    banks: List<Bank>
) {
    BasicAlertDialog(
        onDismissRequest = { onOpenDialogWindow() },
        modifier = modifier
    )
    {
        Surface(
            modifier = Modifier
                .wrapContentWidth()
                .wrapContentHeight(),
            shape = MaterialTheme.shapes.large,
            tonalElevation = AlertDialogDefaults.TonalElevation
        )
        {
            Column(
                modifier = Modifier.padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            )
            {
                Text(text = "Добавление банковского счёта", fontSize = 24.sp)

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(text = "1. Тип счёта:", fontSize = 14.sp)

                    RadioButtonGroup(
                        options = listOf(
                            TypeBankAccount.STANDARD.toString(),
                            TypeBankAccount.CREDIT.toString()
                        ),
                        selectedOption = selectedTypeBankAccount.name,
                        onOptionSelected = { onSelectTypeBankAccount(enumValueOf<TypeBankAccount>(it)) },
                        fontSize = 12.sp,
                        sizeRadio = 20.dp
                    )
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                )
                {
                    Column {
                        Text(
                            text = "2. Банк:",
                            fontSize = 14.sp,
                            modifier = Modifier
                        )
                    }


                    DropDownMenu(
                        isOpenMenu = isOpenMenuBank,
                        selectedIndex = selectIndexBank,
                        items = banks.map { it.name },
                        onSelect = onSelectBank,
                        onToggleMenu = onToggleMenuBank,
                        modifier = Modifier
                    )
                }

                if (selectedTypeBankAccount == TypeBankAccount.CREDIT) {
                    val interestRate = banks[selectIndexBank].interestRate

                    val interestRateCustom = if (selectedMonthCountCredit.months >= 12)
                    {
                        (interestRate.toDouble() + interestRate.toDouble() / 12 * (selectedMonthCountCredit.months - 12)).toInt()
                    } else{ interestRate.toInt() }

                    Text("Процентная ставка -> $interestRateCustom%")
                }

                val rememberScrollStateForMount = rememberScrollState()
                val rememberScrollStateForSum = rememberScrollState()

                if (selectedTypeBankAccount == TypeBankAccount.CREDIT) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .horizontalScroll(rememberScrollStateForMount),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    )
                    {
                        SingleChoiceSegmentedButtonRow {
                            MonthCountCredit.entries.forEachIndexed { index, option ->
                                SegmentedButton(
                                    selected = selectedMonthCountCredit == option,
                                    onClick = { onSelectMountForCredit(option) },
                                    shape = when (index) {
                                        0 -> RoundedCornerShape(
                                            topStart = 12.dp,
                                            bottomStart = 12.dp
                                        ) // Левый крайний
                                        MonthCountCredit.entries.toTypedArray().lastIndex -> RoundedCornerShape(
                                            topEnd = 12.dp,
                                            bottomEnd = 12.dp
                                        ) // Правый крайний
                                        else -> RoundedCornerShape(0.dp) // Центральные без скруглений
                                    },
                                    label = {
                                        when (option) {
                                            MonthCountCredit.THREE_MONTH -> Text("3 месяца")
                                            MonthCountCredit.SIX_MONTH -> Text("6 месяцев")
                                            MonthCountCredit.TWELVE_MONTH -> Text("12 месяцев")
                                            MonthCountCredit.EIGHT_MONTH -> Text("18 месяцев")
                                            MonthCountCredit.TWENTY_FOUR_MONTH -> Text("24 месяца")
                                            MonthCountCredit.THIRTY_SIX_MONTH -> Text("36 месяцев")
                                        }
                                    }
                                )
                            }
                        }
                    }
                }

                if (selectedTypeBankAccount == TypeBankAccount.CREDIT) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .horizontalScroll(rememberScrollStateForSum),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    )
                    {
                        SingleChoiceSegmentedButtonRow {
                            SumForCredit.entries.forEachIndexed { index, option ->
                                SegmentedButton(
                                    selected = selectedSumForCredit == option,
                                    onClick = { onSelectSumForCredit(option) },
                                    shape = when (index) {
                                        0 -> RoundedCornerShape(
                                            topStart = 12.dp,
                                            bottomStart = 12.dp
                                        ) // Левый крайний
                                        SumForCredit.entries.toTypedArray().lastIndex -> RoundedCornerShape(
                                            topEnd = 12.dp,
                                            bottomEnd = 12.dp
                                        ) // Правый крайний
                                        else -> RoundedCornerShape(0.dp) // Центральные без скруглений
                                    },
                                    label = {
                                        when (option) {
                                            SumForCredit.ONE_THOUSAND -> Text("1000₿")
                                            SumForCredit.TWO_THOUSAND -> Text("2000₿")
                                            SumForCredit.FIVE_THOUSAND -> Text("5000₿")
                                            SumForCredit.TEN_THOUSAND -> Text("10000₿")
                                            SumForCredit.TWENTY_THOUSAND -> Text("20000₿")
                                            SumForCredit.FIFTY_THOUSAND -> Text("50000₿")
                                            SumForCredit.ONE_HUNDRED_THOUSAND -> Text("100000₿")
                                        }
                                    }
                                )
                            }
                        }

                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {
                        onOpenDialogWindow()
                        onAddBankAccount()
                    }
                )
                {
                    Text(text = "Добавить cчёт")
                }

                Text(
                    text = "Следует учесть, что максимум можно создать не более 5 аккаунтов каждого типа",
                    color = redOrange,
                    fontSize = 8.sp,
                    lineHeight = 8.sp
                )
            }
        }
    }
}

@Preview
@Composable
fun AddingDialogWindowPreview() {
    AddingDialogWindow(
        onOpenDialogWindow = {},
        onSelectBank = {},
        onToggleMenuBank = {},
        onSelectTypeBankAccount = {},
        selectedTypeBankAccount = TypeBankAccount.STANDARD,
        onAddBankAccount = {},
        onSelectSumForCredit = {},
        onSelectMountForCredit = {},
        selectedSumForCredit = SumForCredit.ONE_THOUSAND,
        selectedMonthCountCredit = MonthCountCredit.TWELVE_MONTH,
        selectIndexBank = 0,
        isOpenMenuBank = false,
        banks = emptyList()
    )
}