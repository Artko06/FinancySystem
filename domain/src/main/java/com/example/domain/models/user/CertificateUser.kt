package com.example.domain.models.user

data class CertificateUser(
    val baseUser: BaseUser,
    val id: Int,
    val hashedPassword: String
)