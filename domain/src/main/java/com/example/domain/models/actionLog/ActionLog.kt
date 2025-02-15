package com.example.domain.models.actionLog

import com.example.domain.models.user.BaseUser

data class ActionLog(
    override val id: Int,
    override val baseUser: BaseUser, // Кто совершил действие
    override val actionType: ActionType, // Тип лога
    override val date: String, // Дата действия
    override val time: String, // Время действия
) : IActionLog
