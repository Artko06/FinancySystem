package com.example.financysystem.presentation.screens.userScreen.screen.contentClientUserScreen.companyUser

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AttachMoney
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.AlertDialogDefaults
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.financysystem.presentation.screens.components.TextEntry
import com.example.financysystem.ui.theme.gray
import com.example.financysystem.ui.theme.green
import com.example.financysystem.ui.theme.redOrange

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddingSalaryProjectDialog(
    modifier: Modifier = Modifier,
    onOpenDialogAddingSalaryProject: () -> Unit,
    onChangeInfoSalaryProject: (String) -> Unit,
    onChangeSumSalaryProject: (String) -> Unit,
    onAddSalaryProject: () -> Unit,
    infoSalaryProject: String,
    sumSalaryProject: String,
    errorInputSalaryProject: String?
){
    BasicAlertDialog(
        onDismissRequest = { onOpenDialogAddingSalaryProject() },
        modifier = modifier
    )
    {
        Surface(
            modifier = Modifier
                .wrapContentWidth()
                .wrapContentHeight(),
            shape = MaterialTheme.shapes.large,
            tonalElevation = AlertDialogDefaults.TonalElevation
        )
        {
            Column(
                modifier = Modifier.padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Text(text = "Создание зарплатного проекта", fontSize = 24.sp)

                Spacer(modifier = Modifier.height(24.dp))

                TextEntry(
                    modifier = Modifier
                        .fillMaxWidth(),
                    description = "Описание",
                    hint = "",
                    textValue = infoSalaryProject,
                    textColor = gray,
                    keyboardType = KeyboardType.Text,
                    cursorColor = green,
                    onValueChanged = onChangeInfoSalaryProject,
                    trailingIcon = null,
                    onTrailingIconClick = null,
                    leadingIcon = Icons.Default.Info
                )

                TextEntry(
                    modifier = Modifier
                        .fillMaxWidth(),
                    description = "Заработная плата",
                    hint = "",
                    textValue = sumSalaryProject,
                    textColor = gray,
                    keyboardType = KeyboardType.Number,
                    cursorColor = green,
                    onValueChanged = onChangeSumSalaryProject,
                    trailingIcon = null,
                    onTrailingIconClick = null,
                    leadingIcon = Icons.Default.AttachMoney
                )

                Spacer(modifier = Modifier.height(18.dp))

                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {
                        onAddSalaryProject()
                    }
                )
                {
                    Text(text = "Создать")
                }

                if(errorInputSalaryProject != null) {
                    Text(
                        text = errorInputSalaryProject,
                        color = redOrange,
                        fontSize = 10.sp,
                        lineHeight = 10.sp
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AddingSalaryProjectDialogPreview(){
    AddingSalaryProjectDialog(
        onOpenDialogAddingSalaryProject = {},
        onChangeSumSalaryProject = {},
        onChangeInfoSalaryProject = {},
        onAddSalaryProject = {},
        infoSalaryProject = "",
        sumSalaryProject = "",
        errorInputSalaryProject = "Ошибка"
    )
}