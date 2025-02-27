package com.example.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.example.data.local.entity.bank.bankAccount.BaseBankAccountEntity
import com.example.data.local.entity.bank.bankAccount.companyBankAccount.CompanyBankAccountEntity
import com.example.data.local.entity.bank.bankAccount.creditBankAccount.CreditBankAccountEntity
import com.example.data.local.entity.bank.bankAccount.standardBankAccount.StandardBankAccountEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface BankAccountDao {
    // BaseBankAccountEntity
    @Query("SELECT * FROM base_bank_accounts")
    fun getAllBaseBankAccounts(): Flow<List<BaseBankAccountEntity>>

    @Query("SELECT * FROM base_bank_accounts WHERE id = :baseBankAccountId")
    fun getBaseBankAccountById(baseBankAccountId: Int): Flow<BaseBankAccountEntity?>

    @Query("SELECT * FROM base_bank_accounts WHERE baseUserId = :baseUserId")
    fun getBaseBankAccountsByBaseUserId(baseUserId: Int): Flow<List<BaseBankAccountEntity>>

    @Query("SELECT * FROM base_bank_accounts WHERE baseUserId = :baseUserId ORDER BY id DESC LIMIT 1")
    fun getLatestBaseBankAccountByBaseUserId(baseUserId: Int): Flow<BaseBankAccountEntity?>

    @Query("UPDATE base_bank_accounts SET statusBankAccount = :newStatusBankAccount WHERE id = :baseBankAccountId")
    suspend fun changeStatusBaseBankAccount(baseBankAccountId: Int, newStatusBankAccount: String)

    @Query("UPDATE base_bank_accounts SET balance = :newBalance WHERE id = :baseBankAccountId")
    suspend fun changeBalanceBaseBankAccount(baseBankAccountId: Int, newBalance: Double)

    @Upsert
    suspend fun insertBaseBankAccount(bankAccount: BaseBankAccountEntity)

    @Upsert
    suspend fun insertListOfBaseBankAccount(bankAccounts: List<BaseBankAccountEntity>)

    @Delete
    suspend fun deleteBaseBankAccount(bankAccount: BaseBankAccountEntity)

    // StandardBankAccountEntity
    @Query("SELECT * FROM standard_bank_accounts")
    fun getAllStandardBankAccounts(): Flow<List<StandardBankAccountEntity>>

    @Query("SELECT * FROM standard_bank_accounts WHERE baseBankAccountId = :baseBankAccountId")
    fun getStandardBankAccountByBaseBankAccountId(baseBankAccountId: Int): Flow<StandardBankAccountEntity?>

    @Upsert
    suspend fun insertStandardBankAccount(bankAccount: StandardBankAccountEntity)

    @Delete
    suspend fun deleteStandardBankAccount(bankAccount: StandardBankAccountEntity)

    // CreditBankAccountEntity
    @Query("SELECT * FROM credit_bank_accounts")
    fun getAllCreditBankAccounts(): Flow<List<CreditBankAccountEntity>>

    @Query("SELECT * FROM credit_bank_accounts WHERE baseBankAccountId = :baseBankAccountId")
    fun getCreditBankAccountByBaseBankAccountId(baseBankAccountId: Int): Flow<CreditBankAccountEntity?>

    @Query("UPDATE credit_bank_accounts SET statusCreditBid = :newStatusCreditBid WHERE id = :creditBankAccountId")
    suspend fun changeStatusCreditBankAccount(creditBankAccountId: Int, newStatusCreditBid: String)

    @Upsert
    suspend fun insertCreditBankAccount(bankAccount: CreditBankAccountEntity)

    @Delete
    suspend fun deleteCreditBankAccount(bankAccount: CreditBankAccountEntity)

    // CompanyBankAccountEntity
    @Query("SELECT * FROM company_bank_accounts")
    fun getAllCompanyBankAccounts(): Flow<List<CompanyBankAccountEntity>>

    @Query("SELECT * FROM company_bank_accounts WHERE companyId = :companyId")
    fun getCompanyBankAccountsByCompanyId(companyId: Int): Flow<List<CompanyBankAccountEntity>>

    @Upsert
    suspend fun insertCompanyBankAccount(bankAccount: CompanyBankAccountEntity)

    @Upsert
    suspend fun insertListOfCompanyBankAccount(bankAccounts: List<CompanyBankAccountEntity>)

    @Delete
    suspend fun deleteCompanyBankAccount(bankAccount: CompanyBankAccountEntity)
}
