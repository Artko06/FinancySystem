package com.example.financysystem.presentation.screens.userScreen.state.clientUserState

import com.example.domain.models.bank.Bank
import com.example.domain.models.bank.bankAccount.creditBankAccount.ICreditBankAccount
import com.example.domain.models.bank.bankAccount.standartBankAccount.IStandardBankAccount
import com.example.domain.models.user.TypeOfUser
import com.example.financysystem.presentation.screens.userScreen.state.GeneralInfoUsers
import com.example.financysystem.presentation.screens.userScreen.state.contentState.ClientSelectedContent

data class ClientUserState(
    val clientSelectedContent: ClientSelectedContent = ClientSelectedContent.PROFILE,
    val standardBankAccounts: List<IStandardBankAccount> = emptyList<IStandardBankAccount>(),
    val creditBankAccounts: List<ICreditBankAccount> = emptyList<ICreditBankAccount>(),

    val banks: List<Bank> = emptyList<Bank>(),
    val selectedIndexBank: Int = 0,
    val isOpenMenuBank: Boolean = false,
    val selectedTypeBankAccount: TypeBankAccount = TypeBankAccount.STANDARD,

    override val id: Int = 0,
    override val email: String = "",
    override val phone: String = "",
    override val firstName: String = "",
    override val lastName: String = "",
    override val surName: String = "",
    override val typeOfUser: TypeOfUser = TypeOfUser.ClientUser
): GeneralInfoUsers