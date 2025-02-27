package com.example.financysystem.presentation.screens.userScreen.event

import com.example.financysystem.presentation.screens.userScreen.state.contentState.CompanySelectedContent

sealed class CompanyUserEvent {
    data class onContentWindowChange(val newContentWindow: CompanySelectedContent): CompanyUserEvent()
    object OnOpenAddingSalaryProject: CompanyUserEvent()
    object OnAddSalaryProject: CompanyUserEvent()

    data class OnChangeSumSalaryProject(val sum: String): CompanyUserEvent()
    data class OnChangeInfoSalaryProject(val info: String): CompanyUserEvent()
}