package com.example.domain.repository

import com.example.domain.models.company.Company
import kotlinx.coroutines.flow.Flow

interface CompanyRepository {
    fun getAllCompanies(): Flow<List<Company>>
    fun getCompanyById(companyId: Int) : Flow<Company?>
    suspend fun insertCompany(company: Company)
    suspend fun insertListOfCompany(companies: List<Company>)
    suspend fun deleteCompany(company: Company)
}