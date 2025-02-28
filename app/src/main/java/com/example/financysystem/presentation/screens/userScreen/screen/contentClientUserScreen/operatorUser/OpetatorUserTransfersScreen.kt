package com.example.financysystem.presentation.screens.userScreen.screen.contentClientUserScreen.operatorUser

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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.domain.models.transfer.Transfer
import com.example.financysystem.presentation.screens.components.TransferItem
import com.example.financysystem.presentation.screens.userScreen.state.OperatorUserState

@Composable
fun OperatorUserTransfersScreen(
    modifier: Modifier = Modifier,
    operatorUserState: OperatorUserState,
    onCancelTransfer: (Int) -> Unit
) {
    Column {
        LazyColumn(
            modifier = Modifier.then(modifier).fillMaxWidth().fillMaxHeight(0.4f),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        )
        {
            if (operatorUserState.transfers.isEmpty()) {
                item {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = "В системе ещё нет переводов", fontSize = 24.sp)
                    }
                }
            }

            items(operatorUserState.transfers) { transfer ->
                if(transfer is Transfer) {
                    TransferItem(
                        idTransfer = transfer.id.toString(),
                        sum = transfer.amount.toString(),
                        fromCardId = transfer.fromBaseBankAccount.id.toString(),
                        toCardId = transfer.toBaseBankAccount.id.toString(),
                        timeTransfer = transfer.timeTransfer,
                        dataTransfer = transfer.dateTransfer,
                        status = transfer.status,
                        onCancelTransfer = onCancelTransfer
                    )
                }
            }
        }
    }
}


@Preview(showSystemUi = true)
@Composable
fun OperatorUserTransfersScreenPreview(){
    OperatorUserTransfersScreen(
        operatorUserState = OperatorUserState(),
        onCancelTransfer = {}
    )
}