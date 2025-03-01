package com.example.financysystem.presentation.screens.userScreen.screen.contentClientUserScreen.adminUser

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun AdminUserDeleterScreen(
    modifier: Modifier = Modifier,
    onDeleteAllBankAccounts: () -> Unit,
    onDeleteAllSalaryProjects: () -> Unit,
){
    Column(
        modifier = Modifier.then(modifier).fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = onDeleteAllBankAccounts
        ) {
            Text("Удалить все банковские аккаунты")
        }

        Button(
            onClick = onDeleteAllSalaryProjects
        ) {
            Text("Удалить все зарплатные проекты")
        }
    }
}