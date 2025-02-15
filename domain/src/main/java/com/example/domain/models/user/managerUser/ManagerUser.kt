package com.example.domain.models.user.managerUser

import com.example.domain.models.bank.bankAccount.BaseBankAccount
import com.example.domain.models.salaryProject.ISalaryProjectCompany
import com.example.domain.models.transfer.ITransfer
import com.example.domain.models.user.BaseUser
import com.example.domain.models.user.operatorUser.OperatorRole

data class ManagerUser(
    val baseUser: BaseUser,
    override val transfers: List<ITransfer>,
    override val salaryProjectsCompany: List<ISalaryProjectCompany>,
    override val baseBankAccounts: List<BaseBankAccount>
) : OperatorRole, ManagerRole