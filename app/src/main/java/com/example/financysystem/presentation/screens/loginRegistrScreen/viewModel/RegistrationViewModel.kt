package com.example.financysystem.presentation.screens.loginRegistrScreen.viewModel

import androidx.lifecycle.ViewModel
import com.example.domain.useCase.UserRoleUseCases.StartUserUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RegistrationViewModel @Inject constructor(
    private val startUserUseCases: StartUserUseCases
): ViewModel()
{

}