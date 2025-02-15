package com.example.domain.models.user.operatorUser

import com.example.domain.models.salaryProject.ISalaryProjectCompany
import com.example.domain.models.transfer.ITransfer

interface OperatorRole {
    val transfers: List<ITransfer>
    val salaryProjectsCompany: List<ISalaryProjectCompany>
}