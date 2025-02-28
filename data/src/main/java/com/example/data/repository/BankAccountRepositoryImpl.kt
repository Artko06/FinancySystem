package com.example.data.repository

import com.example.data.local.dao.BankAccountDao
import com.example.data.local.dao.BankDao
import com.example.data.local.dao.CompanyDao
import com.example.data.local.dao.UserDao
import com.example.data.local.entity.bank.bankAccount.BaseBankAccountEntity
import com.example.data.local.entity.bank.bankAccount.companyBankAccount.CompanyBankAccountEntity
import com.example.data.local.entity.bank.bankAccount.companyBankAccount.toDomain
import com.example.data.local.entity.bank.bankAccount.companyBankAccount.toEntity
import com.example.data.local.entity.bank.bankAccount.creditBankAccount.CreditBankAccountEntity
import com.example.data.local.entity.bank.bankAccount.creditBankAccount.toDomain
import com.example.data.local.entity.bank.bankAccount.creditBankAccount.toEntity
import com.example.data.local.entity.bank.bankAccount.standardBankAccount.toDomain
import com.example.data.local.entity.bank.bankAccount.standardBankAccount.toEntity
import com.example.data.local.entity.bank.bankAccount.toDomain
import com.example.data.local.entity.bank.bankAccount.toEntity
import com.example.data.local.entity.bank.toDomain
import com.example.data.local.entity.company.toDomain
import com.example.data.local.entity.user.toDomain
import com.example.domain.models.bank.bankAccount.BaseBankAccount
import com.example.domain.models.bank.bankAccount.StatusBankAccount
import com.example.domain.models.bank.bankAccount.companyBankAccount.CompanyBankAccount
import com.example.domain.models.bank.bankAccount.companyBankAccount.ICompanyBankAccount
import com.example.domain.models.bank.bankAccount.creditBankAccount.CreditBankAccount
import com.example.domain.models.bank.bankAccount.creditBankAccount.ICreditBankAccount
import com.example.domain.models.bank.bankAccount.creditBankAccount.StatusCreditBid
import com.example.domain.models.bank.bankAccount.standartBankAccount.IStandardBankAccount
import com.example.domain.models.bank.bankAccount.standartBankAccount.StandardBankAccount
import com.example.domain.repository.BankAccountRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlin.collections.map

