package com.example.financysystem.di

import com.example.data.local.dao.ActionLogDao
import com.example.data.local.dao.BankAccountDao
import com.example.data.local.dao.BankDao
import com.example.data.local.dao.CompanyDao
import com.example.data.local.dao.SalaryProjectDao
import com.example.data.local.dao.TransferDao
import com.example.data.local.dao.UserDao
import com.example.data.repository.ActionLogRepositoryImpl
import com.example.data.repository.BankAccountRepositoryImpl
import com.example.data.repository.BankRepositoryImpl
import com.example.data.repository.CompanyRepositoryImpl
import com.example.data.repository.SalaryRepositoryImpl
import com.example.data.repository.TransferRepositoryImpl
import com.example.data.repository.UserRepositoryImpl
import com.example.domain.repository.ActionLogRepository
import com.example.domain.repository.BankAccountRepository
import com.example.domain.repository.BankRepository
import com.example.domain.repository.CompanyRepository
import com.example.domain.repository.SalaryProjectRepository
import com.example.domain.repository.TransferRepository
import com.example.domain.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideActionLogRepository(
        actionLogDao: ActionLogDao,
        userDao: UserDao
    ): ActionLogRepository {
        return ActionLogRepositoryImpl(
            actionLogDao = actionLogDao,
            userDao = userDao
        )
    }

    @Provides
    @Singleton
    fun provideBankAccountRepository(
        bankAccountDao: BankAccountDao,
        bankDao: BankDao,
        userDao: UserDao,
        companyDao: CompanyDao
    ): BankAccountRepository {
        return BankAccountRepositoryImpl(
            bankAccountDao = bankAccountDao,
            bankDao = bankDao,
            userDao = userDao,
            companyDao = companyDao
        )
    }

    @Provides
    @Singleton
    fun provideBankRepository(bankDao: BankDao): BankRepository {
        return BankRepositoryImpl(bankDao = bankDao)
    }

    @Provides
    @Singleton
    fun provideCompanyRepository(companyDao: CompanyDao): CompanyRepository {
        return CompanyRepositoryImpl(companyDao = companyDao)
    }

    @Provides
    @Singleton
    fun provideSalaryProjectRepository(
        salaryProjectDao: SalaryProjectDao,
        userDao: UserDao,
        companyDao: CompanyDao
    ): SalaryProjectRepository {
        return SalaryRepositoryImpl(
            salaryProjectDao = salaryProjectDao,
            userDao = userDao,
            companyDao = companyDao
        )
    }

    @Provides
    @Singleton
    fun provideTransferRepository(
        transferDao: TransferDao,
        bankAccountDao: BankAccountDao,
        userDao: UserDao,
        bankDao: BankDao
    ): TransferRepository {
        return TransferRepositoryImpl(
            transferDao = transferDao,
            bankAccountDao = bankAccountDao,
            userDao = userDao,
            bankDao = bankDao
        )
    }

    @Provides
    @Singleton
    fun provideUserRepository(
        userDao: UserDao,
        companyDao: CompanyDao
    ): UserRepository {
        return UserRepositoryImpl(
            userDao = userDao,
            companyDao = companyDao
        )
    }
}