package com.example.data.local.entity.user.operatorUser

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.data.local.entity.user.BaseUserEntity
import com.example.domain.models.user.operatorUser.OperatorUser

@Entity(
    tableName = "operator_users",
    foreignKeys = [
        ForeignKey(
            entity = BaseUserEntity::class,
            parentColumns = ["id"],
            childColumns = ["baseUserId"],
            onDelete = ForeignKey.CASCADE // Удаление BaseUser удалит и OperatorUserEntity
        )
    ],
    indices = [Index(value = ["baseUserId"])] // Оптимизация запросов
)
data class OperatorUserEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val baseUserId: Int,
)

//fun OperatorUserEntity.toDomain() = OperatorUser(
//    baseUser = getBaseUserById(baseUserId),
//    transfers = getAllTransfers(),
//    salariesProjectCompany = getAllSalaryProjects()
//)
