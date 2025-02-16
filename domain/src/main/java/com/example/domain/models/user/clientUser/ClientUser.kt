package com.example.domain.models.user.clientUser

import com.example.domain.models.bank.bankAccount.creditBankAccount.ICreditBankAccount
import com.example.domain.models.bank.bankAccount.standartBankAccount.IStandardBankAccount
import com.example.domain.models.salaryProject.ISalaryProjectCompany
import com.example.domain.models.user.BaseUser

data class ClientUser(
    val baseUser: BaseUser,
    val clientUserId: Int,
)