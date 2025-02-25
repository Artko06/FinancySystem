package com.example.financysystem.presentation.screens.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.financysystem.ui.theme.green
import com.example.financysystem.ui.theme.whiteGray

@Composable
fun BankAccountItem(
    modifier: Modifier = Modifier,
    firstName: String,
    lastName: String,
    surName: String,
    bankName: String,
    accountType: String,
    cardId: String,
    balance: String,
    creditLastDate: String?
) {
    Box(
        modifier = Modifier
            .then(modifier)
            .fillMaxWidth()
            .height(200.dp)
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
                Text(
                    text = if (accountType == "Кредитный") "$accountType (до $creditLastDate)"
                            else accountType,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = green
                )

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

@Preview(showSystemUi = true)
@Composable
fun BankAccountItemPreview() {
    BankAccountItem(
        modifier = Modifier.padding(top = 120.dp),
        firstName = "Aртём",
        lastName = "Кохан",
        surName = "Игоревич",
        bankName = "Беларусбанк",
        accountType = "Кредитный",
        cardId = "1",
        balance = "5000",
        creditLastDate = null
    )
}