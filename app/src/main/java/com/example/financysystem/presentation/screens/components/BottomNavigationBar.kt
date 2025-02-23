package com.example.financysystem.presentation.screens.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBalanceWallet
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Factory
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.financysystem.ui.theme.green

data class BottomNavItem(val label: String, val icon: ImageVector)

@Composable
fun BottomNavigationBar(
    modifier: Modifier = Modifier
) {
    val items = listOf(
        BottomNavItem(label = "Профиль", icon = Icons.Filled.AccountCircle),
        BottomNavItem(label = "Счета", Icons.Filled.AccountBalanceWallet),
        BottomNavItem(label = "Зарплатные проекты", Icons.Filled.Factory),
    )

    BottomAppBar(modifier = modifier){
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            items.forEach { item ->
                Column(
                    modifier = Modifier.weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.label,
                        tint = green,
                        modifier = Modifier
                    )
                    Text(
                        text = item.label,
                        fontSize = 12.sp,
                        textAlign = TextAlign.Center,
                        fontFamily = FontFamily.Serif,
                        fontWeight = FontWeight.Bold,
                        style = TextStyle(lineHeight = 12.sp)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BottomNavigationBarPreview() {
    BottomNavigationBar(
        modifier = Modifier.fillMaxWidth()
    )
}