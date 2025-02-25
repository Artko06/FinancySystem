package com.example.financysystem.presentation.screens.userScreen.state

import com.example.domain.models.user.TypeOfUser
import com.example.financysystem.presentation.screens.userScreen.state.contentState.CompanySelectedContent

data class CompanyUserState(
    val companySelectedContent: CompanySelectedContent = CompanySelectedContent.PROFILE,

    override val id: Int = 0,
    override val email: String = "",
    override val phone: String = "",
    override val firstName: String = "",
    override val lastName: String = "",
    override val surName: String = "",
    override val typeOfUser: TypeOfUser = TypeOfUser.CompanyUser
): GeneralInfoUsers