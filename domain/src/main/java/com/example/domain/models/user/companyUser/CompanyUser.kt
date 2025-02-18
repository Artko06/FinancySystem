package com.example.domain.models.user.companyUser

import com.example.domain.models.company.Company
import com.example.domain.models.user.BaseUser

data class CompanyUser(
    val baseUser: BaseUser,
    val companyUserId: Int,
    val company: Company
)