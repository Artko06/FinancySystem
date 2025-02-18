package com.example.domain.models.user.operatorUser

import com.example.domain.models.user.BaseUser

data class OperatorUser(
    val baseUser: BaseUser,
    val operatorUserId: Int
)