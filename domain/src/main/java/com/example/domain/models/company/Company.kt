package com.example.domain.models.company

data class Company(
     val id: Int,
     val type: String, // ИП, ООО, ЗАО и т.д.
     val name: String,
     val unp: String,  // УНП
     val bic: String,  // БИК банка
     val address: String
)
