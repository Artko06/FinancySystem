package com.example.domain.useCase.allUserCases.actionLogUseCases.get

import com.example.domain.models.actionLog.IActionLog
import com.example.domain.repository.ActionLogRepository
import kotlinx.coroutines.flow.Flow

class GetAllActionLogsUseCase(
    private val actionLogRepository: ActionLogRepository
)
{
    operator fun invoke() : Flow<List<IActionLog>> {
        return actionLogRepository.getAllActionLogs()
    }
}