package com.example.domain.repository

import com.example.domain.models.user.BaseUser
import com.example.domain.models.user.adminUser.AdminUser
import com.example.domain.models.user.clientUser.ClientUser
import com.example.domain.models.user.companyUser.CompanyUser
import com.example.domain.models.user.managerUser.ManagerUser
import com.example.domain.models.user.operatorUser.OperatorUser
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    // BaseUser
    fun getBaseUserById(baseUserId: Int): Flow<BaseUser?>
    suspend fun insertBaseUser(baseUser: BaseUser)
    suspend fun deleteBaseUser(baseUser: BaseUser)

    // AdminUser
    fun getAdminUserByBaseUserId(baseUserId: Int): Flow<AdminUser?>
    fun getAdminUserById(adminUserId: Int): Flow<AdminUser?>
    suspend fun insertAdminUser(adminUser: AdminUser)
    suspend fun deleteAdminUser(adminUser: AdminUser)

    // ClientUser
    fun getClientUserByBaseUserId(baseUserId: Int) : Flow<ClientUser?>
    fun getClientUserById(clientUserId: Int): Flow<ClientUser?>
    suspend fun insertClientUser(clientUser: ClientUser)
    suspend fun deleteClientUser(clientUser: ClientUser)

    // CompanyUser
    fun getCompanyUserByBaseUserId(baseUserId: Int): Flow<CompanyUser?>
    fun getCompanyUserById(companyUserId: Int): Flow<CompanyUser?>
    suspend fun insertCompanyUser(companyUser: CompanyUser)
    suspend fun deleteCompanyUser(companyUser: CompanyUser)

    // ManagerUser
    fun getManagerUserByBaseUserId(baseUserId: Int): Flow<ManagerUser?>
    fun getManagerUserById(managerUserId: Int): Flow<ManagerUser?>
    suspend fun insertManagerUser(managerUser: ManagerUser)
    suspend fun deleteManagerUser(managerUser: ManagerUser)

    // OperatorUser
    fun getOperatorUserByBaseUserId(baseUserId: Int): Flow<OperatorUser?>
    fun getOperatorUserById(operatorUserId: Int): Flow<OperatorUser?>
    suspend fun insertOperatorUser(operatorUser: OperatorUser)
    suspend fun deleteOperatorUser(operatorUser: OperatorUser)
}