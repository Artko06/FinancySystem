package com.example.domain.models.user.operatorUser

import com.example.domain.models.transfer.ITransfer
import com.example.domain.models.user.BaseUser

data class OperatorUser(
    val baseUser: BaseUser,
    override val idTransfers: List<ITransfer>
) : OperatorRole