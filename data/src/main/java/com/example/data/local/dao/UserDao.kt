package com.example.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.example.data.local.entity.user.BaseUserEntity
import com.example.data.local.entity.user.adminUser.AdminUserEntity
import com.example.data.local.entity.user.clientUser.ClientUserEntity
import com.example.data.local.entity.user.companyUser.CompanyUserEntity
import com.example.data.local.entity.user.managerUser.ManagerUserEntity
import com.example.data.local.entity.user.operatorUser.OperatorUserEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    // BaseUserEntity
    @Query("SELECT * FROM base_users WHERE id = :baseUserId")
    fun getBaseUserById(baseUserId: Int): Flow<BaseUserEntity?>

    @Upsert
    suspend fun insertBaseUser(baseUser: BaseUserEntity)

    @Delete
    suspend fun deleteBaseUser(baseUser: BaseUserEntity)

    // AdminUserEntity
    @Query("SELECT * FROM admin_users WHERE baseUserId = :baseUserId")
    fun getAdminUserByBaseUserId(baseUserId: Int): Flow<AdminUserEntity?>

    @Query("SELECT * FROM admin_users WHERE id = :adminUserId")
    fun getAdminUserById(adminUserId: Int): Flow<AdminUserEntity?>

    @Upsert
    suspend fun insertAdminUser(adminUser: AdminUserEntity)

    @Delete
    suspend fun deleteAdminUser(adminUser: AdminUserEntity)

    // ClientUserEntity
    @Query("SELECT * FROM client_users WHERE baseUserId = :baseUserId")
    fun getClientUserByBaseUserId(baseUserId: Int): Flow<ClientUserEntity?>

    @Query("SELECT * FROM client_users WHERE id = :clientUserId")
    fun getClientUserById(clientUserId: Int): Flow<ClientUserEntity?>

    @Upsert
    suspend fun insertClientUser(clientUser: ClientUserEntity)

    @Delete
    suspend fun deleteClientUser(clientUser: ClientUserEntity)

    // CompanyUserEntity
    @Query("SELECT * FROM company_users WHERE baseUserId = :baseUserId")
    fun getCompanyUserByBaseUserId(baseUserId: Int): Flow<CompanyUserEntity?>

    @Query("SELECT * FROM company_users WHERE id = :companyUserId")
    fun getCompanyUserById(companyUserId: Int): Flow<CompanyUserEntity?>

    @Upsert
    suspend fun insertCompanyUser(companyUser: CompanyUserEntity)

    @Delete
    suspend fun deleteCompanyUser(companyUser: CompanyUserEntity)

    // ManagerUserEntity
    @Query("SELECT * FROM manager_users WHERE baseUserId = :baseUserId")
    fun getManagerUserByBaseUserId(baseUserId: Int): Flow<ManagerUserEntity?>

    @Query("SELECT * FROM manager_users WHERE id = :managerUserId")
    fun getManagerUserById(managerUserId: Int): Flow<ManagerUserEntity?>

    @Upsert
    suspend fun insertManagerUser(managerUser: ManagerUserEntity)

    @Delete
    suspend fun deleteManagerUser(managerUser: ManagerUserEntity)

    // OperatorUserEntity
    @Query("SELECT * FROM operator_users WHERE baseUserId = :baseUserId")
    fun getOperatorUserByBaseUserId(baseUserId: Int): Flow<OperatorUserEntity?>

    @Query("SELECT * FROM operator_users WHERE id = :operatorUser")
    fun getOperatorUserById(operatorUser: Int): Flow<OperatorUserEntity?>

    @Upsert
    suspend fun insertOperatorUser(operatorUser: OperatorUserEntity)

    @Delete
    suspend fun deleteOperatorUser(operatorUser: OperatorUserEntity)
}