package com.example.financysystem.presentation.screens.userScreen.state

import com.example.domain.models.transfer.ITransfer
import com.example.domain.models.user.TypeOfUser
import com.example.financysystem.presentation.screens.userScreen.state.contentState.OperatorSelectedContent

data class OperatorUserState(
    val operatorSelectedContent: OperatorSelectedContent = OperatorSelectedContent.PROFILE,
    val transfers: List<ITransfer> = emptyList<ITransfer>(),

    override val id: Int = 0,
    override val email: String = "",
    override val phone: String = "",
    override val firstName: String = "",
    override val lastName: String = "",
    override val surName: String = "",
    override val typeOfUser: TypeOfUser = TypeOfUser.OperatorUser
): GeneralInfoUsers