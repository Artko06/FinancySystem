package com.example.domain.models.salaryProject

import com.example.domain.models.company.Company
import com.example.domain.models.user.clientUser.ClientRole

data class SalaryProjectCompany(
    override val id: Int,
    override val client: ClientRole,  // Клиент, который подал заявку
    override val company: Company,
    override val status: StatusJobBid // Статус заявки
) : ISalaryProjectCompany
