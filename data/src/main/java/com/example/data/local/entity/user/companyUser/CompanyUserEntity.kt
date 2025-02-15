package com.example.data.local.entity.user.companyUser

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.data.local.entity.user.BaseUserEntity
import com.example.domain.models.user.companyUser.CompanyUser

@Entity(
    tableName = "company_users",
    foreignKeys = [
        ForeignKey(
            entity = BaseUserEntity::class,
            parentColumns = ["id"],
            childColumns = ["baseUserId"],
            onDelete = ForeignKey.CASCADE // Удаление BaseUser удалит и CompanyUserEntity
        )
    ],
    indices = [Index(value = ["baseUserId"])] // Оптимизация запросов
)
data class CompanyUserEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val baseUserId: Int,
    val companyId : Int
)

//fun CompanyUserEntity.toDomain() = CompanyUser(
//    baseUser = getBaseUserById(baseUserId = baseUserId),
//    company = getCompanyById(companyId = companyId),
//    companyBankAccounts = getCompanyBankAccountsByCompanyId(companyId = companyId)
//)
