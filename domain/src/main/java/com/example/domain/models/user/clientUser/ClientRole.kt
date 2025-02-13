package com.example.domain.models.user.clientUser

import com.example.domain.models.bank.bankAccount.IBankAccount
import com.example.domain.models.salaryProject.SalaryProjectCompany

interface ClientRole {
    val salaryProjects: List<SalaryProjectCompany>
}