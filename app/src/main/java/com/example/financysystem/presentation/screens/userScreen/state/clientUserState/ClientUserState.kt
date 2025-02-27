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
    val sumForCredit: SumForCredit = SumForCredit.ONE_THOUSAND,
    val monthCountCredit: MonthCountCredit = MonthCountCredit.THREE_MONTH,
    val isOpenDialogBankAccount: Boolean = false,
    val idOpenDialogBankAccount: Int = 0,
    val isOpenAddingDialogBankAccount: Boolean = false,

    val errorCreateTransfer: String? = null,
    val isOpenTransferDialog: Boolean = false,
    val idOpenTransferDialog: Int = 0,
    val inputTransferSum: String = "",
    val inputFromCardId: String = "",
    val inputToCardId: String = "",

    override val id: Int = 0,
    override val email: String = "",
    override val phone: String = "",
    override val firstName: String = "",
    override val lastName: String = "",
    override val surName: String = "",
    override val typeOfUser: TypeOfUser = TypeOfUser.ClientUser
): GeneralInfoUsers