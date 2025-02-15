package com.example.domain.models.user.clientUser

import com.example.domain.models.salaryProject.ISalaryProjectCompany

interface ClientRole {
    val salariesProjectsCompany: List<ISalaryProjectCompany>
}