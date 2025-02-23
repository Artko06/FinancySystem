package com.example.data.repository

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
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapMerge

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
                    combine(
                        userDao.getBaseUserById(entity.clientBaseUserId),
                        companyDao.getCompanyById(entity.companyId)
                    ) { baseUser, company ->
                        if (baseUser != null && company != null) {
                            entity.toDomain(
                                clientBaseUser = baseUser.toDomain(),
                                company = company.toDomain()
                            )
                        } else {
                            null
                        }
                    }
                }
                combine(transformedFlows) { it.filterNotNull() }
            }
    }

    override fun getSalaryProjectsByCompanyId(companyId: Int): Flow<List<ISalaryProjectCompany>> {
        return salaryProjectDao.getSalaryProjectsByCompanyId(companyId)
            .flatMapMerge { entities ->
                val transformedFlows = entities.map { entity ->
                    combine(
                        userDao.getBaseUserById(entity.clientBaseUserId),
                        companyDao.getCompanyById(entity.companyId)
                    ) { baseUser, company ->
                        if (baseUser != null && company != null) {
                            entity.toDomain(
                                clientBaseUser = baseUser.toDomain(),
                                company = company.toDomain())
                        } else {
                            null
                        }
                    }
                }
                combine(transformedFlows) { it.filterNotNull() }
            }
    }

    override fun getSalaryProjectsByClientBaseUserId(clientBaseUserId: Int): Flow<List<ISalaryProjectCompany>> {
        return salaryProjectDao.getSalaryProjectsByClientBaseUserId(clientBaseUserId)
            .flatMapMerge { entities ->
                val transformedFlows = entities.map { entity ->
                    combine(
                        userDao.getBaseUserById(entity.clientBaseUserId),
                        companyDao.getCompanyById(entity.companyId)
                    ) { baseUser, company ->
                        if (baseUser != null && company != null) {
                            entity.toDomain(
                                clientBaseUser = baseUser.toDomain(),
                                company = company.toDomain())
                        } else {
                            null
                        }
                    }
                }
                combine(transformedFlows) { it.filterNotNull() }
            }
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
                        clientBaseUserId = salaryProjectCompany.clientBaseUser.id,
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
                        clientBaseUserId = salaryProjectCompany.clientBaseUser.id,
                        companyId = salaryProjectCompany.company.id
                    )
                )
            }
            else -> {
                throw IllegalArgumentException("Unsupported bank account type: ${salaryProjectCompany::class.java}")
            }
        }
    }
}