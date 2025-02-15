package com.example.domain.models.user.clientUser

import com.example.domain.models.bank.bankAccount.BankAccount
import com.example.domain.models.salaryProject.SalaryProjectCompany
import com.example.domain.models.user.BaseUser
import com.example.domain.models.user.ClientBank

data class ClientUser(
    val baseUser: BaseUser,
    override val bankAccounts: List<BankAccount>,
    override val salaryProjects: List<SalaryProjectCompany>
) : ClientRole, ClientBank