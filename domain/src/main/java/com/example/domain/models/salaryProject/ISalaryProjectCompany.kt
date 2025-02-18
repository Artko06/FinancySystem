package com.example.domain.models.salaryProject

import com.example.domain.models.company.Company
import com.example.domain.models.user.BaseUser

interface ISalaryProjectCompany {
    val clientBaseUser: BaseUser  // Клиент, который подал заявку
    val company: Company
    val status: StatusJobBid // Статус заявки
}