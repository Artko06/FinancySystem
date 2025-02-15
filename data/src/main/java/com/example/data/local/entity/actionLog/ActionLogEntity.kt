package com.example.data.local.entity.actionLog

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.domain.models.actionLog.ActionLog
import com.example.domain.models.actionLog.ActionType

@Entity(tableName = "action_logs")
data class ActionLogEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val baseUserId: Int,
    val actionType: String,
    val date: String,
    val time: String
)

//fun ActionLogEntity.toDomain() = ActionLog(
//    id = id,
//    baseUser = getBaseUserById(baseUserId = baseUserId),
//    actionType = enumValueOf<ActionType>(actionType),
//    date = date,
//    time = time
//)