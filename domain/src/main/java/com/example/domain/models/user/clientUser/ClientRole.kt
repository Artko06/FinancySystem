package com.example.domain.models.user.clientUser

import com.example.domain.models.salaryProject.SalaryProjectCompany

interface ClientRole {
    val salaryProjects: List<SalaryProjectCompany>
}