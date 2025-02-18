package com.example.domain.repository

import com.example.domain.models.bank.bankAccount.BaseBankAccount
import com.example.domain.models.bank.bankAccount.StatusBankAccount
import com.example.domain.models.bank.bankAccount.companyBankAccount.ICompanyBankAccount
import com.example.domain.models.bank.bankAccount.creditBankAccount.CreditBankAccount
import com.example.domain.models.bank.bankAccount.creditBankAccount.ICreditBankAccount
import com.example.domain.models.bank.bankAccount.creditBankAccount.StatusCreditBid
import com.example.domain.models.bank.bankAccount.standartBankAccount.IStandardBankAccount
import kotlinx.coroutines.flow.Flow

interface BankAccountRepository {
    // BaseBankAccount
    fun getAllBaseBankAccounts(): Flow<List<BaseBankAccount>>
    fun getBaseBankAccountById(baseBankAccountId: Int): Flow<BaseBankAccount?>
    fun getBaseBankAccountsByBaseUserId(baseUserId: Int): Flow<List<BaseBankAccount>>
    suspend fun changeStatusBaseBankAccount(bankAccount: BaseBankAccount, statusBankAccount: StatusBankAccount)
    suspend fun changeBalanceBaseBankAccount(bankAccount: BaseBankAccount, balance: Double)
    suspend fun insertBaseBankAccount(bankAccount: BaseBankAccount)
    suspend fun deleteBaseBankAccount(bankAccount: BaseBankAccount)

    // StandardBankAccount
    fun getAllStandardBankAccounts(): Flow<List<IStandardBankAccount>>
    fun getStandardBankAccountByBaseBankAccountId(baseBankAccountId: Int): Flow<IStandardBankAccount?>
    fun getStandardBankAccountByBaseUserId(baseUserId: Int) : Flow<List<IStandardBankAccount>>
    suspend fun insertStandardBankAccount(bankAccount: IStandardBankAccount)
    suspend fun deleteStandardBankAccount(bankAccount: IStandardBankAccount)

    // CreditBankAccount
    fun getAllCreditBankAccounts(): Flow<List<ICreditBankAccount>>
    fun getCreditBankAccountByBaseBankAccountId(baseBankAccountId: Int): Flow<ICreditBankAccount?>
    fun getCreditBankAccountByBaseUserId(baseUserId: Int) : Flow<List<ICreditBankAccount>>
    suspend fun changeStatusCreditBankAccount(bankAccount: CreditBankAccount, statusCreditBid: StatusCreditBid)
    suspend fun insertCreditBankAccount(bankAccount: ICreditBankAccount)
    suspend fun deleteCreditBankAccount(bankAccount: ICreditBankAccount)

    // CompanyBankAccount
    fun getAllCompanyBankAccounts(): Flow<List<ICompanyBankAccount>>
    fun getCompanyBankAccountsByCompanyId(companyId: Int): Flow<List<ICompanyBankAccount>>
    suspend fun insertCompanyBankAccount(bankAccount: ICompanyBankAccount)
    suspend fun deleteCompanyBankAccount(bankAccount: ICompanyBankAccount)
}