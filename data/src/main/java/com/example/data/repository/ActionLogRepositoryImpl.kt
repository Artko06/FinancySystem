package com.example.data.repository

import com.example.data.local.dao.ActionLogDao
import com.example.data.local.dao.UserDao
import com.example.data.local.entity.actionLog.toDomain
import com.example.data.local.entity.actionLog.toEntity
import com.example.data.local.entity.user.toDomain
import com.example.domain.models.actionLog.IActionLog
import com.example.domain.repository.ActionLogRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.map

class ActionLogRepositoryImpl(
    private val actionLogDao: ActionLogDao,
    private val userDao: UserDao
) : ActionLogRepository
{
    @OptIn(ExperimentalCoroutinesApi::class)
    override fun getAllActionLogs(): Flow<List<IActionLog>> {
        return actionLogDao.getAllActionLogs()
            .flatMapMerge { actionLogsEntity ->
                val transformedLogs = actionLogsEntity.map { actionLogEntity ->
                    userDao.getBaseUserById(actionLogEntity.baseUserId)
                        .map { baseUserEntity ->
                            baseUserEntity?.let { actionLogEntity.toDomain(it.toDomain()) }
                        }
                }
                combine(transformedLogs) { it.filterNotNull() } // Фильтруем null-значения
            }
    }

    override suspend fun insertActionLog(actionLog: IActionLog) {
        actionLogDao.insertActionLog(
            actionLog = actionLog.toEntity(actionLog.baseUser.id)
        )
    }

    override suspend fun deleteActionLog(actionLog: IActionLog) {
        actionLogDao.deleteActionLog(
            actionLog = actionLog.toEntity(actionLog.baseUser.id)
        )
    }
}