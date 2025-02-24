package com.example.financysystem.presentation.screens.userScreen.event

import com.example.financysystem.presentation.screens.userScreen.state.contentState.ClientSelectedContent

sealed class ClientUserEvent {
    data class onContentWindowChange(val newContentWindow: ClientSelectedContent): ClientUserEvent()
}