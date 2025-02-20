package com.example.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.example.data.local.entity.user.BaseUserEntity
import com.example.data.local.entity.user.CertificateUserEntity
import com.example.data.local.entity.user.adminUser.AdminUserEntity
import com.example.data.local.entity.user.clientUser.ClientUserEntity
import com.example.data.local.entity.user.companyUser.CompanyUserEntity
import com.example.data.local.entity.user.managerUser.ManagerUserEntity
import com.example.data.local.entity.user.operatorUser.OperatorUserEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    // BaseUserEntity
    @Query("SELECT * FROM base_users")
    fun getAllBaseUser(): Flow<List<BaseUserEntity>>

    @Query("SELECT * FROM base_users WHERE id = :baseUserId")
    fun getBaseUserById(baseUserId: Int): Flow<BaseUserEntity?>

    @Query("SELECT * FROM base_users WHERE email = :email")
    fun getBaseUserByEmail(email: String) : Flow<BaseUserEntity?>

    @Upsert
    suspend fun insertBaseUser(baseUser: BaseUserEntity)

    @Delete
    suspend fun deleteBaseUser(baseUser: BaseUserEntity)

    // CertificateUserEntity
    @Query("SELECT * FROM certificate_users WHERE baseUserId = :baseUserId")
    fun getCertificateUserByBaseUserId(baseUserId: Int) : Flow<CertificateUserEntity?>

    @Upsert
    suspend fun insertCertificateUser(certificateUser: CertificateUserEntity)

    @Delete
    suspend fun deleteCertificateUser(certificateUser: CertificateUserEntity)

    // AdminUserEntity
    @Query("SELECT * FROM admin_users")
    fun getAllAdminUser(): Flow<List<AdminUserEntity>>

    @Query("SELECT * FROM admin_users WHERE baseUserId = :baseUserId")
    fun getAdminUserByBaseUserId(baseUserId: Int): Flow<AdminUserEntity?>

    @Query("SELECT * FROM admin_users WHERE id = :adminUserId")
    fun getAdminUserById(adminUserId: Int): Flow<AdminUserEntity?>

    @Upsert
    suspend fun insertAdminUser(adminUser: AdminUserEntity)

    @Delete
    suspend fun deleteAdminUser(adminUser: AdminUserEntity)

    // ClientUserEntity
    @Query("SELECT * FROM client_users")
    fun getAllClientUser(): Flow<List<ClientUserEntity>>

    @Query("SELECT * FROM client_users WHERE baseUserId = :baseUserId")
    fun getClientUserByBaseUserId(baseUserId: Int): Flow<ClientUserEntity?>

    @Query("SELECT * FROM client_users WHERE id = :clientUserId")
    fun getClientUserById(clientUserId: Int): Flow<ClientUserEntity?>

    @Upsert
    suspend fun insertClientUser(clientUser: ClientUserEntity)

    @Delete
    suspend fun deleteClientUser(clientUser: ClientUserEntity)

    // CompanyUserEntity
    @Query("SELECT * FROM company_users")
    fun getAllCompanyUser(): Flow<List<CompanyUserEntity>>

    @Query("SELECT * FROM company_users WHERE baseUserId = :baseUserId")
    fun getCompanyUserByBaseUserId(baseUserId: Int): Flow<CompanyUserEntity?>

    @Query("SELECT * FROM company_users WHERE id = :companyUserId")
    fun getCompanyUserById(companyUserId: Int): Flow<CompanyUserEntity?>

    @Upsert
    suspend fun insertCompanyUser(companyUser: CompanyUserEntity)

    @Delete
    suspend fun deleteCompanyUser(companyUser: CompanyUserEntity)

    // ManagerUserEntity
    @Query("SELECT * FROM manager_users")
    fun getAllManagerUser(): Flow<List<ManagerUserEntity>>

    @Query("SELECT * FROM manager_users WHERE baseUserId = :baseUserId")
    fun getManagerUserByBaseUserId(baseUserId: Int): Flow<ManagerUserEntity?>

    @Query("SELECT * FROM manager_users WHERE id = :managerUserId")
    fun getManagerUserById(managerUserId: Int): Flow<ManagerUserEntity?>

    @Upsert
    suspend fun insertManagerUser(managerUser: ManagerUserEntity)

    @Delete
    suspend fun deleteManagerUser(managerUser: ManagerUserEntity)

    // OperatorUserEntity
    @Query("SELECT * FROM operator_users")
    fun getAllOperatorUser(): Flow<List<OperatorUserEntity>>

    @Query("SELECT * FROM operator_users WHERE baseUserId = :baseUserId")
    fun getOperatorUserByBaseUserId(baseUserId: Int): Flow<OperatorUserEntity?>

    @Query("SELECT * FROM operator_users WHERE id = :operatorUserId")
    fun getOperatorUserById(operatorUserId: Int): Flow<OperatorUserEntity?>

    @Upsert
    suspend fun insertOperatorUser(operatorUser: OperatorUserEntity)

    @Delete
    suspend fun deleteOperatorUser(operatorUser: OperatorUserEntity)
}