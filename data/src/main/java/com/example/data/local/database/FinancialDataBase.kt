package com.example.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.data.local.dao.ActionLogDao
import com.example.data.local.dao.BankAccountDao
import com.example.data.local.dao.BankDao
import com.example.data.local.dao.CompanyDao
import com.example.data.local.dao.SalaryProjectDao
import com.example.data.local.dao.TransferDao
import com.example.data.local.dao.UserDao
import com.example.data.local.entity.actionLog.ActionLogEntity
import com.example.data.local.entity.bank.BankEntity
import com.example.data.local.entity.bank.bankAccount.BaseBankAccountEntity
import com.example.data.local.entity.bank.bankAccount.companyBankAccount.CompanyBankAccountEntity
import com.example.data.local.entity.bank.bankAccount.creditBankAccount.CreditBankAccountEntity
import com.example.data.local.entity.bank.bankAccount.standardBankAccount.StandardBankAccountEntity
import com.example.data.local.entity.company.CompanyEntity
import com.example.data.local.entity.salaryProject.SalaryProjectCompanyEntity
import com.example.data.local.entity.transfer.TransferEntity
import com.example.data.local.entity.user.BaseUserEntity
import com.example.data.local.entity.user.CertificateUserEntity
import com.example.data.local.entity.user.adminUser.AdminUserEntity
import com.example.data.local.entity.user.clientUser.ClientUserEntity
import com.example.data.local.entity.user.companyUser.CompanyUserEntity
import com.example.data.local.entity.user.managerUser.ManagerUserEntity
import com.example.data.local.entity.user.operatorUser.OperatorUserEntity
import com.example.domain.models.user.CertificateUser

@Database(
    entities = [
        // ActionLog
        ActionLogEntity::class,
        // Bank
        BankEntity::class,
        // BankAccount
        BaseBankAccountEntity::class,
        StandardBankAccountEntity::class,
        CreditBankAccountEntity::class,
        CompanyBankAccountEntity::class,
        // Company
        CompanyEntity::class,
        // SalaryProjectCompany
        SalaryProjectCompanyEntity::class,
        // Transfer
        TransferEntity::class,
        // Users
        BaseUserEntity::class,
        CertificateUserEntity::class,
        AdminUserEntity::class,
        ClientUserEntity::class,
        CompanyUserEntity::class,
        ManagerUserEntity::class,
        OperatorUserEntity::class
               ],
    version = 1,
    exportSchema = false
)
abstract class FinancialDataBase : RoomDatabase() {

    abstract val actionLogDao : ActionLogDao
    abstract val bankAccountDao : BankAccountDao
    abstract val bankDao : BankDao
    abstract val companyDao : CompanyDao
    abstract val salaryProjectDao : SalaryProjectDao
    abstract val transferDao : TransferDao
    abstract val userDao : UserDao

    companion object {
        const val DATABASE_NAME = "FinancialSystem_db"
    }
}