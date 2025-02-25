package com.example.financysystem.presentation.screens.userScreen.event

import com.example.financysystem.presentation.screens.userScreen.state.clientUserState.TypeBankAccount
import com.example.financysystem.presentation.screens.userScreen.state.contentState.ClientSelectedContent

sealed class ClientUserEvent {
    data class onContentWindowChange(val newContentWindow: ClientSelectedContent): ClientUserEvent()

    data class onSelectBank(val indexBank: Int) : ClientUserEvent()
    object onToggleMenuBank: ClientUserEvent()
    data class onSelectTypeBankAccount(val typeBankAccount: TypeBankAccount): ClientUserEvent()

    object OnAddBankAccount : ClientUserEvent()
    data class OnLoadStandardBankAccounts(val baseUserId: Int) : ClientUserEvent()
    data class OnLoadCreditBankAccounts(val baseUserId: Int) : ClientUserEvent()
    object OnLoadBanks: ClientUserEvent()
}