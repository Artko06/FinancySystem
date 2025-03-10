package com.example.financysystem.presentation.screens.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.financysystem.ui.theme.green

@Composable
fun RadioButtonGroup(
    options: List<String>,
    selectedOption: String,
    onOptionSelected: (String) -> Unit,
    fontSize: TextUnit,
    sizeRadio: Dp,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        options.forEach { option ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onOptionSelected(option) }
                    .padding(8.dp)
            ) {
                RadioButton(
                    selected = option == selectedOption,
                    onClick = { onOptionSelected(option) },
                    modifier = Modifier.size(sizeRadio)
                )

                Text(
                    text = option,
                    modifier = Modifier.padding(start = 8.dp),
                    color = green,
                    fontWeight = FontWeight.Medium,
                    fontSize = fontSize
                )
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun RadioButtonGroupPreview(){
    RadioButtonGroup(
        options = listOf("Опция 1", "Опция 2", "Опция 3"),
        selectedOption = "Опция 1",
        onOptionSelected = {},
        fontSize = 10.sp,
        sizeRadio = 20.dp
    )
}