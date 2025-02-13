package com.example.domain.models.actionLog

data class ActionLog(
    override val id: String,
    override val userId: String, // Кто совершил действие
    override val actionType: ActionType, // Тип лога
    override val date: String, // Дата действия
    override val time: String, // Время действия
) : IActionLog
