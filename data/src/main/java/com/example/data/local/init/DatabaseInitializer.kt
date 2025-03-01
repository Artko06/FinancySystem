package com.example.data.local.init

import android.app.Application
import android.util.Log
import com.example.data.local.dao.BankAccountDao
import com.example.data.local.dao.BankDao
import com.example.data.local.dao.CompanyDao
import com.example.data.local.dao.UserDao
import com.example.data.local.database.FinancialDataBase
import com.example.data.local.entity.bank.BankEntity
import com.example.data.local.entity.bank.bankAccount.BaseBankAccountEntity
import com.example.data.local.entity.bank.bankAccount.companyBankAccount.CompanyBankAccountEntity
import com.example.data.local.entity.company.CompanyEntity
import com.example.data.local.entity.user.CertificateUserEntity
import com.example.data.local.entity.user.adminUser.AdminUserEntity
import com.example.data.local.entity.user.clientUser.ClientUserEntity
import com.example.data.local.entity.user.companyUser.CompanyUserEntity
import com.example.data.local.entity.user.managerUser.ManagerUserEntity
import com.example.data.local.entity.user.operatorUser.OperatorUserEntity
import com.example.domain.models.bank.bankAccount.StatusBankAccount
import com.example.domain.models.user.TypeOfUser
import com.example.domain.util.PasswordHasher
import jakarta.inject.Inject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import java.io.File

