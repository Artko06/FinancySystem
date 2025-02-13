package com.example.domain.models.user.managerUser

import com.example.domain.models.bank.bankAccount.IBankAccount
import com.example.domain.models.transfer.ITransfer
import com.example.domain.models.user.BaseUser
import com.example.domain.models.user.operatorUser.OperatorRole

data class ManagerUser(
    val baseUser: BaseUser,
    override val idTransfers: List<ITransfer>,
    override val idCreditsAccount: List<IBankAccount>
) : OperatorRole, ManagerRole