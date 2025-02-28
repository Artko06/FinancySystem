package com.example.financysystem.presentation.screens.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AttachMoney
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.domain.models.transfer.StatusTransfer
import com.example.financysystem.ui.theme.green
import com.example.financysystem.ui.theme.redOrange
import com.example.financysystem.ui.theme.whiteGray

@Composable
fun TransferItem(
    modifier: Modifier = Modifier,
    idTransfer: String,
    sum: String,
    fromCardId: String,
    toCardId: String,
    timeTransfer: String,
    dataTransfer: String,
    status: StatusTransfer,
    onCancelTransfer: (Int) -> Unit
) {
    Box(
        modifier = Modifier
            .then(modifier)
            .fillMaxWidth()
            .padding(16.dp)
            .background(
                color = whiteGray,
                shape = RoundedCornerShape(15.dp)
            )
            .padding(16.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier.fillMaxWidth()
        ) {
            // Icon Section
            Box(
                modifier = Modifier
                    .size(80.dp)
                    .background(
                        color = Color.DarkGray,
                        shape = RoundedCornerShape(15.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Filled.AttachMoney,
                    contentDescription = "Transfer Icon",
                    tint = green,
                    modifier = Modifier.size(42.dp)
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            // Text Section
            Column(
                verticalArrangement = Arrangement.spacedBy(3.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Row {
                    Column {
                        Text(
                            text = "from Card ID: $fromCardId",
                            fontWeight = FontWeight.Medium,
                            fontSize = 16.sp,
                            color = Color.Black
                        )

                        Text(
                            text = "to Card ID: $toCardId",
                            fontWeight = FontWeight.Medium,
                            fontSize = 16.sp,
                            color = Color.Black
                        )
                    }

                    if (status == StatusTransfer.SUCCESS) {
                        IconButton(
                            onClick = {
                                onCancelTransfer(idTransfer.toInt())
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Filled.Cancel,
                                contentDescription = "Transfer Icon",
                                tint = redOrange
                            )
                        }
                    }
                }

                Text(
                    text = "Cумма перевода: $sum ₿",
                    fontWeight = FontWeight.Medium,
                    fontSize = 14.sp,
                    color = green
                )

                Text(
                    text = "$dataTransfer, $timeTransfer",
                    fontWeight = FontWeight.Medium,
                    fontSize = 14.sp,
                    color = Color.Black
                )

                Row {
                    Text(
                        text = "Статус: ",
                        fontWeight = FontWeight.Medium,
                        fontSize = 12.sp,
                        color = Color.Black
                    )

                    Text(
                        text = when (status) {
                            StatusTransfer.REJECTED_BY_OPERATOR -> "Отменён"
                            StatusTransfer.SUCCESS -> "Успешный"
                        },
                        fontWeight = FontWeight.Bold,
                        fontSize = 12.sp,
                        color = when (status) {
                            StatusTransfer.REJECTED_BY_OPERATOR -> redOrange
                            StatusTransfer.SUCCESS -> green
                        }
                    )
                }


            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TransferItemPreview() {
    TransferItem(
        idTransfer = "10",
        toCardId = "10",
        fromCardId = "11",
        sum = "500",
        timeTransfer = "12:30:12",
        dataTransfer = "12.12.2025",
        status = StatusTransfer.REJECTED_BY_OPERATOR,
        onCancelTransfer = {}
    )
}