package com.example.financysystem.presentation.screens.userScreen.screen.contentClientUserScreen.clientUser

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.domain.models.salaryProject.SalaryProjectCompany
import com.example.financysystem.presentation.screens.components.SalaryProjectItem
import com.example.financysystem.presentation.screens.userScreen.state.clientUserState.ClientUserState

@Composable
fun ClientUserSalaryProjectScreen(
    modifier: Modifier = Modifier,
    clientUserState: ClientUserState,
    onChangeClientSalaryProject: (SalaryProjectCompany) -> Unit
){
    Column {
        LazyColumn(
            modifier = Modifier.then(modifier).fillMaxWidth().fillMaxHeight(0.4f),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        )
        {
            if (clientUserState.salaryProjects.isEmpty()) {
                item {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = "Нет зарплатных проектов", fontSize = 24.sp)
                    }
                }
            }

            items(clientUserState.salaryProjects.reversed()) { salaryProject ->
                if (salaryProject is SalaryProjectCompany &&
                    (salaryProject.clientBaseUser == null || salaryProject.clientBaseUser?.id == clientUserState.id)
                    )
                {
                    SalaryProjectItem(
                        modifier = Modifier,
                        salaryProject = salaryProject
                    )

                    Button(
                        modifier = Modifier.fillMaxWidth(),
                        onClick = { onChangeClientSalaryProject(salaryProject) }
                    ) {
                        if(salaryProject.clientBaseUser == null){
                            Text("Подать заявку")
                        }
                        else{
                            Text("Отозвать заявку")
                        }
                    }
                }
            }


        }
    }

}


@Preview(showSystemUi = true)
@Composable
fun SalaryProjectScreenPreview(){
    ClientUserSalaryProjectScreen(
        modifier = Modifier.padding(top = 200.dp),
        clientUserState = ClientUserState(),
        onChangeClientSalaryProject = {}
    )
}