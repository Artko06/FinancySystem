package com.example.domain.models.user.operatorUser

import com.example.domain.models.transfer.ITransfer

interface OperatorRole {
    val idTransfers: List<ITransfer>
}