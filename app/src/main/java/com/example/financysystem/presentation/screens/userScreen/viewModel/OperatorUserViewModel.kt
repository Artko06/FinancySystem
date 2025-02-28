package com.example.financysystem.presentation.screens.userScreen.viewModel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.models.transfer.StatusTransfer
import com.example.domain.models.transfer.Transfer
import com.example.domain.useCase.UserRoleUseCases.OperatorUserUseCases
import com.example.financysystem.presentation.screens.userScreen.event.OperatorUserEvent
import com.example.financysystem.presentation.screens.userScreen.state.OperatorUserState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OperatorUserViewModel @Inject constructor(
    private val operatorUserUseCases: OperatorUserUseCases,
    private val savedStateHandle: SavedStateHandle
): ViewModel()
{
    private val _userEmail = MutableStateFlow(savedStateHandle.get<String>("userEmail") ?: "")

    private val _operatorUserState = MutableStateFlow(OperatorUserState())
    val operatorUserState = _operatorUserState.asStateFlow()

    init {
        viewModelScope.launch {
            val baseUser = operatorUserUseCases.getBaseUserUseCase(_userEmail.value).firstOrNull()
            baseUser?.let {
                _operatorUserState.value = OperatorUserState(
                    id = it.id,
                    email = it.email,
                    phone = it.phone,
                    firstName = it.firstName,
                    lastName = it.lastName,
                    surName = it.surName,
                )
            }
            onLoadAllTransfers()
            onLoadSalaryProjects()
        }
    }

    private suspend fun onLoadAllTransfers(){
        val transfers = operatorUserUseCases.getAllTransfersUseCase.invoke().firstOrNull()!!

        _operatorUserState.update { it.copy(
            transfers = transfers
        )
        }
    }

    private suspend fun onLoadSalaryProjects(){
        val salaryProjects = operatorUserUseCases.getAllSalaryProjectUseCase.invoke().firstOrNull()!!

        _operatorUserState.update {
            it.copy(
                salaryProjects = salaryProjects
            )
        }
    }

    fun onEvent(event: OperatorUserEvent){
        when(event){
            is OperatorUserEvent.onContentWindowChange -> {
                _operatorUserState.update { it.copy(
                    operatorSelectedContent = event.newContentWindow
                )
                }
            }

            is OperatorUserEvent.OnCancelTransfer -> {
                viewModelScope.launch {
                    val transfer = operatorUserUseCases.getTransferById(event.transferId).firstOrNull()

                    if(transfer != null){
                        operatorUserUseCases.changeStatusTransferUseCase(
                            transfer = transfer as Transfer,
                            statusTransfer = StatusTransfer.REJECTED_BY_OPERATOR
                        )

                        operatorUserUseCases.changeBalanceBankAccount.invoke(
                            account = transfer.fromBaseBankAccount,
                            newBalance = transfer.fromBaseBankAccount.balance + transfer.amount
                        )

                        operatorUserUseCases.changeBalanceBankAccount.invoke(
                            account = transfer.toBaseBankAccount,
                            newBalance = transfer.toBaseBankAccount.balance - transfer.amount
                        )

                        onLoadAllTransfers()
                    }
                }
            }

            is OperatorUserEvent.OnChangeStatusSalaryProject -> {
                viewModelScope.launch {
                    operatorUserUseCases.changeStatusSalaryProjectUseCase(
                        event.salaryProject,
                        event.newStatusJobBid
                    )

                    onLoadSalaryProjects()
                }
            }


        }
    }
}