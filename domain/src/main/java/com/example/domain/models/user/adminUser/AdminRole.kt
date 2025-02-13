package com.example.domain.models.user.adminUser

import com.example.domain.models.actionLog.ActionLog

interface AdminRole {
    val actionLogs: List<ActionLog>
}