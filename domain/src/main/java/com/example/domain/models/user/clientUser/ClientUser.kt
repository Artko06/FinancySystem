package com.example.domain.models.user.clientUser

import com.example.domain.models.user.BaseUser

data class ClientUser(
    val baseUser: BaseUser,
    val clientUserId: Int
)