package com.example.financysystem.presentation.screens.userScreen.viewModel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.useCase.UserRoleUseCases.ClientUserUseCases
import com.example.financysystem.presentation.screens.userScreen.event.ClientUserEvent
import com.example.financysystem.presentation.screens.userScreen.state.ClientUserState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ClientUserViewModel @Inject constructor(
    private val clientUserUseCases: ClientUserUseCases,
    private val savedStateHandle: SavedStateHandle
): ViewModel()
{
    private val _userEmail = MutableStateFlow(savedStateHandle.get<String>("userEmail") ?: "")

    private val _clientUserState = MutableStateFlow(ClientUserState())
    val clientUserState: StateFlow<ClientUserState> = _clientUserState.asStateFlow()

    init {
        viewModelScope.launch {
            val baseUser = clientUserUseCases.getBaseUserUseCase(_userEmail.value).firstOrNull()
            baseUser?.let {
                _clientUserState.value = ClientUserState(
                    email = it.email,
                    phone = it.phone,
                    firstName = it.firstName,
                    lastName = it.lastName,
                    surName = it.surName,
                )
            }
        }
    }

    fun onEvent(event: ClientUserEvent){
        when(event){
            is ClientUserEvent.onContentWindowChange -> {
                _clientUserState.update { it.copy(
                    clientSelectedContent = event.newContentWindow
                )
                }
            }
        }
    }
}