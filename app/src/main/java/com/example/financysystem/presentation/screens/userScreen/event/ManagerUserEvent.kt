package com.example.financysystem.presentation.screens.userScreen.event

import com.example.domain.models.bank.bankAccount.creditBankAccount.StatusCreditBid
import com.example.domain.models.salaryProject.SalaryProjectCompany
import com.example.domain.models.salaryProject.StatusJobBid
import com.example.financysystem.presentation.screens.userScreen.state.contentState.ManagerSelectedContent

sealed class ManagerUserEvent {
    data class onContentWindowChange(val newContentWindow: ManagerSelectedContent): ManagerUserEvent()
    data class OnCancelTransfer(val transferId: Int): ManagerUserEvent()
    data class OnChangeStatusSalaryProject(
        val salaryProject: SalaryProjectCompany,
        val newStatusJobBid: StatusJobBid
    ): ManagerUserEvent()
    data class OnChangeStatusBankAccount(val cardId: Int) : ManagerUserEvent()
    data class OnShowBankAccountDialog(val cardId: Int): ManagerUserEvent()
    data class OnChangeStatusCreditBankAccount(
        val creditBankAccountId: Int,
        val statusCreditBid: StatusCreditBid
    ): ManagerUserEvent()
}