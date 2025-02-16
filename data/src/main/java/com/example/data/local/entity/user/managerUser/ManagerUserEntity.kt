package com.example.data.local.entity.user.managerUser

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.data.local.entity.user.BaseUserEntity
import com.example.domain.models.user.BaseUser
import com.example.domain.models.user.managerUser.ManagerUser

@Entity(
    tableName = "manager_users",
    foreignKeys = [
        ForeignKey(
            entity = BaseUserEntity::class,
            parentColumns = ["id"],
            childColumns = ["baseUserId"],
            onDelete = ForeignKey.CASCADE // Удаление BaseUser удалит и ManagerUserEntity
        )
    ],
    indices = [Index(value = ["baseUserId"])] // Оптимизация запросов
)
data class ManagerUserEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val baseUserId: Int,
)

fun ManagerUserEntity.toDomain(baseUser: BaseUser) = ManagerUser(
    baseUser = baseUser,
    managerUserId = id
)

fun ManagerUser.toEntity(baseUserId: Int) = ManagerUserEntity(
    baseUserId = baseUserId,
    id = managerUserId
)
