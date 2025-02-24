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
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.financysystem.ui.theme.green
import com.example.financysystem.ui.theme.whiteGray

@Composable
fun ProfileCard(
    modifier: Modifier = Modifier,
    firstName: String,
    lastName: String,
    surName: String,
    email: String,
    phone: String,
    typeOfUser: String
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
                    imageVector = Icons.Filled.AccountCircle,
                    contentDescription = "User Icon",
                    tint = green,
                    modifier = Modifier.size(42.dp)
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            // Text Section
            Column(
                verticalArrangement = Arrangement.spacedBy(3.dp),

                ) {
                Text(
                    text = "$lastName $firstName $surName",
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 18.sp,
                    color = green
                )
                Text(
                    text = typeOfUser,
                    fontWeight = FontWeight.Medium,
                    fontSize = 16.sp,
                    color = Color.Black
                )
                Text(
                    text = email,
                    fontWeight = FontWeight.Medium,
                    fontSize = 14.sp,
                    color = Color.Black
                )
                Text(
                    text = phone,
                    fontWeight = FontWeight.Medium,
                    fontSize = 14.sp,
                    color = Color.Black
                )
            }
        }
    }
}