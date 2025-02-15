package com.example.domain.models.user.operatorUser

import com.example.domain.models.salaryProject.ISalaryProjectCompany
import com.example.domain.models.transfer.ITransfer
import com.example.domain.models.user.BaseUser

data class OperatorUser(
    val baseUser: BaseUser,
    override val transfers: List<ITransfer>,
    override val salaryProjectCompany: List<ISalaryProjectCompany>
) : OperatorRole