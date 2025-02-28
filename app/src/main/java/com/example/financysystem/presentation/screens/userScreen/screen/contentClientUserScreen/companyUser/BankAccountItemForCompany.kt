@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.financysystem.presentation.screens.userScreen.screen.contentClientUserScreen.companyUser

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AcUnit
import androidx.compose.material.icons.outlined.Block
import androidx.compose.material.icons.outlined.Check
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.MonetizationOn
import androidx.compose.material3.AlertDialogDefaults
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.domain.models.bank.bankAccount.StatusBankAccount
import com.example.financysystem.presentation.screens.userScreen.screen.contentClientUserScreen.clientUser.TransferDialogWindow
import com.example.financysystem.ui.theme.brightBlue
import com.example.financysystem.ui.theme.green
import com.example.financysystem.ui.theme.redOrange
import com.example.financysystem.ui.theme.whiteGray

@Composable
fun BankAccountItemForCompany(
    modifier: Modifier = Modifier,
    firstName: String,
    lastName: String,
    surName: String,
    bankName: String,
    accountType: String,
    cardId: String,
    balance: String,
    statusBankAccount: String,
    isOpenInfoDialog: Boolean,
    idOpenInfoDialog: String,
    onShowBankAccountDialog: (Int) -> Unit,
    onChangeStatusBankAccount: (Int) -> Unit,
    onShowTransferDialog: (Int) -> Unit,
    isOpenTransferDialog: Boolean,
    idOpenTransferDialog: String,
    onCreateTransfer: () -> Unit,
    onChangeToCardId: (String) -> Unit,
    onChangeFromCardId: (String) -> Unit,
    onChangeTransferSum: (String) -> Unit,
    transferSum: String,
    toCardId: String,
    errorTransfer: String?
){
    Box(
        modifier = Modifier
            .then(modifier)
            .fillMaxWidth()
            .fillMaxHeight(0.3f)
            .padding(start = 25.dp, end = 25.dp)
            .background(color = whiteGray, shape = RoundedCornerShape(16.dp))
            .padding(16.dp)
    ) {
        // Card content
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            // Top information: Account type and card ID
            Column {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = accountType,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        color = green,
                        textAlign = TextAlign.Center
                    )

                    if(isOpenInfoDialog && cardId == idOpenInfoDialog){
                        InfoDialogWindowForCompany(
                            firstName = firstName,
                            lastName = lastName,
                            surName = surName,
                            bankName = bankName,
                            accountType = accountType,
                            cardId = cardId,
                            balance = balance,
                            statusBankAccount = statusBankAccount,
                            onShowBankAccountDialog = { onShowBankAccountDialog(cardId.toInt()) }
                        )
                    }

                    if(isOpenTransferDialog && cardId == idOpenTransferDialog){
                        onChangeFromCardId(cardId.toString())
                        TransferDialogWindow(
                            onShowTransferDialog = onShowTransferDialog,
                            onCreateTransfer = onCreateTransfer,
                            onChangeToCardId = onChangeToCardId,
                            onChangeTransferSum = onChangeTransferSum,
                            transferSum = transferSum,
                            fromCardId = cardId,
                            toCardId = toCardId,
                            errorTransfer = errorTransfer
                        )
                    }

                    IconButton(
                        onClick = {
                            onShowBankAccountDialog(cardId.toInt())
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.Info,
                            contentDescription = null,
                            tint = Color.Black
                        )
                    }

                    IconButton(
                        onClick = {
                            onChangeStatusBankAccount(cardId.toInt())
                        }
                    ) {
                        when(enumValueOf<StatusBankAccount>(statusBankAccount)){
                            StatusBankAccount.FROZEN -> {
                                Icon(
                                    imageVector = Icons.Outlined.AcUnit,
                                    contentDescription = "FROZEN",
                                    tint = brightBlue
                                )
                            }

                            StatusBankAccount.BLOCKED -> {
                                Icon(
                                    imageVector = Icons.Outlined.Block,
                                    contentDescription = "BLOCKED",
                                    tint = redOrange
                                )
                            }

                            StatusBankAccount.NORMAL -> {
                                Icon(
                                    imageVector = Icons.Outlined.Check,
                                    contentDescription = "NORMAL",
                                    tint = green
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.weight(1f))

                    IconButton(
                        onClick = {
                            onShowTransferDialog(cardId.toInt())
                        },
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.MonetizationOn,
                            contentDescription = null,
                            tint = green
                        )
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Card ID: $cardId",
                    fontSize = 14.sp,
                    color = Color.Black
                )
            }

            // User
            Text(
                text = "$lastName $firstName $surName",
                fontWeight = FontWeight.ExtraBold,
                fontSize = 18.sp,
                color = Color.Black,
                textAlign = TextAlign.Start
            )

            // Balance
            Text(
                text = "Balance: $balance ₿",
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                color = Color.Black,
                textAlign = TextAlign.Start
            )

            // Bank name in bottom-right corner
            Text(
                text = bankName,
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp,
                color = green,
                modifier = Modifier.align(Alignment.End)
            )
        }
    }
}


@Composable
fun InfoDialogWindowForCompany(
    modifier: Modifier = Modifier,
    firstName: String,
    lastName: String,
    surName: String,
    bankName: String,
    accountType: String,
    cardId: String,
    balance: String,
    statusBankAccount: String,
    onShowBankAccountDialog: (Int) -> Unit,
){
    BasicAlertDialog(
        onDismissRequest = {
            onShowBankAccountDialog(cardId.toInt())
        },
        modifier = modifier
    )
    {
        Surface(
            modifier = Modifier.wrapContentWidth().wrapContentHeight(),
            shape = MaterialTheme.shapes.large,
            tonalElevation = AlertDialogDefaults.TonalElevation
        )
        {
            Column(
                modifier = Modifier.padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            )
            {
                Text(text = "Информация", fontSize = 28.sp)

                Spacer(modifier = Modifier.padding(24.dp))

                Column(
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Text(text = "Владелец: $lastName $firstName $surName")

                    Text(text = "Банк: $bankName")

                    Text(text = "Аккаунт: $accountType")

                    Text(text = "Card ID: $cardId")

                    Text(text = "Balance: $balance")

                    Text(text = "Статус: $statusBankAccount")
                }
            }
        }
    }
}