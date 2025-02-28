package com.example.financysystem.presentation.screens.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.domain.models.company.Company
import com.example.domain.models.salaryProject.SalaryProjectCompany
import com.example.domain.models.salaryProject.StatusJobBid
import com.example.financysystem.ui.theme.green
import com.example.financysystem.ui.theme.redOrange
import com.example.financysystem.ui.theme.whiteGray
import com.example.financysystem.ui.theme.yellow

@Composable
fun SalaryProjectItem(
    modifier: Modifier = Modifier,
    salaryProject: SalaryProjectCompany,
)
{
    Box(
        modifier = Modifier
            .then(modifier)
            .fillMaxWidth()
            .fillMaxHeight(0.3f)
            .padding(start = 25.dp, end = 25.dp)
            .background(color = whiteGray, shape = RoundedCornerShape(16.dp))
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            Text(
                text = "Зарплатный проект",
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                color = green,
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = "Адрес: ${salaryProject.company.address}",
                fontSize = 14.sp,
                color = Color.Black
            )

            if(salaryProject.clientBaseUser != null){
                Text(
                    text = "${salaryProject.clientBaseUser!!.lastName} " +
                            "${salaryProject.clientBaseUser!!.firstName} " +
                            salaryProject.clientBaseUser!!.surName,
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 18.sp,
                    color = Color.Black,
                    textAlign = TextAlign.Start
                )
            }

            Text(
                text = "Заработная плата: ${salaryProject.sum} ₿",
                fontSize = 14.sp,
                color = Color.Black
            )

            Text(
                text = "Должность: ${salaryProject.info}",
                fontSize = 14.sp,
                color = Color.Black
            )
            Row {
                Text(
                    text = "Статус: ",
                    fontSize = 14.sp,
                    color = Color.Black
                )

                Text(
                    text = when(salaryProject.status){
                        StatusJobBid.REJECTED -> "Отклонено банком"
                        StatusJobBid.WAITING -> "В ожидании"
                        StatusJobBid.ACCEPTED -> "Подтверждено банком"
                    },
                    fontSize = 14.sp,
                    color = when(salaryProject.status){
                        StatusJobBid.REJECTED -> redOrange
                        StatusJobBid.WAITING -> yellow
                        StatusJobBid.ACCEPTED -> green
                    },
                    fontWeight = FontWeight.Bold,
                )
            }

            Text(
                text = salaryProject.company.name,
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp,
                color = green,
                modifier = Modifier.align(Alignment.End)
            )
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun SalaryProjectItemPreview(){
    SalaryProjectItem(
        salaryProject = SalaryProjectCompany(
            id = 1,
            clientBaseUser = null,
            status = StatusJobBid.WAITING,
            company = Company(
                id = 1,
                type = "",
                address = "Гомельская область 111111",
                name = "Белнефтехим",
                unp = ""
            ),
            sum = 1000.0,
            info = "Грузчик"
        ),
    )
}