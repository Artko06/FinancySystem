package com.example.financysystem.presentation.screens.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.financysystem.ui.theme.green

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropDownMenu(
    modifier: Modifier = Modifier,
    selectedIndex: Int,
    isOpenMenu: Boolean,
    onSelect: (Int) -> Unit,
    onToggleMenu: () -> Unit,
    items: List<String>
) {
    // Кнопка для раскрытия меню
    ExposedDropdownMenuBox(
        modifier = Modifier
            .then(modifier)
            .padding(16.dp),
        expanded = isOpenMenu,
        onExpandedChange = {
            onToggleMenu()
        }
    ) {
        OutlinedTextField(
            value = items[selectedIndex],
            onValueChange = {},
            readOnly = true,
            label = {
                Text(
                    text = "Банк",
                    color = green,
                    fontWeight = FontWeight.Bold
                )
            },
            trailingIcon = {
                Icon(
                    imageVector = when (isOpenMenu) {
                        true -> Icons.Filled.ArrowDropUp
                        false -> Icons.Filled.ArrowDropDown
                    },
                    contentDescription = null,
                    tint = green
                )
            },
            modifier = Modifier.menuAnchor()
        )

        // Всплывающее меню со списком банков
        ExposedDropdownMenu(
            expanded = isOpenMenu,
            onDismissRequest = { if (isOpenMenu) onToggleMenu() }
        ) {
            items.onEachIndexed { index ,item ->
                DropdownMenuItem(
                    text = { Text(item) },
                    onClick = {
                        if (isOpenMenu) onToggleMenu()
                        onSelect(index)
                    }
                )
            }
        }
    }
}

//@Preview(showSystemUi = true)
//@Composable
//fun DropDownMenuBanksPreview() {
//    DropDownMenuBanks(
//        modifier = Modifier.padding(top = 200.dp),
//        selectedIndexBank = 0,
//        isOpenMenuBank = false,
//        onToggleMenuBank = {},
//        onSelectBank = {},
//        banks = listOf(
//            Bank(id = 0, name = "Беларусбанк", bic = ""),
//            Bank(id = 1, name = "Беларусбанк", bic = ""),
//            Bank(id = 2, name = "Беларусбанк", bic = "")
//        )
//    )
//}