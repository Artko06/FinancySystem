package com.example.financysystem.presentation.screens.userScreen.screen.contentClientUserScreen.managerUser

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.domain.models.user.TypeOfUser
import com.example.financysystem.presentation.screens.components.ProfileCard

@Composable
fun ManagerUserProfileScreen(
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
        typeOfUser = TypeOfUser.ManagerUser.name
    )
}