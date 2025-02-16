package com.example.data.local.entity.actionLog

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.domain.models.actionLog.ActionLog
import com.example.domain.models.actionLog.ActionType
import com.example.domain.models.actionLog.IActionLog
import com.example.domain.models.user.BaseUser

@Entity(tableName = "action_logs")
data class ActionLogEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val baseUserId: Int,
    val actionType: String,
    val date: String,
    val time: String
)

fun ActionLogEntity.toDomain(baseUser: BaseUser) = ActionLog(
    id = id,
    baseUser = baseUser,
    actionType = enumValueOf<ActionType>(actionType),
    date = date,
    time = time
)

fun IActionLog.toEntity(baseUserId: Int) = ActionLogEntity(
    id = id,
    baseUserId = baseUserId,
    actionType = actionType.toString(),
    date = date,
    time = time
)