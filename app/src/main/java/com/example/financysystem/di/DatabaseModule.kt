package com.example.financysystem.di

import android.app.Application
import androidx.room.Room
import com.example.data.local.dao.ActionLogDao
import com.example.data.local.dao.BankAccountDao
import com.example.data.local.dao.BankDao
import com.example.data.local.dao.CompanyDao
import com.example.data.local.dao.SalaryProjectDao
import com.example.data.local.dao.TransferDao
import com.example.data.local.dao.UserDao
import com.example.data.local.database.FinancialDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideFinancialDatabase(app: Application) : FinancialDataBase {
        return Room.databaseBuilder(
            context = app,
            klass = FinancialDataBase::class.java,
            name = FinancialDataBase.DATABASE_NAME
        ).build()
    }

    @Provides
    fun provideActionLogDao(dataBase: FinancialDataBase): ActionLogDao {
        return dataBase.actionLogDao
    }

    @Provides
    fun provideBankDao(dataBase: FinancialDataBase): BankDao {
        return dataBase.bankDao
    }

    @Provides
    fun provideBankAccountDao(dataBase: FinancialDataBase): BankAccountDao {
        return dataBase.bankAccountDao
    }

    @Provides
    fun provideCompanyDao(dataBase: FinancialDataBase): CompanyDao {
        return dataBase.companyDao
    }

    @Provides
    fun provideTransferDao(dataBase: FinancialDataBase): TransferDao {
        return dataBase.transferDao
    }

    @Provides
    fun provideSalaryProjectDao(dataBase: FinancialDataBase): SalaryProjectDao {
        return dataBase.salaryProjectDao
    }

    @Provides
    fun provideUserDao(dataBase: FinancialDataBase): UserDao {
        return dataBase.userDao
    }
}