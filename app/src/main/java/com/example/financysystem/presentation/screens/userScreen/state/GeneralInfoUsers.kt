package com.example.financysystem.presentation.screens.userScreen.state

import com.example.domain.models.user.TypeOfUser

interface GeneralInfoUsers {
    val id: Int
    val email: String
    val phone: String
    val firstName: String
    val lastName: String
    val surName: String
    val typeOfUser: TypeOfUser
}