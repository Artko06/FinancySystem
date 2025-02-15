package com.example.domain.models.company

import com.example.domain.models.bank.bankAccount.companyBankAccount.ICompanyBankAccount
import com.example.domain.models.salaryProject.ISalaryProjectCompany

data class Company(
     val id: Int,
     val type: String, // ИП, ООО, ЗАО и т.д.
     val name: String,
     val unp: String,  // УНП
     val bic: String,  // БИК банка
     val address: String,
     val salaryProjectsCompany: List<ISalaryProjectCompany>,
     val companyBankAccounts: List<ICompanyBankAccount>
)
