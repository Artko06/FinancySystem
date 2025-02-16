package com.example.domain.models.user.managerUser

import com.example.domain.models.bank.bankAccount.BaseBankAccount
import com.example.domain.models.salaryProject.ISalaryProjectCompany
import com.example.domain.models.transfer.ITransfer
import com.example.domain.models.user.BaseUser

data class ManagerUser(
    val baseUser: BaseUser,
    val managerUserId: Int,
)