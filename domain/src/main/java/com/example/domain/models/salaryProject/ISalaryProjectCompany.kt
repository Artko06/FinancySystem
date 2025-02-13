package com.example.domain.models.salaryProject

import com.example.domain.models.company.Company
import com.example.domain.models.user.clientUser.ClientRole

interface ISalaryProjectCompany {
    val id: String
    val client: ClientRole  // ID клиента, который подал заявку
    val company: Company // ID предприятия
    val status: StatusJobBid // Статус заявки
}