package com.example.domain.models.salaryProject

import com.example.domain.models.company.Company
import com.example.domain.models.user.clientUser.ClientRole

interface ISalaryProjectCompany {
    val id: Int
    val client: ClientRole  // Клиент, который подал заявку
    val company: Company
    val status: StatusJobBid // Статус заявки
}