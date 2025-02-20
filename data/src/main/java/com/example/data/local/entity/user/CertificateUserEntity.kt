package com.example.data.local.entity.user

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.domain.models.user.BaseUser
import com.example.domain.models.user.CertificateUser

@Entity(
    tableName = "certificate_users",
    foreignKeys = [
        ForeignKey(
            entity = BaseUserEntity::class,
            parentColumns = ["id"],
            childColumns = ["baseUserId"],
            onDelete = ForeignKey.CASCADE // Удаление BaseUser удалит и OperatorUserEntity
        )
    ],
    indices = [Index(value = ["baseUserId"])] // Оптимизация запросов)
)
data class CertificateUserEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val baseUserId: Int, // Внешний ключ к пользователю
    val hashedPassword: String
)


fun CertificateUserEntity.toDomain(baseUser: BaseUser) = CertificateUser(
    id = id,
    baseUser = baseUser,
    hashedPassword = hashedPassword
)

fun CertificateUser.toEntity(baseUserId: Int) = CertificateUserEntity(
    id = id,
    baseUserId = baseUserId,
    hashedPassword = hashedPassword
)


