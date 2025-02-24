package com.example.financysystem.presentation.screens.userScreen.event

import com.example.financysystem.presentation.screens.userScreen.state.contentState.OperatorSelectedContent

sealed class OperatorUserEvent {
    data class onContentWindowChange(val newContentWindow: OperatorSelectedContent): OperatorUserEvent()
}