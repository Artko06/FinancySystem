package com.example.data.local.entity.user.clientUser

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.data.local.entity.user.BaseUserEntity
import com.example.domain.models.user.BaseUser
import com.example.domain.models.user.clientUser.ClientUser

@Entity(
    tableName = "client_users",
    foreignKeys = [
        ForeignKey(
            entity = BaseUserEntity::class,
            parentColumns = ["id"],
            childColumns = ["baseUserId"],
            onDelete = ForeignKey.CASCADE // Удаление BaseUser удалит и ClientUserEntity
        )
    ],
    indices = [Index(value = ["baseUserId"])] // Оптимизация запросов
)
data class ClientUserEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val baseUserId: Int
)

fun ClientUserEntity.toDomain(baseUser: BaseUser) = ClientUser(
    clientUserId = id,
    baseUser = baseUser
)

fun ClientUser.toEntity(baseUserId: Int) = ClientUserEntity(
    id = clientUserId,
    baseUserId = baseUserId
)

