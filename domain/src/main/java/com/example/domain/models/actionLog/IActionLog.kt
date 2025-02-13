package com.example.domain.models.actionLog

interface IActionLog {
    val id: String
    val userId: String // Кто совершил действие
    val actionType: ActionType // Тип лога
    val date: String // Дата действия
    val time: String // Время действия
}