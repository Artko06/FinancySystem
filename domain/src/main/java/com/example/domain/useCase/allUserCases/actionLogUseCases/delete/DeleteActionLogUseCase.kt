package com.example.domain.useCase.allUserCases.actionLogUseCases.delete

import com.example.domain.models.actionLog.IActionLog
import com.example.domain.repository.ActionLogRepository

class DeleteActionLogUseCase(
    private val actionLogRepository: ActionLogRepository
)
{
    suspend operator fun invoke(actionLog: IActionLog){
        actionLogRepository.deleteActionLog(actionLog = actionLog)
    }
}