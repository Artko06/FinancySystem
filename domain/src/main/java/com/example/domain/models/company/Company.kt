package com.example.domain.models.company

import com.example.domain.models.bank.bankAccount.IBankAccount
import com.example.domain.models.salaryProject.SalaryProjectCompany

data class Company(
    override val id: String,
    override val type: String, // ИП, ООО, ЗАО и т.д.
    override val name: String,
    override val unp: String,  // УНП
    override val bic: String,  // БИК банка
    override val address: String,
    override val salaryProjectsCompany: List<SalaryProjectCompany>,
    override val bankAccount: IBankAccount
) : ICompany
