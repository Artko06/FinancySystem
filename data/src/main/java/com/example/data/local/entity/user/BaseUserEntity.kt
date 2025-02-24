package com.example.data.local.entity.user

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.domain.models.user.BaseUser
import com.example.domain.models.user.TypeOfUser

@Entity(tableName = "base_users")
data class BaseUserEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val firstName: String,
    val lastName: String,
    val surName: String,
    val seriesPassport: String,
    val numberPassport: String,
    val identityNumber: String,
    val phone: String,
    val email: String,
    val typeOfUser: String,
)

fun BaseUserEntity.toDomain() = BaseUser(
    id = id,
    firstName = firstName,
    lastName = lastName,
    surName = surName,
    seriesPassport = seriesPassport,
    numberPassport = numberPassport,
    identityNumber = identityNumber,
    phone = phone,
    email = email,
    typeOfUser = enumValueOf<TypeOfUser>(typeOfUser)
)

fun BaseUser.toEntity() = BaseUserEntity(
    id = id,
    firstName = firstName,
    lastName = lastName,
    surName = surName,
    seriesPassport = seriesPassport,
    numberPassport = numberPassport,
    identityNumber = identityNumber,
    phone = phone,
    email = email,
    typeOfUser = typeOfUser.toString()
)
