package com.example.financysystem.presentation.screens.userScreen.event

import com.example.financysystem.presentation.screens.userScreen.state.clientUserState.MonthCountCredit
import com.example.financysystem.presentation.screens.userScreen.state.clientUserState.SumForCredit
import com.example.financysystem.presentation.screens.userScreen.state.clientUserState.TypeBankAccount
import com.example.financysystem.presentation.screens.userScreen.state.contentState.ClientSelectedContent

sealed class ClientUserEvent {
    data class onContentWindowChange(val newContentWindow: ClientSelectedContent): ClientUserEvent()

    data class onSelectBank(val indexBank: Int) : ClientUserEvent()
    object onToggleMenuBank: ClientUserEvent()
    data class onSelectTypeBankAccount(val typeBankAccount: TypeBankAccount): ClientUserEvent()
    data class OnShowBankAccountDialog(val cardId: Int): ClientUserEvent()
    object OnOpenAddingDialogBankAccount: ClientUserEvent()
    data class OnSelectSumForCredit(val sumForCredit: SumForCredit): ClientUserEvent()
    data class OnSelectMonthCountCredit(val monthCountCredit: MonthCountCredit): ClientUserEvent()
    data class OnChangeStatusBankAccount(val cardId: Int) : ClientUserEvent()

    object OnCreateTransfer : ClientUserEvent()
    data class OnShowTransferDialog(val cardId: Int): ClientUserEvent()
    data class OnChangeTransferSum(val sum: String): ClientUserEvent()
    data class OnChangeFromCardId(val cardId: String): ClientUserEvent()
    data class OnChangeToCardId(val cardId: String): ClientUserEvent()


    object OnAddBankAccount : ClientUserEvent()
    data class OnLoadStandardBankAccounts(val baseUserId: Int) : ClientUserEvent()
    data class OnLoadCreditBankAccounts(val baseUserId: Int) : ClientUserEvent()
    object OnLoadBanks: ClientUserEvent()
}