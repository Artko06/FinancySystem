package com.example.data.repository

import android.util.Log
import com.example.data.local.dao.CompanyDao
import com.example.data.local.dao.SalaryProjectDao
import com.example.data.local.dao.UserDao
import com.example.data.local.entity.company.toDomain
import com.example.data.local.entity.salaryProject.toDomain
import com.example.data.local.entity.salaryProject.toEntity
import com.example.data.local.entity.user.toDomain
import com.example.domain.models.salaryProject.ISalaryProjectCompany
import com.example.domain.models.salaryProject.SalaryProjectCompany
import com.example.domain.models.salaryProject.StatusJobBid
import com.example.domain.repository.SalaryProjectRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.flowOf

@OptIn(ExperimentalCoroutinesApi::class)
class SalaryRepositoryImpl(
    private val salaryProjectDao: SalaryProjectDao,
    private val userDao: UserDao,
    private val companyDao: CompanyDao
) : SalaryProjectRepository
{

    override fun getAllSalaryProjects(): Flow<List<ISalaryProjectCompany>> {
        return salaryProjectDao.getAllSalaryProjects()
            .flatMapMerge { entities ->
                val transformedFlows = entities.map { entity ->
                    val userFlow = if (entity.clientBaseUserId != null) {
                        userDao.getBaseUserById(entity.clientBaseUserId)
                    } else {
                        flowOf(null)
                    }

                    val companyFlow = companyDao.getCompanyById(entity.companyId)

                    combine(userFlow, companyFlow) { baseUser, company ->
                        if (company != null) { // Компания обязательна, а клиент может быть null
                            entity.toDomain(
                                clientBaseUser = baseUser?.toDomain(),
                                company = company.toDomain()
                            )
                        } else {
                            null // Если компании нет, убираем всю запись
                        }
                    }
                }

                if (transformedFlows.isEmpty()) {
                    flowOf(emptyList()) // Если нет данных, отдаём пустой список
                } else {
                    combine(transformedFlows) { it.filterNotNull() }
                }
            }

    }

    override fun getSalaryProjectsByCompanyId(companyId: Int): Flow<List<ISalaryProjectCompany>> {
        return salaryProjectDao.getSalaryProjectsByCompanyId(companyId)
            .flatMapMerge { entities ->
                val transformedFlows = entities.map { entity ->
                    val userFlow = if (entity.clientBaseUserId != null) {
                        userDao.getBaseUserById(entity.clientBaseUserId)
                    } else {
                        flowOf(null)
                    }

                    val companyFlow = companyDao.getCompanyById(entity.companyId)

                    combine(userFlow, companyFlow) { baseUser, company ->
                        if (company != null) {
                            entity.toDomain(
                                clientBaseUser = baseUser?.toDomain(),
                                company = company.toDomain()
                            )
                        } else {
                            null
                        }
                    }.catch { e ->
                        Log.e("SalaryRepository", "Error fetching data for Salary Project: ${e.message}")
                    }
                }

                if (transformedFlows.isEmpty()) {
                    flowOf(emptyList())
                } else {
                    combine(transformedFlows) { it.filterNotNull() }
                }
            }
    }

    override fun getSalaryProjectsByClientBaseUserId(clientBaseUserId: Int?): Flow<List<ISalaryProjectCompany>> {
        return salaryProjectDao.getSalaryProjectsByClientBaseUserId(clientBaseUserId)
            .flatMapMerge { entities ->
                val transformedFlows = entities.map { entity ->
                    val userFlow = if (entity.clientBaseUserId != null) {
                        userDao.getBaseUserById(entity.clientBaseUserId)
                    } else {
                        flowOf(null)
                    }

                    val companyFlow = companyDao.getCompanyById(entity.companyId)

                    combine(userFlow, companyFlow) { baseUser, company ->
                        if (company != null) {
                            entity.toDomain(
                                clientBaseUser = baseUser?.toDomain(),
                                company = company.toDomain()
                            )
                        } else {
                            null
                        }
                    }
                }

                if (transformedFlows.isEmpty()) {
                    flowOf(emptyList())
                } else {
                    combine(transformedFlows) { it.filterNotNull() }
                }
            }
    }

    override fun getSalaryProjectsByStatus(status: StatusJobBid): Flow<List<ISalaryProjectCompany>> {
        return salaryProjectDao.getSalaryProjectsByStatus(status.toString())
            .flatMapMerge { entities ->
                val transformedFlows = entities.map { entity ->
                    val userFlow = if (entity.clientBaseUserId != null) {
                        userDao.getBaseUserById(entity.clientBaseUserId)
                    } else {
                        flowOf(null)
                    }

                    val companyFlow = companyDao.getCompanyById(entity.companyId)

                    combine(userFlow, companyFlow) { baseUser, company ->
                        if (company != null) {
                            entity.toDomain(
                                clientBaseUser = baseUser?.toDomain(),
                                company = company.toDomain()
                            )
                        } else {
                            null
                        }
                    }
                }

                if (transformedFlows.isEmpty()) {
                    flowOf(emptyList())
                } else {
                    combine(transformedFlows) { it.filterNotNull() }
                }
            }
    }

    override suspend fun changeClientSalaryProject(
        salaryProjectId: Int,
        clientBaseUserId: Int?
    ) {
        salaryProjectDao.changeClientSalaryProject(
            salaryProjectId = salaryProjectId,
            clientBaseUserId = clientBaseUserId
        )
    }

    override suspend fun changeStatusSalaryProject(
        salaryProjectCompany: SalaryProjectCompany,
        statusJobBid: StatusJobBid
    ) {
        salaryProjectDao.changeStatusSalaryProject(
            salaryProjectId = salaryProjectCompany.id,
            newStatusJobBid = statusJobBid.toString()
        )
    }

    override suspend fun insertSalaryProject(salaryProjectCompany: ISalaryProjectCompany) {
        when(salaryProjectCompany){
            is SalaryProjectCompany -> {
                salaryProjectDao.insertSalaryProject(
                    salaryProjectCompanyEntity = salaryProjectCompany.toEntity(
                        clientBaseUserId = salaryProjectCompany.clientBaseUser?.id,
                        companyId = salaryProjectCompany.company.id
                    )
                )
            }
            else -> {
                throw IllegalArgumentException("Unsupported bank account type: ${salaryProjectCompany::class.java}")
            }
        }
    }

    override suspend fun deleteSalaryProject(salaryProjectCompany: ISalaryProjectCompany) {
        when(salaryProjectCompany){
            is SalaryProjectCompany -> {
                salaryProjectDao.deleteSalaryProject(
                    salaryProjectCompanyEntity = salaryProjectCompany.toEntity(
                        clientBaseUserId = salaryProjectCompany.clientBaseUser?.id,
                        companyId = salaryProjectCompany.company.id
                    )
                )
            }
            else -> {
                throw IllegalArgumentException("Unsupported bank account type: ${salaryProjectCompany::class.java}")
            }
        }
    }

    override suspend fun deleteAllSalaryProjects() {
        salaryProjectDao.deleteAllSalaryProjects()
    }
}