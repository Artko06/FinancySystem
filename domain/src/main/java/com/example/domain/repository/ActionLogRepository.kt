package com.example.domain.repository

import com.example.domain.models.actionLog.IActionLog
import kotlinx.coroutines.flow.Flow

interface ActionLogRepository {
    fun getAllActionLogs(): Flow<List<IActionLog>>
    suspend fun insertActionLog(actionLog: IActionLog)
    suspend fun deleteActionLog(actionLog: IActionLog)
}