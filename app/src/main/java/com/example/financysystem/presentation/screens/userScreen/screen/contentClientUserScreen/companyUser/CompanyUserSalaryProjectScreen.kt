package com.example.financysystem.presentation.screens.userScreen.screen.contentClientUserScreen.companyUser

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
import com.example.domain.models.salaryProject.SalaryProjectCompany
import com.example.financysystem.presentation.screens.components.SalaryProjectItem
import com.example.financysystem.presentation.screens.userScreen.state.CompanyUserState

@Composable
fun CompanyUserSalaryProjectScreen(
    modifier: Modifier = Modifier,
    companyUserState: CompanyUserState,
){
    Column {
        LazyColumn(
            modifier = Modifier.then(modifier).fillMaxWidth().fillMaxHeight(0.4f),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        )
        {
            if (companyUserState.salaryProjects.isEmpty()) {
                item {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = "Нет зарплатных проектов", fontSize = 24.sp)
                    }
                }
            }


            items(companyUserState.salaryProjects) { project ->
                when (project) {
                    is SalaryProjectCompany -> {
                        SalaryProjectItem(
                            salaryProject = project
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CompanyUserSalaryProjectScreenPreview(){
    CompanyUserSalaryProjectScreen(
        companyUserState = CompanyUserState()
    )
}