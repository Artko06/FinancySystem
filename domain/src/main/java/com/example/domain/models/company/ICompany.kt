package com.example.domain.models.company

import com.example.domain.models.bank.bankAccount.IBankAccount
import com.example.domain.models.salaryProject.SalaryProjectCompany

interface ICompany {
    val id: String
    val type: String  // ИП, ООО, ЗАО и т.д.
    val name: String
    val unp: String   // УНП
    val bic: String   // БИК банка
    val address: String
    val salaryProjectsCompany: List<SalaryProjectCompany>
    val bankAccount: IBankAccount
}