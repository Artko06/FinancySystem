package com.example.domain.models.actionLog

import com.example.domain.models.user.BaseUser

interface IActionLog {
    val id: Int
    val baseUser: BaseUser // Кто совершил действие
    val actionType: ActionType // Тип лога
    val date: String // Дата действия
    val time: String // Время действия
}