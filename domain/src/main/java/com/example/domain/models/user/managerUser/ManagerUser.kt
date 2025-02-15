package com.example.domain.models.user.managerUser

import com.example.domain.models.bank.bankAccount.BankAccount
import com.example.domain.models.salaryProject.ISalaryProjectCompany
import com.example.domain.models.transfer.ITransfer
import com.example.domain.models.user.BaseUser
import com.example.domain.models.user.operatorUser.OperatorRole

data class ManagerUser(
    val baseUser: BaseUser,
    override val transfers: List<ITransfer>,
    override val salaryProjectCompany: List<ISalaryProjectCompany>,
    override val bankAccounts: List<BankAccount>
) : OperatorRole, ManagerRole