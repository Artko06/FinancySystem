package com.example.financysystem.presentation.screens.userScreen.event

import com.example.financysystem.presentation.screens.userScreen.state.contentState.ManagerSelectedContent

sealed class ManagerUserEvent {
    data class onContentWindowChange(val newContentWindow: ManagerSelectedContent): ManagerUserEvent()
}