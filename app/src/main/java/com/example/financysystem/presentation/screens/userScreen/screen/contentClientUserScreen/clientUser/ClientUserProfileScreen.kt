package com.example.financysystem.presentation.screens.userScreen.screen.contentClientUserScreen.clientUser

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.domain.models.user.TypeOfUser
import com.example.financysystem.presentation.screens.components.ProfileCard

@Composable
fun ClientUserProfileScreen(
    modifier: Modifier = Modifier,
    firstName: String,
    lastName: String,
    surName: String,
    email: String,
    phone: String,
){
    ProfileCard(
        modifier = modifier,
        firstName = firstName,
        lastName = lastName,
        surName = surName,
        email = email,
        phone = phone,
        typeOfUser = TypeOfUser.ClientUser.name
    )
}


@Preview(showSystemUi = true)
@Composable
fun ProfileScreenPreview(){
    ClientUserProfileScreen(
        lastName = "Кохан",
        firstName = "Aртём",
        surName = "Игоревич",
        email = "KOXAN@bsuir.by",
        phone = "+375251111111",
        modifier = Modifier.padding(top = 200.dp)
    )
}