class DatabaseInitializer @Inject constructor(
    private val userDao: UserDao,
    private val bankDao: BankDao,
    private val bankAccountDao: BankAccountDao,
    private val companyDao: CompanyDao,
) {
    companion object {
        const val COUNT_BASE_USERS: Int = 50
        const val COUNT_CLIENT_USERS: Int = 40
        const val COUNT_COMPANY_USERS: Int = 3
        const val COUNT_MANAGER_USERS: Int = 3
        const val COUNT_OPERATOR_USERS: Int = 3
        const val COUNT_ADMIN_USERS: Int = 1
        const val NAME_FILE = "GenerateUsers.txt"
    }

    val initialBanks = listOf<BankEntity>(
        BankEntity(
            id = 1,
            name = "Банк ВТБ",
            bic = "SLANBY22",
            interestRate = 7.5
        ),
        BankEntity(
            id = 2,
            name = "Альфа-Банк",
            bic = "ALFABY2X",
            interestRate = 8.0
        ),
        BankEntity(
            id = 3,
            name = "АСБ Беларусбанк",
            bic = "AKBBBY2X",
            interestRate = 14.0
        ),
        BankEntity(
            id = 4,
            name = "Гойдабанк",
            bic = "GOIDA",
            interestRate = 3.0
        )
    )

    val initialCompany = listOf<CompanyEntity>(
        CompanyEntity(
            id = 1,
            type = "ОАО",
            name = "Мозырский НПЗ",
            unp = "400091131",
            address = "Гомельская область 247760"
        ),
        CompanyEntity(
            id = 2,
            type = "ОАО",
            name = "Новополоцкий НПЗ",
            unp = "391485215",
            address = "Витебская область 211440"
        ),
        CompanyEntity(
            id = 3,
            type = "ЗАО",
            name = "АТЛАНТ",
            unp = "100010198",
            address = "Минск 220035"
        )
    )

    val initialBaseUsers = GeneratorValues.generateBaseUsers(COUNT_BASE_USERS)
    val passwords = GeneratorValues.generatePasswords(COUNT_BASE_USERS)

    private fun databaseExist(appContext: Application): Boolean{
        return File(appContext.getDatabasePath(FinancialDataBase.DATABASE_NAME).path).exists()
    }

    private fun saveGenerateValuesToFile(appContext: Application) {
        val file = File(appContext.getExternalFilesDir(null), NAME_FILE)

        if(file.exists()){
            file.delete()
        }

        file.createNewFile()

        repeat(COUNT_BASE_USERS) { index ->
            file.appendText(
                "${index + 1}.\n" +
                        "Email: ${initialBaseUsers[index].email}\n" +
                        "Password: ${passwords[index]}\n" +
                        "Role: ${
                            when {
                                index < COUNT_CLIENT_USERS
                                    -> TypeOfUser.ClientUser.name

                                index in COUNT_CLIENT_USERS until COUNT_CLIENT_USERS + COUNT_COMPANY_USERS
                                    -> TypeOfUser.CompanyUser.name

                                index in COUNT_CLIENT_USERS + COUNT_COMPANY_USERS until
                                        COUNT_CLIENT_USERS + COUNT_COMPANY_USERS + COUNT_MANAGER_USERS
                                    -> TypeOfUser.ManagerUser.name

                                index in COUNT_CLIENT_USERS + COUNT_COMPANY_USERS + COUNT_MANAGER_USERS until
                                        COUNT_CLIENT_USERS + COUNT_COMPANY_USERS + COUNT_MANAGER_USERS
                                        + COUNT_OPERATOR_USERS
                                    -> TypeOfUser.OperatorUser.name

                                else -> TypeOfUser.AdminUser.name
                            }
                        }\n\n"
            )
        }
        file.setReadOnly()
    }

    val initialClientUser = List(COUNT_CLIENT_USERS) { index ->
        ClientUserEntity(
            id = index + 1,
            baseUserId = index + 1
        )
    }

    val initialCompanyUser = List(COUNT_COMPANY_USERS) { index ->
        CompanyUserEntity(
            id = index + 1,
            baseUserId = index + COUNT_CLIENT_USERS + 1,
            companyId = index + 1
        )
    }

    val initialManagerUser = List(COUNT_COMPANY_USERS) { index ->
        ManagerUserEntity(
            id = index + 1,
            baseUserId = index + COUNT_CLIENT_USERS + COUNT_COMPANY_USERS + 1
        )
    }

    val initialOperatorUser = List(COUNT_COMPANY_USERS) { index ->
        OperatorUserEntity(
            id = index + 1,
            baseUserId = index + COUNT_CLIENT_USERS + COUNT_COMPANY_USERS + COUNT_MANAGER_USERS + 1
        )
    }

    val initialAdminUser = List(COUNT_ADMIN_USERS) { index ->
        AdminUserEntity(
            id = index + 1,
            baseUserId = index + COUNT_CLIENT_USERS + COUNT_COMPANY_USERS + COUNT_MANAGER_USERS + COUNT_OPERATOR_USERS + 1
        )
    }

    val initialBaseBankAccount = listOf<BaseBankAccountEntity>(
        BaseBankAccountEntity(
            id = 1,
            bankId = 1,
            baseUserId = 41,
            balance = 1_000_000.0,
            statusBankAccount = StatusBankAccount.NORMAL.toString()
        ),
        BaseBankAccountEntity(
            id = 2,
            bankId = 2,
            baseUserId = 42,
            balance = 1_000_000.0,
            statusBankAccount = StatusBankAccount.NORMAL.toString()
        ),
        BaseBankAccountEntity(
            id = 3,
            bankId = 1,
            baseUserId = 43,
            balance = 1_000_000.0,
            statusBankAccount = StatusBankAccount.NORMAL.toString()
        ),
    )

    val initialCompanyAccount = listOf<CompanyBankAccountEntity>(
        CompanyBankAccountEntity(
            id = 1,
            baseBankAccountId = 1,
            companyId = 1
        ),
        CompanyBankAccountEntity(
            id = 2,
            baseBankAccountId = 2,
            companyId = 2
        ),
        CompanyBankAccountEntity(
            id = 3,
            baseBankAccountId = 3,
            companyId = 3
        ),
    )


    fun fillDatabase(appContext: Application) {
        if (!databaseExist(appContext = appContext)) {
            val hashPasswords = CoroutineScope(Dispatchers.IO).async {
                passwords.map { PasswordHasher.hashPassword(it) }
            }

            CoroutineScope(Dispatchers.IO).launch {

                val hashPasswordsResult = hashPasswords.await()
                val initialCertificateUser = List(COUNT_BASE_USERS) { index ->
                    CertificateUserEntity(
                        id = index + 1,
                        baseUserId = index + 1,
                        hashedPassword = hashPasswordsResult[index]
                    )
                }

                bankDao.insertListOfBank(banks = initialBanks)
                companyDao.insertListOfCompany(companies = initialCompany)
                userDao.insertListOfBaseUser(baseUsers = initialBaseUsers)
                userDao.insertListOfCertificateUser(certificateUsers = initialCertificateUser)
                userDao.insertListOfClientUser(clientUsers = initialClientUser)
                userDao.insertListOfCompanyUser(companyUsers = initialCompanyUser)
                userDao.insertListOfManagerUser(managerUsers = initialManagerUser)
                userDao.insertListOfOperatorUser(operatorUsers = initialOperatorUser)
                userDao.insertListOfAdminUser(adminUsers = initialAdminUser)
                bankAccountDao.insertListOfBaseBankAccount(bankAccounts = initialBaseBankAccount)
                bankAccountDao.insertListOfCompanyBankAccount(bankAccounts = initialCompanyAccount)

                saveGenerateValuesToFile(appContext = appContext)
                Log.d("Init", "Finish init")
            }
        }
    }
}