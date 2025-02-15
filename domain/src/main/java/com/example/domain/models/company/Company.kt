package com.example.domain.models.company

import com.example.domain.models.bank.bankAccount.BankAccount
import com.example.domain.models.salaryProject.SalaryProjectCompany

data class Company(
     val id: Int,
     val type: String, // ИП, ООО, ЗАО и т.д.
     val name: String,
     val unp: String,  // УНП
     val bic: String,  // БИК банка
     val address: String,
     val salaryProjectsCompany: List<SalaryProjectCompany>,
     val bankAccount: BankAccount
)
