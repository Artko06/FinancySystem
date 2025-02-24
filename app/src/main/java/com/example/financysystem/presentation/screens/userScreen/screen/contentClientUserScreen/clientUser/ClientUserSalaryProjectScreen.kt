package com.example.financysystem.presentation.screens.userScreen.screen.contentClientUserScreen.clientUser

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ClientUserSalaryProjectScreen(
    modifier: Modifier = Modifier
){
    Text(
        text = "SalaryProject",
        modifier = modifier
    )
}

@Preview(showSystemUi = true)
@Composable
fun SalaryProjectScreenPreview(){
    ClientUserSalaryProjectScreen(modifier = Modifier.padding(top = 200.dp))
}