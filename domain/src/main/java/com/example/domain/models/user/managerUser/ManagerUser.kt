package com.example.domain.models.user.managerUser

import com.example.domain.models.user.BaseUser

data class ManagerUser(
    val baseUser: BaseUser,
    val managerUserId: Int
)