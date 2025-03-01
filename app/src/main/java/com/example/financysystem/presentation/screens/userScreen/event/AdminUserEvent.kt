package com.example.financysystem.presentation.screens.userScreen.event

import com.example.financysystem.presentation.screens.userScreen.state.contentState.AdminSelectedContent

sealed class AdminUserEvent {
    data class onContentWindowChange(val newContentWindow: AdminSelectedContent): AdminUserEvent()
    object OnDeleteAllBankAccounts: AdminUserEvent()
    object OnDeleteAllSalaryProjects: AdminUserEvent()
}