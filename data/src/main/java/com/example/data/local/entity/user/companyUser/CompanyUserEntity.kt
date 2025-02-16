package com.example.data.local.entity.user.companyUser

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.data.local.entity.user.BaseUserEntity
import com.example.domain.models.company.Company
import com.example.domain.models.user.BaseUser
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

fun CompanyUserEntity.toDomain(baseUser: BaseUser, company: Company) = CompanyUser(
    baseUser = baseUser,
    companyUserId = id,
    company = company
)

fun CompanyUser.toEntity(baseUserId: Int, companyId: Int) = CompanyUserEntity(
    baseUserId = baseUserId,
    id = companyUserId,
    companyId = companyId
)
