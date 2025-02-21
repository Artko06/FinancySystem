package com.example.financysystem.presentation.screens.components

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.financysystem.ui.theme.green


@Composable
fun TextEntry(
    description: String,
    hint: String,
    leadingIcon: ImageVector,
    textValue: String,
    keyboardType: KeyboardType = KeyboardType.Ascii,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    textColor: Color,
    cursorColor: Color,
    onValueChanged: (String) -> Unit,
    trailingIcon: ImageVector? = null,
    onTrailingIconClick: (() -> Unit)?,
    modifier: Modifier = Modifier
) {

    Column(
        modifier = modifier
    ) {
        Text(
            text = description,
            color = textColor
        )
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 3.dp)
                .shadow(3.dp, RoundedCornerShape(25.dp))
                .border(1.5.dp, textColor, RoundedCornerShape(15.dp))
                .height(50.dp),
            value = textValue,
            colors = TextFieldDefaults.colors(
                cursorColor = cursorColor
            ),
            onValueChange = onValueChanged,
            shape = RoundedCornerShape(15.dp),
            singleLine = true,
            leadingIcon = {
                Icon(
                    imageVector = leadingIcon,
                    contentDescription = null,
                    tint = cursorColor
                )
            },
            trailingIcon = {
                trailingIcon?.let {
                    Icon(
                        imageVector = it,
                        contentDescription = null,
                        tint = cursorColor,
                        modifier = Modifier.clickable { onTrailingIconClick?.invoke() }
                    )
                }
            },
            placeholder = {
                Text(
                    text = hint,
                    color = Color.Gray
                )
            },
            keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
            visualTransformation = visualTransformation
        )

    }
}

@Preview(showBackground = true)
@Composable
fun TextEntryPreview() {
    TextEntry(
        description = "Email address",
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp, 0.dp, 10.dp, 5.dp),
        hint = "adress@bsuir.by",
        leadingIcon = Icons.Default.Email,
        textValue = "Some text",
        textColor = Color.Black,
        cursorColor = green,
        onValueChanged = {},
        trailingIcon = Icons.Default.Visibility,
        onTrailingIconClick = {},
        visualTransformation = PasswordVisualTransformation()
    )
}
