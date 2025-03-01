package com.example.domain.useCase.allUserCases.actionLogUseCases.insert

import com.example.domain.models.actionLog.ActionLog
import com.example.domain.models.actionLog.ActionType
import com.example.domain.models.user.BaseUser
import com.example.domain.repository.ActionLogRepository

class InsertActionLogUseCase(
    private val actionLogRepository: ActionLogRepository
)
{
    suspend operator fun invoke(
        baseUser: BaseUser,
        actionType: ActionType,
        date: String,
        time: String
    ){
        val actionLog = ActionLog(
            id = 0,
            baseUser = baseUser,
            actionType = actionType,
            date = date,
            time = time
        )

        actionLogRepository.insertActionLog(actionLog)
    }
}