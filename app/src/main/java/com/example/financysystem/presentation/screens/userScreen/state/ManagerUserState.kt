package com.example.financysystem.presentation.screens.userScreen.state

import com.example.domain.models.bank.bankAccount.companyBankAccount.ICompanyBankAccount
import com.example.domain.models.bank.bankAccount.creditBankAccount.ICreditBankAccount
import com.example.domain.models.bank.bankAccount.standartBankAccount.IStandardBankAccount
import com.example.domain.models.salaryProject.ISalaryProjectCompany
import com.example.domain.models.transfer.ITransfer
import com.example.domain.models.user.TypeOfUser
import com.example.financysystem.presentation.screens.userScreen.state.contentState.ManagerSelectedContent

data class ManagerUserState(
    val managerSelectedContent: ManagerSelectedContent = ManagerSelectedContent.PROFILE,
    val transfers: List<ITransfer> = emptyList<ITransfer>(),
    val salaryProjects: List<ISalaryProjectCompany> = emptyList<ISalaryProjectCompany>(),
    val standardBankAccounts: List<IStandardBankAccount> = emptyList<IStandardBankAccount>(),
    val creditBankAccounts: List<ICreditBankAccount> = emptyList<ICreditBankAccount>(),
    val companyBankAccounts: List<ICompanyBankAccount> = emptyList<ICompanyBankAccount>(),

    val isOpenDialogBankAccount: Boolean = false,
    val idOpenDialogBankAccount: Int = 0,

    override val id: Int = 0,
    override val email: String = "",
    override val phone: String = "",
    override val firstName: String = "",
    override val lastName: String = "",
    override val surName: String = "",
    override val typeOfUser: TypeOfUser = TypeOfUser.ManagerUser
): GeneralInfoUsers