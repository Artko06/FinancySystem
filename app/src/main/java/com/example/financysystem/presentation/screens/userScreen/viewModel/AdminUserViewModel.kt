package com.example.financysystem.presentation.screens.userScreen.viewModel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.useCase.UserRoleUseCases.AdminUserUseCases
import com.example.financysystem.presentation.screens.userScreen.event.AdminUserEvent
import com.example.financysystem.presentation.screens.userScreen.state.AdminUserState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AdminUserViewModel @Inject constructor(
    private val adminUserUseCases: AdminUserUseCases,
    private val savedStateHandle: SavedStateHandle
): ViewModel()
{
    private val _userEmail = MutableStateFlow(savedStateHandle.get<String>("userEmail") ?: "")

    private val _adminUserState = MutableStateFlow(AdminUserState())
    val adminUserState = _adminUserState.asStateFlow()

    init {
        viewModelScope.launch {
            val baseUser = adminUserUseCases.getBaseUserUseCase(_userEmail.value).firstOrNull()
            baseUser?.let {
                _adminUserState.value = AdminUserState(
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

    fun onEvent(event: AdminUserEvent){
        when(event){
            is AdminUserEvent.onContentWindowChange -> {
                _adminUserState.update { it.copy(
                    adminSelectedContent = event.newContentWindow
                )
                }
            }
        }
    }
}