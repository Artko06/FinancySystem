package com.example.financysystem.presentation.screens.userScreen.event

import com.example.financysystem.presentation.screens.userScreen.state.contentState.CompanySelectedContent

sealed class CompanyUserEvent {
    data class onContentWindowChange(val newContentWindow: CompanySelectedContent): CompanyUserEvent()
}