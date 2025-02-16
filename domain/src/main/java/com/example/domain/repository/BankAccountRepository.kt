package com.example.domain.repository

import com.example.domain.models.bank.bankAccount.BaseBankAccount
import com.example.domain.models.bank.bankAccount.companyBankAccount.ICompanyBankAccount
import com.example.domain.models.bank.bankAccount.creditBankAccount.ICreditBankAccount
import com.example.domain.models.bank.bankAccount.standartBankAccount.IStandardBankAccount
import kotlinx.coroutines.flow.Flow

interface BankAccountRepository {
    // BaseBankAccount
    fun getAllBaseBankAccounts(): Flow<List<BaseBankAccount>>
    fun getBaseBankAccountById(baseBankAccountId: Int): Flow<BaseBankAccount?>
    fun getBaseBankAccountsByBaseUserId(baseUserId: Int): Flow<List<BaseBankAccount>>
    suspend fun insertBaseBankAccount(bankAccount: BaseBankAccount)
    suspend fun deleteBaseBankAccount(bankAccount: BaseBankAccount)

    // StandardBankAccount
    fun getAllStandardBankAccounts(): Flow<List<IStandardBankAccount>>
    fun getStandardBankAccountByBaseBankAccountId(baseBankAccountId: Int): Flow<IStandardBankAccount?>
    suspend fun insertStandardBankAccount(bankAccount: IStandardBankAccount)
    suspend fun deleteStandardBankAccount(bankAccount: IStandardBankAccount)

    // CreditBankAccount
    fun getAllCreditBankAccounts(): Flow<List<ICreditBankAccount>>
    fun getCreditBankAccountByBaseBankAccountId(baseBankAccountId: Int): Flow<ICreditBankAccount?>
    suspend fun insertCreditBankAccount(bankAccount: ICreditBankAccount)
    suspend fun deleteCreditBankAccount(bankAccount: ICreditBankAccount)

    // CompanyBankAccount
    fun getAllCompanyBankAccounts(): Flow<List<ICompanyBankAccount>>
    fun getCompanyBankAccountsByCompanyId(companyId: Int): Flow<List<ICompanyBankAccount>>
    suspend fun insertCompanyBankAccount(bankAccount: ICompanyBankAccount)
    suspend fun deleteCompanyBankAccount(bankAccount: ICompanyBankAccount)
}