package com.example.financysystem.presentation.screens.userScreen.viewModel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.useCase.UserRoleUseCases.CompanyUserUseCases
import com.example.financysystem.presentation.screens.userScreen.event.CompanyUserEvent
import com.example.financysystem.presentation.screens.userScreen.state.CompanyUserState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CompanyUserViewModel @Inject constructor(
    private val companyUserUseCases: CompanyUserUseCases,
    private val savedStateHandle: SavedStateHandle
): ViewModel()
{
    private val _userEmail = MutableStateFlow(savedStateHandle.get<String>("userEmail") ?: "")

    private val _companyUserState = MutableStateFlow(CompanyUserState())
    val companyUserState = _companyUserState.asStateFlow()

    init {
        viewModelScope.launch {
            val baseUser = companyUserUseCases.getBaseUserUseCase(_userEmail.value).firstOrNull()
            baseUser?.let {
                _companyUserState.value = CompanyUserState(
                    id = it.id,
                    email = it.email,
                    phone = it.phone,
                    firstName = it.firstName,
                    lastName = it.lastName,
                    surName = it.surName,
                )
            }
        }
    }

    fun onEvent(event: CompanyUserEvent){
        when(event){
            is CompanyUserEvent.onContentWindowChange -> {
                _companyUserState.update { it.copy(
                    companySelectedContent = event.newContentWindow
                )
                }
            }
        }
    }
}