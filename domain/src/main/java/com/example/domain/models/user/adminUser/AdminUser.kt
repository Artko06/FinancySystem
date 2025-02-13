package com.example.domain.models.user.adminUser

import com.example.domain.models.actionLog.ActionLog
import com.example.domain.models.user.BaseUser

data class AdminUser(
    val baseUser: BaseUser,
    override val actionLogs: List<ActionLog>
) : AdminRole
