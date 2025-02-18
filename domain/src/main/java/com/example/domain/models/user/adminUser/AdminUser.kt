package com.example.domain.models.user.adminUser

import com.example.domain.models.user.BaseUser

data class AdminUser(
    val baseUser: BaseUser,
    val adminUserId: Int
)