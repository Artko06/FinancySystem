package com.example.data.local.entity.salaryProject

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.domain.models.company.Company
import com.example.domain.models.salaryProject.SalaryProjectCompany
import com.example.domain.models.salaryProject.StatusJobBid
import com.example.domain.models.user.BaseUser

@Entity(tableName = "salary_project_companies")
data class SalaryProjectCompanyEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val clientBaseUserId: Int?,
    val companyId: Int,
    val status: String,
    val sum: Double,
    val info: String
)

fun SalaryProjectCompanyEntity.toDomain(
    clientBaseUser: BaseUser?,
    company: Company
) = SalaryProjectCompany(
    id = id,
    clientBaseUser = clientBaseUser,
    company = company,
    status = enumValueOf<StatusJobBid>(status),
    sum = sum,
    info = info
)

fun SalaryProjectCompany.toEntity(
    clientBaseUserId: Int?,
    companyId: Int
) = SalaryProjectCompanyEntity(
    id = id,
    clientBaseUserId = clientBaseUserId,
    companyId = companyId,
    status = status.toString(),
    sum = sum,
    info = info
)

