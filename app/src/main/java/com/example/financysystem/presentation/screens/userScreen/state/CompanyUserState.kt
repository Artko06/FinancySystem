package com.example.financysystem.presentation.screens.userScreen.state

import com.example.domain.models.bank.bankAccount.companyBankAccount.ICompanyBankAccount
import com.example.domain.models.company.Company
import com.example.domain.models.salaryProject.ISalaryProjectCompany
import com.example.domain.models.user.TypeOfUser
import com.example.financysystem.presentation.screens.userScreen.state.contentState.CompanySelectedContent

data class CompanyUserState(
    val companySelectedContent: CompanySelectedContent = CompanySelectedContent.PROFILE,
    val salaryProjects: List<ISalaryProjectCompany> = emptyList<ISalaryProjectCompany>(),
    val companyBankAccounts: List<ICompanyBankAccount> = emptyList<ICompanyBankAccount>(),
    val company: Company = Company(id = 0, type = "", name = "", unp = "", address = ""),

    val sumSalaryProject: String = "",
    val infoSalaryProject: String = "",
    val errorInputSalaryProject: String? = null,
    val isOpenDialogAddingSalaryProject: Boolean = false,

    val isOpenDialogBankAccount: Boolean = false,
    val idOpenDialogBankAccount: Int = 0,

    val errorCreateTransfer: String? = null,
    val isOpenTransferDialog: Boolean = false,
    val idOpenTransferDialog: Int = 0,
    val inputTransferSum: String = "",
    val inputFromCardId: String = "",
    val inputToCardId: String = "",

    override val id: Int = 0,
    override val email: String = "",
    override val phone: String = "",
    override val firstName: String = "",
    override val lastName: String = "",
    override val surName: String = "",
    override val typeOfUser: TypeOfUser = TypeOfUser.CompanyUser
): GeneralInfoUsers