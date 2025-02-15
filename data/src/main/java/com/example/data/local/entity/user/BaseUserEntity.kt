package com.example.data.local.entity.user

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.domain.models.user.BaseUser

@Entity(tableName = "base_users")
data class BaseUserEntity(
    @PrimaryKey(autoGenerate = true)
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

fun BaseUserEntity.toDomain() = BaseUser(
    id = id,
    firstName = firstName,
    lastName = lastName,
    surName = surName,
    seriesPassword = seriesPassword,
    numberPassword = numberPassword,
    identityNumber = identityNumber,
    phone = phone,
    email = email
)
