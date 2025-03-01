package com.example.domain.useCase.roles

import com.example.domain.useCase.allUserCases.UserUseCases.get.GetBaseUserUseCase
import com.example.domain.useCase.allUserCases.actionLogUseCases.get.GetAllActionLogsUseCase
import com.example.domain.useCase.allUserCases.actionLogUseCases.insert.InsertActionLogUseCase
import com.example.domain.useCase.allUserCases.bankUseCases.delete.DeleteAllBankAccountUseCase
import com.example.domain.useCase.allUserCases.salaryProjectUseCases.delete.DeleteAllSalaryProjectUseCase

interface AdminUserRole {
    // GET
    val getAllActionLogsUseCase: GetAllActionLogsUseCase
    val getBaseUserUseCase: GetBaseUserUseCase

    // INSERT
    val insertActionLogUseCase: InsertActionLogUseCase

    //DELETE
    val deleteAllSalaryProjectUseCase: DeleteAllSalaryProjectUseCase
    val deleteAllBankAccountUseCase: DeleteAllBankAccountUseCase

    // CHANGE
}