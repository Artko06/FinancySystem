package com.example.domain.roles

import com.example.domain.models.actionLog.IActionLog
import kotlinx.coroutines.flow.Flow

interface AdminUserRole {
    fun getAllActionLogs() : Flow<List<IActionLog>>
}

// Все логи