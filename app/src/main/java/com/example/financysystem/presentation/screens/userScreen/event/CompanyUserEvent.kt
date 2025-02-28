package com.example.financysystem.presentation.screens.userScreen.event

import com.example.financysystem.presentation.screens.userScreen.state.contentState.CompanySelectedContent

sealed class CompanyUserEvent {
    data class onContentWindowChange(val newContentWindow: CompanySelectedContent): CompanyUserEvent()
    object OnOpenAddingSalaryProject: CompanyUserEvent()
    object OnAddSalaryProject: CompanyUserEvent()

    data class OnChangeSumSalaryProject(val sum: String): CompanyUserEvent()
    data class OnChangeInfoSalaryProject(val info: String): CompanyUserEvent()


    data class OnChangeStatusBankAccount(val cardId: Int) : CompanyUserEvent()
    data class OnShowBankAccountDialog(val cardId: Int): CompanyUserEvent()

    object OnCreateTransfer : CompanyUserEvent()
    data class OnShowTransferDialog(val cardId: Int): CompanyUserEvent()
    data class OnChangeTransferSum(val sum: String): CompanyUserEvent()
    data class OnChangeFromCardId(val cardId: String): CompanyUserEvent()
    data class OnChangeToCardId(val cardId: String): CompanyUserEvent()
}