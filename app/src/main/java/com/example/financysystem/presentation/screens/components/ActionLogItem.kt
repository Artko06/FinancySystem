package com.example.financysystem.presentation.screens.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PermDeviceInformation
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.domain.models.actionLog.ActionType
import com.example.domain.models.user.TypeOfUser
import com.example.financysystem.ui.theme.green
import com.example.financysystem.ui.theme.whiteGray

@Composable
fun ActionLogItem(
    modifier: Modifier = Modifier,
    firstName: String,
    lastName: String,
    surName: String,
    typeOfUser: TypeOfUser,
    dateLog: String,
    timeLog: String,
    actionType: ActionType,
) {
    Box(
        modifier = Modifier
            .then(modifier)
            .fillMaxWidth()
            .padding(16.dp)
            .background(
                color = whiteGray,
                shape = RoundedCornerShape(15.dp)
            )
            .padding(16.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier.fillMaxWidth()
        ) {
            // Icon Section
            Box(
                modifier = Modifier
                    .size(80.dp)
                    .background(
                        color = Color.DarkGray,
                        shape = RoundedCornerShape(15.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Filled.PermDeviceInformation,
                    contentDescription = "Log Icon",
                    tint = green,
                    modifier = Modifier.size(42.dp)
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            // Text Section
            Column(
                verticalArrangement = Arrangement.spacedBy(3.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Row {
                    Column {
                        Text(
                            text = "$lastName $firstName $surName",
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp,
                            color = green
                        )

                        Text(
                            text = "${typeOfUser.name}",
                            fontWeight = FontWeight.Medium,
                            fontSize = 16.sp,
                            color = Color.Black
                        )
                    }
                }

                // Info action
                Text(
                    text = when(actionType){
                        ActionType.AUTHORIZATION -> {
                            "Вошёл в аккаунт"
                        }

                        ActionType.CREATE_BANK_ACCOUNT -> {
                            "Cоздал банковский аккаунт"
                        }

                        ActionType.CHANGE_STATUS_BANK_ACCOUNT_BY_CLIENT -> {
                            "Клиент изменил статус своего счёта"
                        }

                        ActionType.CHANGE_STATUS_BANK_ACCOUNT_BY_COMPANY -> {
                            "Комания изменила статус своего счёта"
                        }

                        ActionType.CHANGE_STATUS_BANK_ACCOUNT_BY_MANAGER -> {
                            "Менеджер изменил статус счёта"
                        }

                        ActionType.REQUEST_TO_SALARY_PROJECT_BY_CLIENT -> {
                            "Клиент отправил запрос на зарплатный проект"
                        }

                        ActionType.RECALL_TO_SALARY_PROJECT_BY_CLIENT -> {
                            "Клиент отозвал запрос на зарплатный проект"
                        }

                        ActionType.REQUEST_TO_SALARY_PROJECT_BY_COMPANY -> {
                            "Компания отправила заявку в банк на зарплатный проект"
                        }

                        ActionType.ACCEPT_CREDIT -> {
                            "Одобрили кредит пользевателю"
                        }

                        ActionType.TRANSFER -> {
                            "Отправил перевод средств"
                        }

                        ActionType.CHANGE_STATUS_SALARY_PROJECT -> {
                            "Банк отправил ответ на зарплатный проет от компании"
                        }

                        ActionType.CANCEL_TRANSFER -> {
                            "Отменил перевод средств"
                        }

                        ActionType.DELETE_ALL_BANK_ACCOUNTS_BY_ADMIN -> {
                            "Администратор удалил все банковские аккаунты"
                        }

                        ActionType.DELETE_ALL_SALARY_PROJECTS_BY_ADMIN -> {
                            "Администратор удалил все зарплатные проекты"
                        }
                    },
                    fontWeight = FontWeight.Medium,
                    fontSize = 12.sp,
                    color = Color.Black

                )

                Text(
                    text = "$dateLog, $timeLog",
                    fontWeight = FontWeight.Medium,
                    fontSize = 14.sp,
                    color = Color.Black
                )

            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ActionLogItemPreview() {
    ActionLogItem(
        firstName = "Артём",
        lastName = "Кохан",
        surName = "Игоревич",
        typeOfUser = TypeOfUser.ClientUser,
        dateLog = "30.12.2000",
        timeLog = "12:22:34",
        actionType = ActionType.CHANGE_STATUS_BANK_ACCOUNT_BY_CLIENT
    )
}