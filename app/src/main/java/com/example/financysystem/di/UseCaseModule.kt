package com.example.financysystem.di

import com.example.domain.repository.ActionLogRepository
import com.example.domain.repository.BankAccountRepository
import com.example.domain.repository.BankRepository
import com.example.domain.repository.SalaryProjectRepository
import com.example.domain.repository.TransferRepository
import com.example.domain.repository.UserRepository
import com.example.domain.useCase.UserRoleUseCases.AdminUserUseCases
import com.example.domain.useCase.UserRoleUseCases.ClientUserUseCases
import com.example.domain.useCase.UserRoleUseCases.CompanyUserUseCases
import com.example.domain.useCase.UserRoleUseCases.ManagerUserUseCases
import com.example.domain.useCase.UserRoleUseCases.OperatorUserUseCases
import com.example.domain.useCase.UserRoleUseCases.StartUserUseCases
import com.example.domain.useCase.allUserCases.UserUseCases.get.GetBaseUserUseCase
import com.example.domain.useCase.allUserCases.UserUseCases.get.GetCompanyUserByBaseUserUseCase
import com.example.domain.useCase.allUserCases.UserUseCases.insert.InsertAdminUserUseCase
import com.example.domain.useCase.allUserCases.UserUseCases.insert.InsertClientUserUseCase
import com.example.domain.useCase.allUserCases.UserUseCases.insert.InsertCompanyUserUseCase
import com.example.domain.useCase.allUserCases.UserUseCases.insert.InsertManagerUserUseCase
import com.example.domain.useCase.allUserCases.UserUseCases.insert.InsertOperatorUserUseCase
import com.example.domain.useCase.allUserCases.UserUseCases.other.ValidateEmailUseCase
import com.example.domain.useCase.allUserCases.UserUseCases.other.ValidatePasswordUseCase
import com.example.domain.useCase.allUserCases.actionLogUseCases.get.GetAllActionLogsUseCase
import com.example.domain.useCase.allUserCases.actionLogUseCases.insert.InsertActionLogUseCase
import com.example.domain.useCase.allUserCases.auth_regUseCases.ValidateLoginInputUseCase
import com.example.domain.useCase.allUserCases.auth_regUseCases.ValidateRegisterInputUseCase
import com.example.domain.useCase.allUserCases.bankAccountUseCases.change.ChangeBalanceBankAccount
import com.example.domain.useCase.allUserCases.bankAccountUseCases.change.ChangeStatusBaseBankAccountUseCase
import com.example.domain.useCase.allUserCases.bankAccountUseCases.change.ChangeStatusCreditBankAccountUseCase
import com.example.domain.useCase.allUserCases.bankAccountUseCases.get.GetAllCompanyBankAccountUseCase
import com.example.domain.useCase.allUserCases.bankAccountUseCases.get.GetAllCreditBankAccountUseCase
import com.example.domain.useCase.allUserCases.bankAccountUseCases.get.GetAllStandardBankAccountUseCase
import com.example.domain.useCase.allUserCases.bankAccountUseCases.get.GetBaseBankAccountById
import com.example.domain.useCase.allUserCases.bankAccountUseCases.get.GetCompanyBankAccountsByCompanyUseCase
import com.example.domain.useCase.allUserCases.bankAccountUseCases.get.GetCreditBankAccountByBaseUserUseCase
import com.example.domain.useCase.allUserCases.bankAccountUseCases.get.GetStandardBankAccountsByBaseUserUseCase
import com.example.domain.useCase.allUserCases.bankAccountUseCases.insert.InsertCreditBankAccountUseCase
import com.example.domain.useCase.allUserCases.bankAccountUseCases.insert.InsertStandardBankAccountUseCase
import com.example.domain.useCase.allUserCases.bankUseCases.get.GetAllBanksUseCases
import com.example.domain.useCase.allUserCases.salaryProjectUseCases.change.ChangeClientSalaryProjectUseCase
import com.example.domain.useCase.allUserCases.salaryProjectUseCases.change.ChangeStatusSalaryProjectUseCase
import com.example.domain.useCase.allUserCases.salaryProjectUseCases.get.GetAllSalaryProjectUseCase
import com.example.domain.useCase.allUserCases.salaryProjectUseCases.get.GetSalaryProjectsByClientBaseUserUseCase
import com.example.domain.useCase.allUserCases.salaryProjectUseCases.get.GetSalaryProjectsByCompanyUseCase
import com.example.domain.useCase.allUserCases.salaryProjectUseCases.get.GetSalaryProjectsByStatus
import com.example.domain.useCase.allUserCases.salaryProjectUseCases.insert.InsertSalaryProjectUseCase
import com.example.domain.useCase.allUserCases.transferUseCases.change.ChangeStatusTransferUseCase
import com.example.domain.useCase.allUserCases.transferUseCases.get.GetAllTransfersUseCase
import com.example.domain.useCase.allUserCases.transferUseCases.get.GetTransferById
import com.example.domain.useCase.allUserCases.transferUseCases.other.CreateTransferUseCase
import com.example.domain.useCase.allUserCases.transferUseCases.other.validateTransfer.ValidateTransferUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    @Singleton
    fun provideAdminUserUseCases(
        actionLogRepository: ActionLogRepository,
        userRepository: UserRepository
    ): AdminUserUseCases {
        return AdminUserUseCases(
            // GET
            getAllActionLogsUseCase = GetAllActionLogsUseCase(
                actionLogRepository = actionLogRepository
            ),
            getBaseUserUseCase = GetBaseUserUseCase(
                userRepository = userRepository
            ),

            // INSERT
            insertActionLogUseCase = InsertActionLogUseCase(
                actionLogRepository = actionLogRepository
            )

            // CHANGE
        )
    }

    @Provides
    @Singleton
    fun provideClientUserUseCases(
        actionLogRepository: ActionLogRepository,
        bankAccountRepository: BankAccountRepository,
        bankRepository: BankRepository,
        salaryProjectRepository: SalaryProjectRepository,
        transferRepository: TransferRepository,
        userRepository: UserRepository
    ): ClientUserUseCases {
        return ClientUserUseCases(
            // GET
            getStandardBankAccountsByBaseUserUseCase = GetStandardBankAccountsByBaseUserUseCase(
                bankAccountRepository = bankAccountRepository
            ),
            getCreditBankAccountByBaseUserUseCase = GetCreditBankAccountByBaseUserUseCase(
                bankAccountRepository = bankAccountRepository
            ),
            getSalaryProjectsByClientBaseUserUseCase = GetSalaryProjectsByClientBaseUserUseCase(
                salaryProjectRepository = salaryProjectRepository
            ),
            getAllBanksUseCases = GetAllBanksUseCases(
                bankRepository = bankRepository
            ),
            getBaseUserUseCase = GetBaseUserUseCase(
                userRepository = userRepository
            ),
            getBaseBankAccountById = GetBaseBankAccountById(
                bankAccountRepository = bankAccountRepository
            ),
            getSalaryProjectsByStatus = GetSalaryProjectsByStatus(
                salaryProjectRepository = salaryProjectRepository
            ),


            // INSERT
            insertActionLogUseCase = InsertActionLogUseCase(
                actionLogRepository = actionLogRepository
            ),
            insertCreditBankAccountUseCase = InsertCreditBankAccountUseCase(
                bankAccountRepository = bankAccountRepository
            ),
            insertStandardBankAccountUseCase = InsertStandardBankAccountUseCase(
                bankAccountRepository = bankAccountRepository
            ),


            // CHANGE
            changeBalanceBaseBankAccountUseCase = ChangeStatusBaseBankAccountUseCase(
                bankAccountRepository = bankAccountRepository
            ),
            changeStatusBaseBankAccountUseCase = ChangeStatusBaseBankAccountUseCase(
                bankAccountRepository = bankAccountRepository
            ),
            changeClientSalaryProjectUseCase = ChangeClientSalaryProjectUseCase(
                salaryProjectRepository = salaryProjectRepository
            ),


            // OTHER
            createTransferUseCase = CreateTransferUseCase(
                bankAccountRepository = bankAccountRepository,
                transferRepository = transferRepository
            ),
            validateTransferUseCase = ValidateTransferUseCase(
                bankAccountRepository = bankAccountRepository
            )
        )
    }

    @Provides
    @Singleton
    fun provideCompanyUserUseCases(
        actionLogRepository: ActionLogRepository,
        bankAccountRepository: BankAccountRepository,
        bankRepository: BankRepository,
        salaryProjectRepository: SalaryProjectRepository,
        transferRepository: TransferRepository,
        userRepository: UserRepository
    ): CompanyUserUseCases {
        return CompanyUserUseCases(
            // GET
            getCompanyBankAccountsByCompanyUseCase = GetCompanyBankAccountsByCompanyUseCase(
                bankAccountRepository = bankAccountRepository
            ),
            getAllBanksUseCases = GetAllBanksUseCases(
                bankRepository = bankRepository
            ),
            getSalaryProjectsByCompanyUseCase = GetSalaryProjectsByCompanyUseCase(
                salaryProjectRepository = salaryProjectRepository
            ),
            getBaseUserUseCase = GetBaseUserUseCase(
                userRepository = userRepository
            ),
            getBaseBankAccountById = GetBaseBankAccountById(
                bankAccountRepository = bankAccountRepository
            ),
            getCompanyUserByBaseUserUseCase = GetCompanyUserByBaseUserUseCase(
                userRepository = userRepository
            ),


            // INSERT
            insertActionLogUseCase = InsertActionLogUseCase(
                actionLogRepository = actionLogRepository
            ),
            insertSalaryProjectUseCase = InsertSalaryProjectUseCase(
                salaryProjectRepository = salaryProjectRepository
            ),


            // CHANGE
            changeStatusBaseBankAccountUseCase = ChangeStatusBaseBankAccountUseCase(
                bankAccountRepository = bankAccountRepository
            ),
            changeStatusSalaryProjectUseCase = ChangeStatusSalaryProjectUseCase(
                salaryProjectRepository = salaryProjectRepository
            ),


            // OTHER
            createTransferUseCase = CreateTransferUseCase(
                bankAccountRepository = bankAccountRepository,
                transferRepository = transferRepository
            ),
            validateTransferUseCase = ValidateTransferUseCase(
                bankAccountRepository = bankAccountRepository
            )
        )
    }

    @Provides
    @Singleton
    fun provideManagerUserUseCases(
        bankAccountRepository: BankAccountRepository,
        salaryProjectRepository: SalaryProjectRepository,
        transferRepository: TransferRepository,
        userRepository: UserRepository,
        actionLogRepository: ActionLogRepository
    ): ManagerUserUseCases {
        return ManagerUserUseCases(
            // GET
            getAllTransfersUseCase = GetAllTransfersUseCase(
                transferRepository = transferRepository
            ),
            getAllSalaryProjectUseCase = GetAllSalaryProjectUseCase(
                salaryProjectRepository = salaryProjectRepository
            ),
            getCreditBankAccountByBaseUserUseCase = GetCreditBankAccountByBaseUserUseCase(
                bankAccountRepository = bankAccountRepository
            ),
            getBaseUserUseCase = GetBaseUserUseCase(
                userRepository = userRepository
            ),
            getTransferById = GetTransferById(
                transferRepository = transferRepository
            ),
            getAllStandardBankAccount = GetAllStandardBankAccountUseCase(
                bankAccountRepository = bankAccountRepository
            ),
            getAllCreditBankAccount = GetAllCreditBankAccountUseCase(
                bankAccountRepository = bankAccountRepository
            ),
            getAllCompanyBankAccount = GetAllCompanyBankAccountUseCase(
                bankAccountRepository = bankAccountRepository
            ),
            getBaseBankAccountById = GetBaseBankAccountById(
                bankAccountRepository = bankAccountRepository
            ),



            // INSERT
            insertActionLogUseCase = InsertActionLogUseCase(
                actionLogRepository = actionLogRepository
            ),


            // CHANGE
            changeStatusSalaryProjectUseCase = ChangeStatusSalaryProjectUseCase(
                salaryProjectRepository = salaryProjectRepository
            ),
            changeStatusCreditBankAccountUseCase = ChangeStatusCreditBankAccountUseCase(
                bankAccountRepository = bankAccountRepository
            ),
            changeStatusTransferUseCase = ChangeStatusTransferUseCase(
                transferRepository = transferRepository
            ),
            changeBalanceBankAccount = ChangeBalanceBankAccount(
                bankAccountRepository = bankAccountRepository
            ),
            changeStatusBaseBankAccountUseCase = ChangeStatusBaseBankAccountUseCase(
                bankAccountRepository = bankAccountRepository
            )
        )
    }

    @Provides
    @Singleton
    fun provideOperatorUserUseCases(
        bankAccountRepository: BankAccountRepository,
        salaryProjectRepository: SalaryProjectRepository,
        transferRepository: TransferRepository,
        userRepository: UserRepository,
        actionLogRepository: ActionLogRepository
    ): OperatorUserUseCases {
        return OperatorUserUseCases(
            // GET
            getAllSalaryProjectUseCase = GetAllSalaryProjectUseCase(
                salaryProjectRepository = salaryProjectRepository
            ),
            getCreditBankAccountByBaseUserUseCase = GetCreditBankAccountByBaseUserUseCase(
                bankAccountRepository = bankAccountRepository
            ),
            getAllTransfersUseCase = GetAllTransfersUseCase(
                transferRepository = transferRepository
            ),
            getBaseUserUseCase = GetBaseUserUseCase(
                userRepository = userRepository
            ),
            getTransferById = GetTransferById(
                transferRepository = transferRepository
            ),


            // INSERT
            insertActionLogUseCase = InsertActionLogUseCase(
                actionLogRepository = actionLogRepository
            ),

            // CHANGE
            changeStatusSalaryProjectUseCase = ChangeStatusSalaryProjectUseCase(
                salaryProjectRepository = salaryProjectRepository
            ),
            changeStatusTransferUseCase = ChangeStatusTransferUseCase(
                transferRepository = transferRepository
            ),
            changeBalanceBankAccount = ChangeBalanceBankAccount(
                bankAccountRepository = bankAccountRepository
            )
        )
    }

    @Provides
    @Singleton
    fun provideStartUserUseCases(
        userRepository: UserRepository
    ): StartUserUseCases {
        return StartUserUseCases(
            // GET
            getBaseUserUseCase = GetBaseUserUseCase(
                userRepository = userRepository
            ),


            // INSERT
            insertAdminUserUseCase = InsertAdminUserUseCase(
                userRepository = userRepository
            ),
            insertClientUserUseCase = InsertClientUserUseCase(
                userRepository = userRepository
            ),
            insertCompanyUserUseCase = InsertCompanyUserUseCase(
                userRepository = userRepository
            ),
            insertManagerUserUseCase = InsertManagerUserUseCase(
                userRepository = userRepository
            ),
            insertOperatorUserUseCase = InsertOperatorUserUseCase(
                userRepository = userRepository
            ),


            // CHANGE


            // OTHER
            validateEmailUseCase = ValidateEmailUseCase(
                userRepository = userRepository
            ),
            validatePasswordUseCase = ValidatePasswordUseCase(
                userRepository = userRepository
            ),
            validateLoginInputUseCase = ValidateLoginInputUseCase(),
            validateRegisterInputUseCase = ValidateRegisterInputUseCase()
        )
    }
}