package com.example.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.example.data.local.entity.salaryProject.SalaryProjectCompanyEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface SalaryProjectDao {
    @Query("Select * FROM salary_project_companies")
    fun getAllSalaryProjects() : Flow<List<SalaryProjectCompanyEntity>>

    @Query("Select * FROM salary_project_companies WHERE companyId = :companyId")
    fun getSalaryProjectsByCompanyId(companyId: Int) : Flow<List<SalaryProjectCompanyEntity>>

    @Query("Select * FROM salary_project_companies WHERE clientBaseUserId = :clientBaseUserId")
    fun getSalaryProjectsByClientBaseUserId(clientBaseUserId: Int) : Flow<List<SalaryProjectCompanyEntity>>

    @Query("UPDATE salary_project_companies SET status = :newStatusJobBid WHERE id = :salaryProjectId")
    suspend fun changeStatusSalaryProject(salaryProjectId: Int, newStatusJobBid: String)

    @Upsert
    suspend fun insertSalaryProject(salaryProjectCompanyEntity: SalaryProjectCompanyEntity)

    @Delete
    suspend fun deleteSalaryProject(salaryProjectCompanyEntity: SalaryProjectCompanyEntity)
}