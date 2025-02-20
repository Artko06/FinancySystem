package com.example.data.repository

import com.example.data.local.dao.CompanyDao
import com.example.data.local.dao.UserDao
import com.example.data.local.entity.company.toDomain
import com.example.data.local.entity.user.BaseUserEntity
import com.example.data.local.entity.user.adminUser.toDomain
import com.example.data.local.entity.user.adminUser.toEntity
import com.example.data.local.entity.user.clientUser.toDomain
import com.example.data.local.entity.user.clientUser.toEntity
import com.example.data.local.entity.user.companyUser.toDomain
import com.example.data.local.entity.user.companyUser.toEntity
import com.example.data.local.entity.user.managerUser.toDomain
import com.example.data.local.entity.user.managerUser.toEntity
import com.example.data.local.entity.user.operatorUser.toDomain
import com.example.data.local.entity.user.operatorUser.toEntity
import com.example.data.local.entity.user.toDomain
import com.example.data.local.entity.user.toEntity
import com.example.domain.models.user.BaseUser
import com.example.domain.models.user.CertificateUser
import com.example.domain.models.user.adminUser.AdminUser
import com.example.domain.models.user.clientUser.ClientUser
import com.example.domain.models.user.companyUser.CompanyUser
import com.example.domain.models.user.managerUser.ManagerUser
import com.example.domain.models.user.operatorUser.OperatorUser
import com.example.domain.repository.UserRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map

