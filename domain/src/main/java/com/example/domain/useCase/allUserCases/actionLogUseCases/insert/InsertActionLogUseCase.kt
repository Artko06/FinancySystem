package com.example.domain.useCase.allUserCases.actionLogUseCases.insert

import com.example.domain.models.actionLog.IActionLog
import com.example.domain.repository.ActionLogRepository

class InsertActionLogUseCase(
    private val actionLogRepository: ActionLogRepository
)
{
    suspend operator fun invoke(actionLog: IActionLog){
        actionLogRepository.insertActionLog(actionLog = actionLog)
    }
}