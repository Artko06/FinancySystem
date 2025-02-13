package com.example.domain.models.user.clientUser

import com.example.domain.models.bank.bankAccount.IBankAccount
import com.example.domain.models.salaryProject.SalaryProjectCompany
import com.example.domain.models.user.BaseUser
import com.example.domain.models.user.ClientBank

class ClientUser(
    val baseUser: BaseUser,
    override val bankAccounts: List<IBankAccount>,
    override val salaryProjects: List<SalaryProjectCompany>
) : ClientRole, ClientBank