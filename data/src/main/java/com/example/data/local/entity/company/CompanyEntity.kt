package com.example.data.local.entity.company

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.domain.models.company.Company

@Entity(tableName = "companies")
data class CompanyEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val type: String, // ИП, ООО, ЗАО и т.д.
    val name: String,
    val unp: String, // УНП
    val bic: String, // БИК банка
    val address: String
)

fun CompanyEntity.toDomain() = Company(
    id = id,
    type = type,
    name = name,
    unp = unp,
    bic = bic,
    address = address
)

fun Company.toEntity() = CompanyEntity(
    id = id,
    type = type,
    name = name,
    unp = unp,
    bic = bic,
    address = address
)