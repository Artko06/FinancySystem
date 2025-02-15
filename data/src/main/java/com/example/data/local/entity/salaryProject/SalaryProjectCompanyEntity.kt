package com.example.data.local.entity.salaryProject

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.domain.models.salaryProject.SalaryProjectCompany
import com.example.domain.models.salaryProject.StatusJobBid

@Entity(tableName = "salary_project_companies")
data class SalaryProjectCompanyEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val clientUserId: Int,
    val companyId: Int,
    val status: String
)

//fun SalaryProjectCompanyEntity.toDomain() = SalaryProjectCompany(
//    id = id,
//    client = getClientUserById(clientUserId = clientUserId),
//    company = getCompanyById(companyId = companyId),
//    status = enumValueOf<StatusJobBid>(status)
//)
