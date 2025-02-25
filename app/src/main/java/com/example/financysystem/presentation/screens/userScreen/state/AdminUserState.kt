package com.example.financysystem.presentation.screens.userScreen.state

import com.example.domain.models.user.TypeOfUser
import com.example.financysystem.presentation.screens.userScreen.state.contentState.AdminSelectedContent

data class AdminUserState(
    val adminSelectedContent: AdminSelectedContent = AdminSelectedContent.PROFILE,

    override val id: Int = 0,
    override val email: String = "",
    override val phone: String = "",
    override val firstName: String = "",
    override val lastName: String = "",
    override val surName: String = "",
    override val typeOfUser: TypeOfUser = TypeOfUser.AdminUser
) : GeneralInfoUsers