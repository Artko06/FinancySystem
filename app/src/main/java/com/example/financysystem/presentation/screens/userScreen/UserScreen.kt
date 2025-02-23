package com.example.financysystem.presentation.screens.userScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.financysystem.presentation.screens.components.BottomNavigationBar
import com.example.financysystem.presentation.screens.components.HeaderBackground
import com.example.financysystem.ui.theme.gray
import com.example.financysystem.ui.theme.green
import com.example.financysystem.ui.theme.white

@Composable
fun UserScreen(
    userEmail: String
)
{
    Scaffold(
        bottomBar = { BottomNavigationBar(
            modifier = Modifier.navigationBarsPadding().height(75.dp)
        ) }
    ) { paddingValues ->

        Box(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background)
                .fillMaxSize()
        )
        {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(paddingValues.calculateTopPadding() + 75.dp),
                contentAlignment = Alignment.Center
            ) {
                HeaderBackground(
                    leftColor = green,
                    rightColor = gray,
                    modifier = Modifier
                        .fillMaxSize()
                )
                Text(
                    text = userEmail.toString(),
                    color = white,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun BottomNavigationBarPreview() {
    UserScreen(
        userEmail = "koxan@bsuir.by"
    )
}