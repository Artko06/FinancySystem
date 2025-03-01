package com.example.domain.useCase.UserRoleUseCases

import com.example.domain.useCase.allUserCases.UserUseCases.get.GetBaseUserUseCase
import com.example.domain.useCase.roles.AdminUserRole
import com.example.domain.useCase.allUserCases.actionLogUseCases.get.GetAllActionLogsUseCase
import com.example.domain.useCase.allUserCases.actionLogUseCases.insert.InsertActionLogUseCase
import com.example.domain.useCase.allUserCases.bankUseCases.delete.DeleteAllBankAccountUseCase
import com.example.domain.useCase.allUserCases.salaryProjectUseCases.delete.DeleteAllSalaryProjectUseCase

data class AdminUserUseCases(
    // GET
    override val getAllActionLogsUseCase: GetAllActionLogsUseCase,
    override val getBaseUserUseCase: GetBaseUserUseCase,

    // INSERT
    override val insertActionLogUseCase: InsertActionLogUseCase,


    // DELETE
    override val deleteAllSalaryProjectUseCase: DeleteAllSalaryProjectUseCase,
    override val deleteAllBankAccountUseCase: DeleteAllBankAccountUseCase


    // CHANGE
): AdminUserRole