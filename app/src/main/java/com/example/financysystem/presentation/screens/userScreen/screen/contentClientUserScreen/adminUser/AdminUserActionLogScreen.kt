package com.example.financysystem.presentation.screens.userScreen.screen.contentClientUserScreen.adminUser

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.domain.models.actionLog.ActionLog
import com.example.financysystem.presentation.screens.components.ActionLogItem
import com.example.financysystem.presentation.screens.userScreen.state.AdminUserState

@Composable
fun AdminUserActionLogScreen(
    modifier: Modifier = Modifier,
    adminUserState: AdminUserState
) {
    Column {
        LazyColumn(
            modifier = Modifier.then(modifier).fillMaxWidth().fillMaxHeight(0.4f),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        )
        {
            if (adminUserState.actionLogs.isEmpty()) {
                item {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = "Нет логов", fontSize = 24.sp)
                    }
                }
            }


            items(adminUserState.actionLogs) { project ->
                when (project) {
                    is ActionLog -> {
                        ActionLogItem(
                            firstName = project.baseUser.firstName,
                            lastName = project.baseUser.lastName,
                            surName = project.baseUser.surName,
                            typeOfUser = project.baseUser.typeOfUser,
                            dateLog = project.date,
                            timeLog = project.time,
                            actionType = project.actionType
                        )
                    }
                }
            }
        }
    }
}


@Preview(showSystemUi = true)
@Composable
fun AdminUserActionLogScreenPreview(){
    AdminUserActionLogScreen(
        modifier = Modifier.padding(top = 160.dp),
        adminUserState = AdminUserState()
    )
}