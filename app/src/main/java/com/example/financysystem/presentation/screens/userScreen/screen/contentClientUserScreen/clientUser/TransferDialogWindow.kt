package com.example.financysystem.presentation.screens.userScreen.screen.contentClientUserScreen.clientUser

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AttachMoney
import androidx.compose.material.icons.filled.CreditCard
import androidx.compose.material3.AlertDialogDefaults
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.financysystem.presentation.screens.components.TextEntry
import com.example.financysystem.ui.theme.gray
import com.example.financysystem.ui.theme.green
import com.example.financysystem.ui.theme.redOrange

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TransferDialogWindow(
    modifier: Modifier = Modifier,
    onShowTransferDialog: (Int) -> Unit,
    onCreateTransfer: () -> Unit,
    onChangeToCardId: (String) -> Unit,
    onChangeTransferSum: (String) -> Unit,
    transferSum: String,
    fromCardId: String,
    toCardId: String,
    errorTransfer: String?
) {
    BasicAlertDialog(
        onDismissRequest = {
            onShowTransferDialog(fromCardId.toInt())
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
                Text(text = "Перевод", fontSize = 28.sp)

                Spacer(modifier = Modifier.padding(24.dp))

                TextEntry(
                    modifier = Modifier
                        .fillMaxWidth(),
                    description = "Номер карты",
                    hint = "Введите целое число",
                    textValue = toCardId,
                    keyboardType = KeyboardType.Number,
                    textColor = gray,
                    cursorColor = green,
                    onValueChanged = onChangeToCardId,
                    trailingIcon = null,
                    onTrailingIconClick = null,
                    leadingIcon = Icons.Default.CreditCard
                )

                TextEntry(
                    modifier = Modifier
                        .fillMaxWidth(),
                    description = "Сумма перевода",
                    hint = "",
                    textValue = transferSum,
                    keyboardType = KeyboardType.Number,
                    textColor = gray,
                    cursorColor = green,
                    onValueChanged = onChangeTransferSum,
                    trailingIcon = null,
                    onTrailingIconClick = null,
                    leadingIcon = Icons.Default.AttachMoney
                )

                Spacer(modifier = Modifier.height(18.dp))

                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {
                        onCreateTransfer()
                    }
                )
                {
                    Text(text = "Перевести")
                }

                if(errorTransfer != null) {
                    Text(
                        text = errorTransfer,
                        color = redOrange,
                        fontSize = 10.sp,
                        lineHeight = 10.sp
                    )
                }
            }
        }
    }
}

@Suppress("UNCHECKED_CAST")
@Preview
@Composable
fun TransferDialogWindowPreview() {
    TransferDialogWindow(
        onShowTransferDialog = {},
        onCreateTransfer = {},
        onChangeToCardId= {},
        onChangeTransferSum = {},
        transferSum = "",
        fromCardId = "",
        toCardId = "",
        errorTransfer = "Неверный номер карты"
    )
}