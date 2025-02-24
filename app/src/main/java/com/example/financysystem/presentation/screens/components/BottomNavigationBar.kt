package com.example.financysystem.presentation.screens.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBalanceWallet
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Factory
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.financysystem.ui.theme.green

@Composable
fun BottomNavigationBar(
    items: List<BottomNavItem>,
    modifier: Modifier = Modifier,
    selectedNavItem: Int,
    onSelectNavItem: (Int) -> Unit
) {
    NavigationBar(
        modifier = modifier
    ) {
        items.forEachIndexed { index, navItem ->
            NavigationBarItem(
                selected = selectedNavItem == index ,
                onClick = {
                    onSelectNavItem(index)
                },
                icon = {
                    Icon(
                        imageVector = navItem.icon,
                        tint = green,
                        contentDescription = navItem.label
                    )
                },
                label = {
                    Text(
                        text = navItem.label,
                        fontSize = 12.sp,
                        textAlign = TextAlign.Center,
                        fontFamily = FontFamily.Serif,
                        fontWeight = FontWeight.Bold,
                        style = TextStyle(lineHeight = 12.sp)
                    )
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BottomNavigationBarPreview() {
    BottomNavigationBar(
        items = listOf(
            BottomNavItem(label = "Профиль", icon = Icons.Filled.AccountCircle),
            BottomNavItem(label = "Счета", Icons.Filled.AccountBalanceWallet),
            BottomNavItem(label = "Зарплатные проекты", Icons.Filled.Factory),
        ),
        modifier = Modifier.fillMaxWidth(),
        selectedNavItem = 0,
        onSelectNavItem = {}
    )
}