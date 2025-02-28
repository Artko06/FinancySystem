package com.example.financysystem.presentation.screens.userScreen.event

import com.example.domain.models.salaryProject.SalaryProjectCompany
import com.example.domain.models.salaryProject.StatusJobBid
import com.example.financysystem.presentation.screens.userScreen.state.contentState.OperatorSelectedContent

sealed class OperatorUserEvent {
    data class onContentWindowChange(val newContentWindow: OperatorSelectedContent): OperatorUserEvent()
    data class OnCancelTransfer(val transferId: Int): OperatorUserEvent()
    data class OnChangeStatusSalaryProject(
        val salaryProject: SalaryProjectCompany,
        val newStatusJobBid: StatusJobBid
    ): OperatorUserEvent()
}