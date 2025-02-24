package com.example.financysystem.presentation.screens.userScreen.state

import com.example.domain.models.user.TypeOfUser
import com.example.financysystem.presentation.screens.userScreen.state.contentState.ClientSelectedContent

data class ClientUserState(
    val clientSelectedContent: ClientSelectedContent = ClientSelectedContent.PROFILE,

    override val email: String = "",
    override val phone: String = "",
    override val firstName: String = "",
    override val lastName: String = "",
    override val surName: String = "",
    override val typeOfUser: TypeOfUser = TypeOfUser.ClientUser
): GeneralInfoUsers