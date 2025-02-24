package com.example.financysystem.presentation.screens.userScreen.screen.contentClientUserScreen.companyUser

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.domain.models.user.TypeOfUser
import com.example.financysystem.presentation.screens.components.ProfileCard

@Composable
fun CompanyUserProfileScreen(
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
        typeOfUser = TypeOfUser.CompanyUser.name
    )
}