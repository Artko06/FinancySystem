package com.example.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.example.data.local.entity.actionLog.ActionLogEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ActionLogDao {
    @Query("SELECT * FROM action_logs ORDER BY date DESC, time DESC")
    fun getAllActionLogs(): Flow<List<ActionLogEntity>>

    @Upsert
    suspend fun insertActionLog(actionLog: ActionLogEntity)

    @Delete
    suspend fun deleteActionLog(actionLog: ActionLogEntity)
}