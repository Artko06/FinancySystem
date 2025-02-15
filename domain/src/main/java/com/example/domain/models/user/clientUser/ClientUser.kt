package com.example.domain.models.user.clientUser

import com.example.domain.models.bank.bankAccount.creditBankAccount.ICreditBankAccount
import com.example.domain.models.bank.bankAccount.standartBankAccount.IStandardBankAccount
import com.example.domain.models.salaryProject.ISalaryProjectCompany
import com.example.domain.models.user.BaseUser
import com.example.domain.models.user.DefaultClientBank

data class ClientUser(
    val baseUser: BaseUser,
    override val standardBankAccounts: List<IStandardBankAccount>,
    override val creditBankAccounts: List<ICreditBankAccount>,
    override val salariesProjectsCompany: List<ISalaryProjectCompany>
) : ClientRole, DefaultClientBank