package com.example.data.local.entity.user.adminUser

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.data.local.entity.user.BaseUserEntity
import com.example.domain.models.user.adminUser.AdminUser


@Entity(
    tableName = "admin_users",
    foreignKeys = [
        ForeignKey(
            entity = BaseUserEntity::class,
            parentColumns = ["id"],
            childColumns = ["baseUserId"],
            onDelete = ForeignKey.CASCADE // Удаление BaseUser удалит и AdminUserEntity
        )
    ],
    indices = [Index(value = ["baseUserId"])] // Оптимизация запросов
)
data class AdminUserEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val baseUserId: Int
)

//fun AdminUserEntity.toDomain() = AdminUser(
//    baseUser = getBaseUserById(baseUserId),
//    actionLogs = getAllActionLogs()
//)