@OptIn(ExperimentalCoroutinesApi::class)
class BankAccountRepositoryImpl(
    private val bankAccountDao: BankAccountDao,
    private val bankDao: BankDao,
    private val userDao: UserDao,
    private val companyDao: CompanyDao
) : BankAccountRepository
{
    // BaseBankAccount
    override fun getAllBaseBankAccounts(): Flow<List<BaseBankAccount>> {
        return bankAccountDao.getAllBaseBankAccounts()
            .flatMapMerge { baseBankAccounts ->
                val transformedFlows = baseBankAccounts.map { baseBankAccount ->
                    flowOf(baseBankAccount).mapToBaseBankAccount()
                }
                combine(transformedFlows) { it.filterNotNull() }
            }
    }

    override fun getBaseBankAccountById(baseBankAccountId: Int): Flow<BaseBankAccount?> {
        return bankAccountDao.getBaseBankAccountById(baseBankAccountId).mapToBaseBankAccount()
    }

    override fun getBaseBankAccountsByBaseUserId(baseUserId: Int): Flow<List<BaseBankAccount>> {
        return bankAccountDao.getBaseBankAccountsByBaseUserId(baseUserId)
            .flatMapMerge { baseBankAccounts ->
                val transformedFlows = baseBankAccounts.map { baseBankAccount ->
                    flowOf(baseBankAccount).mapToBaseBankAccount()
                }
                combine(transformedFlows) { it.filterNotNull() }
            }
    }

    override fun getLatestBaseBankAccountByBaseUserId(baseUserId: Int): Flow<BaseBankAccount?> {
        return bankAccountDao.getLatestBaseBankAccountByBaseUserId(baseUserId).mapToBaseBankAccount()
    }

    override suspend fun changeStatusBaseBankAccount(
        bankAccount: BaseBankAccount,
        statusBankAccount: StatusBankAccount
    )
    {
        bankAccountDao.changeStatusBaseBankAccount(
            baseBankAccountId = bankAccount.id,
            newStatusBankAccount = statusBankAccount.toString()
        )
    }

    override suspend fun changeBalanceBaseBankAccount(
        bankAccount: BaseBankAccount,
        balance: Double
    ) {
        bankAccountDao.changeBalanceBaseBankAccount(
            baseBankAccountId = bankAccount.id,
            newBalance = balance
        )
    }

    override suspend fun insertBaseBankAccount(bankAccount: BaseBankAccount) {
        return bankAccountDao.insertBaseBankAccount(bankAccount = bankAccount
            .toEntity(
                baseUserId = bankAccount.baseUser.id,
                bankId = bankAccount.bank.id
                )
        )
    }

    override suspend fun deleteBaseBankAccount(bankAccount: BaseBankAccount) {
        return bankAccountDao.deleteBaseBankAccount(bankAccount = bankAccount
            .toEntity(
                baseUserId = bankAccount.baseUser.id,
                bankId = bankAccount.bank.id
            )
        )
    }

    // StandardBankAccount
    override fun getAllStandardBankAccounts(): Flow<List<IStandardBankAccount>> {
        return bankAccountDao.getAllStandardBankAccounts()
            .flatMapMerge { standardBankAccounts ->
                val transformedFlows = standardBankAccounts.map { standardBankAccount ->
                    bankAccountDao.getBaseBankAccountById(standardBankAccount.baseBankAccountId)
                        .mapToBaseBankAccount()
                        .map { baseBankAccount ->
                            baseBankAccount?.let { standardBankAccount.toDomain(it) }
                        }
                }
                combine(transformedFlows) { it.filterNotNull() }
            }
    }

    override fun getStandardBankAccountByBaseBankAccountId(baseBankAccountId: Int): Flow<IStandardBankAccount?> {
        return bankAccountDao.getStandardBankAccountByBaseBankAccountId(baseBankAccountId)
            .flatMapMerge { standardBankAccountEntity ->
                if (standardBankAccountEntity == null) {
                    flowOf(null)
                } else {
                    bankAccountDao.getBaseBankAccountById(standardBankAccountEntity.baseBankAccountId)
                        .mapToBaseBankAccount()
                        .map { baseBankAccount ->
                            baseBankAccount?.let { standardBankAccountEntity.toDomain(it) }
                        }
                }
            }
    }

    override fun getStandardBankAccountByBaseUserId(baseUserId: Int): Flow<List<IStandardBankAccount>> {
        return getBaseBankAccountsByBaseUserId(baseUserId = baseUserId)
            .flatMapMerge { baseAccounts ->
                val creditAccountFlows = baseAccounts.map { baseAccount ->
                    getStandardBankAccountByBaseBankAccountId(
                        baseBankAccountId = baseAccount.id
                    )
                }
                combine(creditAccountFlows) { it.filterNotNull() }
            }
    }

    override suspend fun insertStandardBankAccount(bankAccount: IStandardBankAccount) {
        when(bankAccount){
            is StandardBankAccount -> {
                return bankAccountDao.insertStandardBankAccount(
                    bankAccount = bankAccount.toEntity(bankAccount.baseBankAccount.id)
                )
            }
            else -> {
                throw IllegalArgumentException("Unsupported bank account type: ${bankAccount::class.java}")
            }
        }
    }

    override suspend fun deleteStandardBankAccount(bankAccount: IStandardBankAccount) {
        when(bankAccount){
            is StandardBankAccount -> {
                return bankAccountDao.deleteStandardBankAccount(
                    bankAccount = bankAccount.toEntity(bankAccount.baseBankAccount.id)
                )
            }
            else -> {
                throw IllegalArgumentException("Unsupported bank account type: ${bankAccount::class.java}")
            }
        }
    }

    // CreditBankAccount
    override fun getAllCreditBankAccounts(): Flow<List<ICreditBankAccount>> {
        return bankAccountDao.getAllCreditBankAccounts()
            .flatMapMerge { creditBankAccounts ->
                val transformedFlows = creditBankAccounts.map { creditBankAccount ->
                    flowOf(creditBankAccount).mapToCreditBankAccount()
                }
                combine(transformedFlows) { it.filterNotNull() }
            }
    }

    override fun getCreditBankAccountByBaseBankAccountId(baseBankAccountId: Int): Flow<ICreditBankAccount?> {
        return bankAccountDao.getCreditBankAccountByBaseBankAccountId(
            baseBankAccountId = baseBankAccountId
        ).mapToCreditBankAccount()
    }

    override fun getCreditBankAccountByBaseUserId(baseUserId: Int): Flow<List<ICreditBankAccount>> {
        return getBaseBankAccountsByBaseUserId(baseUserId = baseUserId)
            .flatMapMerge { baseAccounts ->
                val creditAccountFlows = baseAccounts.map { baseAccount ->
                    getCreditBankAccountByBaseBankAccountId(
                        baseBankAccountId = baseAccount.id
                    )
                }
                combine(creditAccountFlows) { it.filterNotNull() }
            }
    }

    override suspend fun changeStatusCreditBankAccount(
        creditBankAccountId: Int,
        statusCreditBid: StatusCreditBid
    )
    {
        bankAccountDao.changeStatusCreditBankAccount(
            creditBankAccountId = creditBankAccountId,
            newStatusCreditBid = statusCreditBid.toString()
        )
    }

    override suspend fun insertCreditBankAccount(bankAccount: ICreditBankAccount) {
        when(bankAccount){
            is CreditBankAccount -> {
                return bankAccountDao.insertCreditBankAccount(
                    bankAccount = bankAccount.toEntity(bankAccount.baseBankAccount.id)
                )
            }
            else -> {
                throw IllegalArgumentException("Unsupported bank account type: ${bankAccount::class.java}")
            }
        }
    }

    override suspend fun deleteCreditBankAccount(bankAccount: ICreditBankAccount) {
        when(bankAccount){
            is CreditBankAccount -> {
                return bankAccountDao.deleteCreditBankAccount(
                    bankAccount = bankAccount.toEntity(bankAccount.baseBankAccount.id)
                )
            }
            else -> {
                throw IllegalArgumentException("Unsupported bank account type: ${bankAccount::class.java}")
            }
        }
    }

    // CompanyBankAccount
    override fun getAllCompanyBankAccounts(): Flow<List<ICompanyBankAccount>> {
        return bankAccountDao.getAllCompanyBankAccounts()
            .flatMapMerge { companyBankAccounts ->
                val transformedFlows = companyBankAccounts.map { companyBankAccount ->
                    flowOf(companyBankAccount).mapToCompanyBankAccount()
                }
                combine(transformedFlows) { it.filterNotNull() }
            }
    }

    override fun getCompanyBankAccountsByCompanyId(companyId: Int): Flow<List<ICompanyBankAccount>> {
        return bankAccountDao.getCompanyBankAccountsByCompanyId(companyId)
            .flatMapMerge { companyBankAccounts ->
                val transformedFlows = companyBankAccounts.map { companyBankAccount ->
                    flowOf(companyBankAccount).mapToCompanyBankAccount()
                }
                combine(transformedFlows) { it.filterNotNull() }
            }
    }

    override suspend fun insertCompanyBankAccount(bankAccount: ICompanyBankAccount) {
        when(bankAccount){
            is CompanyBankAccount -> {
                return bankAccountDao.insertCompanyBankAccount(
                    bankAccount = bankAccount.toEntity(
                        baseBankAccountId = bankAccount.id,
                        companyId = bankAccount.company.id
                        )
                )
            }
            else -> {
                throw IllegalArgumentException("Unsupported bank account type: ${bankAccount::class.java}")
            }
        }
    }

    override suspend fun deleteCompanyBankAccount(bankAccount: ICompanyBankAccount) {
        when(bankAccount){
            is CompanyBankAccount -> {
                return bankAccountDao.deleteCompanyBankAccount(
                    bankAccount = bankAccount.toEntity(
                        baseBankAccountId = bankAccount.id,
                        companyId = bankAccount.company.id
                    )
                )
            }
            else -> {
                throw IllegalArgumentException("Unsupported bank account type: ${bankAccount::class.java}")
            }
        }
    }

    // Mappers for BankAccounts
    private fun Flow<BaseBankAccountEntity?>.mapToBaseBankAccount(): Flow<BaseBankAccount?> {
        return flatMapMerge { baseBankAccountEntity ->
            if (baseBankAccountEntity == null) {
                flowOf(null)
            } else {
                combine(
                    userDao.getBaseUserById(baseBankAccountEntity.baseUserId),
                    bankDao.getBankById(baseBankAccountEntity.bankId)
                ) { baseUserEntity, bankEntity ->
                    if (baseUserEntity != null && bankEntity != null) {
                        baseBankAccountEntity.toDomain(
                            baseUser = baseUserEntity.toDomain(),
                            bank = bankEntity.toDomain()
                        )
                    } else {
                        null
                    }
                }
            }
        }
    }

    private fun Flow<CreditBankAccountEntity?>.mapToCreditBankAccount(): Flow<ICreditBankAccount?> {
        return flatMapMerge { creditBankAccountEntity ->
            if (creditBankAccountEntity == null) {
                flowOf(null)
            } else {
                bankAccountDao.getBaseBankAccountById(creditBankAccountEntity.baseBankAccountId)
                    .mapToBaseBankAccount()
                    .map { baseBankAccount ->
                        baseBankAccount?.let { creditBankAccountEntity.toDomain(it) }
                    }
            }
        }
    }

    private fun Flow<CompanyBankAccountEntity?>.mapToCompanyBankAccount(): Flow<CompanyBankAccount?> {
        return flatMapMerge { companyBankAccountEntity ->
            if (companyBankAccountEntity == null) {
                flowOf(null)
            } else {
                combine(
                    bankAccountDao.getBaseBankAccountById(
                        companyBankAccountEntity.baseBankAccountId
                    ).mapToBaseBankAccount(),
                    companyDao.getCompanyById(companyBankAccountEntity.companyId)
                ) { baseBankAccount, company ->
                    if (baseBankAccount != null && company != null) {
                        companyBankAccountEntity.toDomain(
                            baseBankAccount = baseBankAccount,
                            company = company.toDomain()
                        )
                    } else {
                        null
                    }
                }
            }
        }
    }
}