@OptIn(ExperimentalCoroutinesApi::class)
class UserRepositoryImpl(
    private val userDao: UserDao,
    private val companyDao: CompanyDao
) : UserRepository
{
    // BaseUser
    override fun getBaseUserById(baseUserId: Int): Flow<BaseUser?> {
        return userDao.getBaseUserById(baseUserId = baseUserId).map { baseUserEntity ->
            baseUserEntity?.toDomain()
        }
    }

    override fun getBaseUserByEmail(email: String): Flow<BaseUser?> {
        return userDao.getBaseUserByEmail(email = email).map { baseUserEntity ->
            baseUserEntity?.toDomain()
        }
    }

    override suspend fun insertBaseUser(baseUser: BaseUser) {
        userDao.insertBaseUser(baseUser = baseUser.toEntity())
    }

    override suspend fun deleteBaseUser(baseUser: BaseUser) {
        userDao.deleteBaseUser(baseUser = baseUser.toEntity())
    }

    // CertificateUser
    override fun getCertificateUserByBaseUserId(baseUserId: Int): Flow<CertificateUser?> {
        return userDao.getCertificateUserByBaseUserId(baseUserId = baseUserId)
            .mapToUserDomain(
                getBaseUser = {user -> userDao.getBaseUserById(baseUserId)},
                toDomain = {entity, baseUser -> entity.toDomain(baseUser)}
            )
    }

    override suspend fun insertCertificateUser(certificateUser: CertificateUser) {
        userDao.insertCertificateUser(certificateUser = certificateUser.toEntity(
            baseUserId = certificateUser.baseUser.id
            )
        )
    }

    override suspend fun deleteCertificateUser(certificateUser: CertificateUser) {
        userDao.deleteCertificateUser(certificateUser = certificateUser.toEntity(
            baseUserId = certificateUser.baseUser.id
            )
        )
    }

    // AdminUser
    override fun getAdminUserByBaseUserId(baseUserId: Int): Flow<AdminUser?> {
        return userDao.getAdminUserByBaseUserId(baseUserId = baseUserId)
            .mapToUserDomain (
                getBaseUser = {user -> userDao.getBaseUserById(baseUserId)},
                toDomain = {entity, baseUser -> entity.toDomain(baseUser) }
            )
    }

    override fun getAdminUserById(adminUserId: Int): Flow<AdminUser?> {
        return userDao.getAdminUserById(adminUserId = adminUserId)
            .mapToUserDomain(
                getBaseUser = {user -> userDao.getBaseUserById(user.baseUserId)},
                toDomain = {entity, baseUser -> entity.toDomain(baseUser) }
            )
    }

    override suspend fun insertAdminUser(adminUser: AdminUser) {
        return userDao.insertAdminUser(adminUser = adminUser.toEntity(
            baseUserId = adminUser.baseUser.id
            )
        )
    }

    override suspend fun deleteAdminUser(adminUser: AdminUser) {
        return userDao.deleteAdminUser(adminUser = adminUser.toEntity(
            baseUserId = adminUser.baseUser.id
        )
        )
    }

    // ClientUser
    override fun getClientUserByBaseUserId(baseUserId: Int): Flow<ClientUser?> {
        return userDao.getClientUserByBaseUserId(baseUserId)
            .mapToUserDomain (
                getBaseUser = {userDao.getBaseUserById(baseUserId)},
                toDomain = {entity, baseUser -> entity.toDomain(baseUser) }
            )
    }

    override fun getClientUserById(clientUserId: Int): Flow<ClientUser?> {
        return userDao.getClientUserById(clientUserId = clientUserId)
            .mapToUserDomain(
                getBaseUser = {user -> userDao.getBaseUserById(user.baseUserId)},
                toDomain = {entity, baseUser -> entity.toDomain(baseUser) }
            )
    }

    override suspend fun insertClientUser(clientUser: ClientUser) {
        userDao.insertClientUser(clientUser = clientUser.toEntity(
            baseUserId = clientUser.baseUser.id
            )
        )
    }

    override suspend fun deleteClientUser(clientUser: ClientUser) {
        userDao.deleteClientUser(clientUser = clientUser.toEntity(
            baseUserId = clientUser.baseUser.id
            )
        )
    }

    // CompanyUser
    override fun getCompanyUserByBaseUserId(baseUserId: Int): Flow<CompanyUser?> {
        return userDao.getCompanyUserByBaseUserId(baseUserId)
            .flatMapMerge { companyUserEntity ->
                if (companyUserEntity == null) {
                    flowOf(null)
                } else {
                    combine(
                        userDao.getBaseUserById(companyUserEntity.baseUserId),
                        companyDao.getCompanyById(companyUserEntity.companyId)
                    ) { baseUserEntity, companyEntity ->
                        if (baseUserEntity != null && companyEntity != null) {
                            companyUserEntity.toDomain(
                                baseUser = baseUserEntity.toDomain(),
                                company = companyEntity.toDomain()
                            )
                        } else {
                            null
                        }
                    }
                }
            }
    }

    override fun getCompanyUserById(companyUserId: Int): Flow<CompanyUser?> {
        return userDao.getCompanyUserById(companyUserId)
            .flatMapMerge { companyUserEntity ->
                if (companyUserEntity == null) {
                    flowOf(null)
                } else {
                    combine(
                        userDao.getBaseUserById(companyUserEntity.baseUserId),
                        companyDao.getCompanyById(companyUserEntity.companyId)
                    ) { baseUserEntity, companyEntity ->
                        if (baseUserEntity != null && companyEntity != null) {
                            companyUserEntity.toDomain(
                                baseUser = baseUserEntity.toDomain(),
                                company = companyEntity.toDomain()
                            )
                        } else {
                            null
                        }
                    }
                }
            }
    }

    override suspend fun insertCompanyUser(companyUser: CompanyUser) {
        return userDao.insertCompanyUser(companyUser = companyUser.toEntity(
            baseUserId = companyUser.baseUser.id,
            companyId = companyUser.companyUserId
        )
        )
    }

    override suspend fun deleteCompanyUser(companyUser: CompanyUser) {
        return userDao.deleteCompanyUser(companyUser = companyUser.toEntity(
                baseUserId = companyUser.baseUser.id,
                companyId = companyUser.companyUserId
            )
        )
    }

    // ManagerUser
    override fun getManagerUserByBaseUserId(baseUserId: Int): Flow<ManagerUser?> {
        return userDao.getManagerUserByBaseUserId(baseUserId = baseUserId)
            .mapToUserDomain(
                getBaseUser = {user -> userDao.getBaseUserById(user.baseUserId)},
                toDomain = {entity, baseUser -> entity.toDomain(baseUser) }
            )
    }

    override fun getManagerUserById(managerUserId: Int): Flow<ManagerUser?> {
        return userDao.getManagerUserById(managerUserId = managerUserId)
            .mapToUserDomain(
                getBaseUser = {user -> userDao.getBaseUserById(user.baseUserId)},
                toDomain = {entity, baseUser -> entity.toDomain(baseUser) }
            )
    }

    override suspend fun insertManagerUser(managerUser: ManagerUser) {
        userDao.insertManagerUser(managerUser = managerUser.toEntity(
            baseUserId = managerUser.baseUser.id
        )
        )
    }

    override suspend fun deleteManagerUser(managerUser: ManagerUser) {
        userDao.deleteManagerUser(managerUser = managerUser.toEntity(
                baseUserId = managerUser.baseUser.id
            )
        )
    }

    // OperatorUser
    override fun getOperatorUserByBaseUserId(baseUserId: Int): Flow<OperatorUser?> {
        return userDao.getOperatorUserByBaseUserId(baseUserId = baseUserId)
            .mapToUserDomain(
                getBaseUser = {user -> userDao.getBaseUserById(user.baseUserId)},
                toDomain = {entity, baseUser -> entity.toDomain(baseUser = baseUser) }
            )
    }

    override fun getOperatorUserById(operatorUserId: Int): Flow<OperatorUser?> {
        return userDao.getOperatorUserById(operatorUserId = operatorUserId)
            .mapToUserDomain(
                getBaseUser = {user -> userDao.getBaseUserById(user.baseUserId)},
                toDomain = {entity, baseUser -> entity.toDomain(baseUser = baseUser) }
            )
    }

    override suspend fun insertOperatorUser(operatorUser: OperatorUser) {
        userDao.insertOperatorUser(operatorUser = operatorUser.toEntity(
            baseUserId = operatorUser.baseUser.id
            )
        )
    }

    override suspend fun deleteOperatorUser(operatorUser: OperatorUser) {
        userDao.deleteOperatorUser(operatorUser = operatorUser.toEntity(
            baseUserId = operatorUser.baseUser.id
        )
        )
    }

    // Mappers for User
    private fun <T, R> Flow<T?>.mapToUserDomain(
        getBaseUser: (T) -> Flow<BaseUserEntity?>,
        toDomain: (T, BaseUser) -> R
    ): Flow<R?> {
        return flatMapMerge { entity ->
            if (entity == null) {
                flowOf(null)
            } else {
                getBaseUser(entity).map { baseUserEntity ->
                    baseUserEntity?.let { toDomain(entity, it.toDomain()) }
                }
            }
        }
    }
}