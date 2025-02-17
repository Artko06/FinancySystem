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

    override fun getSalaryProjectsByClientUserId(clientUserId: Int): Flow<List<ISalaryProjectCompany>> {
        return salaryProjectDao.getSalaryProjectsByClientUserId(clientUserId)
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

    override suspend fun insertSalaryProject(salaryProjectCompanyEntity: ISalaryProjectCompany) {
        when(salaryProjectCompanyEntity){
            is SalaryProjectCompany -> {
                return salaryProjectDao.insertSalaryProject(
                    salaryProjectCompanyEntity = salaryProjectCompanyEntity.toEntity(
                        clientBaseUserId = salaryProjectCompanyEntity.clientBaseUser.id,
                        companyId = salaryProjectCompanyEntity.company.id
                    )
                )
            }
            else -> {
                throw IllegalArgumentException("Unsupported bank account type: ${salaryProjectCompanyEntity::class.java}")
            }
        }
    }

    override suspend fun deleteSalaryProject(salaryProjectCompanyEntity: ISalaryProjectCompany) {
        when(salaryProjectCompanyEntity){
            is SalaryProjectCompany -> {
                return salaryProjectDao.deleteSalaryProject(
                    salaryProjectCompanyEntity = salaryProjectCompanyEntity.toEntity(
                        clientBaseUserId = salaryProjectCompanyEntity.clientBaseUser.id,
                        companyId = salaryProjectCompanyEntity.company.id
                    )
                )
            }
            else -> {
                throw IllegalArgumentException("Unsupported bank account type: ${salaryProjectCompanyEntity::class.java}")
            }
        }
    }
}