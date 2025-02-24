package com.example.domain.models.user

data class BaseUser(
    val id: Int,

    val firstName: String,
    val lastName: String,
    val surName: String,

    val seriesPassport: String,
    val numberPassport: String,
    val identityNumber: String,

    val phone: String,
    val email: String,

    val typeOfUser: TypeOfUser
)
