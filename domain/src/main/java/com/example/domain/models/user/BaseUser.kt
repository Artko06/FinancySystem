package com.example.domain.models.user

data class BaseUser(
    val id: Int,

    val firstName: String,
    val lastName: String,
    val surName: String,

    val seriesPassword: String,
    val numberPassword: String,
    val identityNumber: String,

    val phone: String,
    val email: String
)
