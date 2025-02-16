package com.example.data.repository

import com.example.data.local.dao.UserDao
import com.example.data.local.entity.user.toDomain
import com.example.data.local.entity.user.toEntity
import com.example.domain.models.user.BaseUser
import com.example.domain.models.user.adminUser.AdminUser
import com.example.domain.models.user.clientUser.ClientUser
import com.example.domain.models.user.companyUser.CompanyUser
import com.example.domain.models.user.managerUser.ManagerUser
import com.example.domain.models.user.operatorUser.OperatorUser
import com.example.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserRepositoryImpl(
    private val userDao: UserDao
) : UserRepository
{
    override fun getBaseUserById(baseUserId: Int): Flow<BaseUser?> {
        return userDao.getBaseUserById(baseUserId = baseUserId).map { baseUserEntity ->
            baseUserEntity?.toDomain()
        }
    }

    override suspend fun insertBaseUser(baseUser: BaseUser) {
        userDao.insertBaseUser(baseUser = baseUser.toEntity())
    }

    override suspend fun deleteBaseUser(baseUser: BaseUser) {
        userDao.deleteBaseUser(baseUser = baseUser.toEntity())
    }

    override fun getAdminUserByBaseUserId(baseUserId: Int): Flow<AdminUser?> {
        TODO("Not yet implemented")
    }

    override fun getAdminUserById(adminUserId: Int): Flow<AdminUser?> {
        TODO("Not yet implemented")
    }

    override suspend fun insertAdminUser(adminUser: AdminUser) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteAdminUser(adminUser: AdminUser) {
        TODO("Not yet implemented")
    }

    override fun getClientUserByBaseUserId(baseUserId: Int): Flow<ClientUser?> {
        TODO("Not yet implemented")
    }

    override fun getClientUserById(clientUserId: Int): Flow<ClientUser?> {
        TODO("Not yet implemented")
    }

    override suspend fun insertClientUser(clientUser: ClientUser) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteClientUser(clientUser: ClientUser) {
        TODO("Not yet implemented")
    }

    override fun getCompanyUserByBaseUserId(baseUserId: Int): Flow<CompanyUser> {
        TODO("Not yet implemented")
    }

    override fun getCompanyUserById(companyUserId: Int): Flow<CompanyUser?> {
        TODO("Not yet implemented")
    }

    override suspend fun insertCompanyUser(companyUser: CompanyUser) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteCompanyUser(companyUser: CompanyUser) {
        TODO("Not yet implemented")
    }

    override fun getManagerUserByBaseUserId(baseUserId: Int): Flow<ManagerUser?> {
        TODO("Not yet implemented")
    }

    override fun getManagerUserById(managerUserId: Int): Flow<ManagerUser?> {
        TODO("Not yet implemented")
    }

    override suspend fun insertManagerUser(managerUser: ManagerUser) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteManagerUser(managerUser: ManagerUser) {
        TODO("Not yet implemented")
    }

    override fun getOperatorUserByBaseUserId(baseUserId: Int): Flow<OperatorUser?> {
        TODO("Not yet implemented")
    }

    override fun getOperatorUserById(operatorUser: Int): Flow<OperatorUser?> {
        TODO("Not yet implemented")
    }

    override suspend fun insertOperatorUser(operatorUser: OperatorUser) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteOperatorUser(operatorUser: OperatorUser) {
        TODO("Not yet implemented")
    }
}