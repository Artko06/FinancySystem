package com.example.domain.models.salaryProject

import com.example.domain.models.company.Company
import com.example.domain.models.user.BaseUser

data class SalaryProjectCompany(
    val id: Int,
    override val clientBaseUser: BaseUser,  // Клиент, который подал заявку
    override val company: Company,
    override val status: StatusJobBid // Статус заявки
) : ISalaryProjectCompany
