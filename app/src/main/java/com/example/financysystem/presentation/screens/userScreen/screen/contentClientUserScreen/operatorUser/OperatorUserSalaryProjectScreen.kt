package com.example.financysystem.presentation.screens.userScreen.screen.contentClientUserScreen.operatorUser

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.domain.models.salaryProject.SalaryProjectCompany
import com.example.domain.models.salaryProject.StatusJobBid
import com.example.financysystem.presentation.screens.components.SalaryProjectItem
import com.example.financysystem.presentation.screens.userScreen.state.OperatorUserState

@Composable
fun OperatorUserSalaryProjectScreen(
    modifier: Modifier = Modifier,
    operatorUserState: OperatorUserState,
    onChangeStatusSalaryProject: (SalaryProjectCompany, StatusJobBid) -> Unit
){
    Column {
        LazyColumn(
            modifier = Modifier.then(modifier).fillMaxWidth().fillMaxHeight(0.4f),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        )
        {
            if (operatorUserState.salaryProjects.isEmpty()) {
                item {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = "Нет зарплатных проектов", fontSize = 24.sp)
                    }
                }
            }

            items(operatorUserState.salaryProjects.reversed()) { salaryProject ->
                if(salaryProject is SalaryProjectCompany) {
                    SalaryProjectItem(
                        modifier = Modifier,
                        salaryProject = salaryProject
                    )

                    if(salaryProject.status == StatusJobBid.WAITING) {


                        Row(
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Button(
                                onClick = {
                                    onChangeStatusSalaryProject(
                                        salaryProject,
                                        StatusJobBid.ACCEPTED
                                    )
                                },
                                modifier = Modifier.weight(1f)
                            ) {
                                Text(
                                    text = "Одобрить"
                                )
                            }

                            Button(
                                onClick = {
                                    onChangeStatusSalaryProject(
                                        salaryProject,
                                        StatusJobBid.REJECTED
                                    )
                                },
                                modifier = Modifier.weight(1f)
                            ) {
                                Text(
                                    text = "Отклонить"
                                )
                            }
                        }
                    }


                }
            }
        }
    }
}