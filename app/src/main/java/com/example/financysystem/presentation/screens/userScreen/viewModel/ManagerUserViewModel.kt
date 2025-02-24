package com.example.financysystem.presentation.screens.userScreen.viewModel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.useCase.UserRoleUseCases.ManagerUserUseCases
import com.example.financysystem.presentation.screens.userScreen.event.ManagerUserEvent
import com.example.financysystem.presentation.screens.userScreen.state.ManagerUserState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ManagerUserViewModel @Inject constructor(
    private val managerUserUseCases: ManagerUserUseCases,
    private val savedStateHandle: SavedStateHandle
): ViewModel()
{
    private val _userEmail = MutableStateFlow(savedStateHandle.get<String>("userEmail") ?: "")

    private val _managerUserState = MutableStateFlow(ManagerUserState())
    val managerUserState = _managerUserState.asStateFlow()

    init {
        viewModelScope.launch {
            val baseUser = managerUserUseCases.getBaseUserUseCase(_userEmail.value).firstOrNull()
            baseUser?.let {
                _managerUserState.value = ManagerUserState(
                    email = it.email,
                    phone = it.phone,
                    firstName = it.firstName,
                    lastName = it.lastName,
                    surName = it.surName,
                )
            }
        }
    }

    fun onEvent(event: ManagerUserEvent){
        when(event){
            is ManagerUserEvent.onContentWindowChange -> {
                _managerUserState.update { it.copy(
                    managerSelectedContent = event.newContentWindow
                )
                }
            }
        }
    }
}