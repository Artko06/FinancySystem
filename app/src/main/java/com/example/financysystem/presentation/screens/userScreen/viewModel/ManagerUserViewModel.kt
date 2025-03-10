package com.example.financysystem.presentation.screens.userScreen.viewModel

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.models.actionLog.ActionType
import com.example.domain.models.bank.bankAccount.StatusBankAccount
import com.example.domain.models.transfer.StatusTransfer
import com.example.domain.models.transfer.Transfer
import com.example.domain.models.user.BaseUser
import com.example.domain.useCase.UserRoleUseCases.ManagerUserUseCases
import com.example.financysystem.presentation.screens.userScreen.event.ManagerUserEvent
import com.example.financysystem.presentation.screens.userScreen.state.ManagerUserState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject

@RequiresApi(Build.VERSION_CODES.O)
@HiltViewModel
class ManagerUserViewModel @Inject constructor(
    private val managerUserUseCases: ManagerUserUseCases,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _userEmail = MutableStateFlow(savedStateHandle.get<String>("userEmail") ?: "")

    private val _managerUserState = MutableStateFlow(ManagerUserState())
    val managerUserState = _managerUserState.asStateFlow()

    init {
        viewModelScope.launch {
            val baseUser = managerUserUseCases.getBaseUserUseCase(_userEmail.value).firstOrNull()
            baseUser?.let {
                _managerUserState.value = ManagerUserState(
                    id = it.id,
                    email = it.email,
                    phone = it.phone,
                    firstName = it.firstName,
                    lastName = it.lastName,
                    surName = it.surName,
                )
                onCreateActionLog(baseUser = it, ActionType.AUTHORIZATION)
            }
            onLoadBankAccounts()
            onLoadSalaryProjects()
            onLoadAllTransfers()
        }
    }

    private suspend fun onLoadAllTransfers() {
        val transfers = managerUserUseCases.getAllTransfersUseCase.invoke().firstOrNull()!!

        _managerUserState.update {
            it.copy(
                transfers = transfers
            )
        }
    }

    private suspend fun onLoadSalaryProjects() {
        val salaryProjects = managerUserUseCases.getAllSalaryProjectUseCase.invoke().firstOrNull()!!

        _managerUserState.update {
            it.copy(
                salaryProjects = salaryProjects
            )
        }
    }

    private suspend fun onCreateActionLog(baseUser: BaseUser, actionType: ActionType){
        managerUserUseCases.insertActionLogUseCase.invoke(
            baseUser = baseUser,
            actionType = actionType,
            date = LocalDate.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")),
            time = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"))
        )
    }

    private suspend fun onLoadBankAccounts() {
        val standardBankAccounts =
            managerUserUseCases.getAllStandardBankAccount.invoke().firstOrNull()!!
        val creditBankAccounts =
            managerUserUseCases.getAllCreditBankAccount.invoke().firstOrNull()!!
        val companyBankAccounts =
            managerUserUseCases.getAllCompanyBankAccount.invoke().firstOrNull()!!


        _managerUserState.update {
            it.copy(
                standardBankAccounts = standardBankAccounts,
                creditBankAccounts = creditBankAccounts,
                companyBankAccounts = companyBankAccounts
            )
        }

    }

    fun onEvent(event: ManagerUserEvent) {
        when (event) {
            is ManagerUserEvent.onContentWindowChange -> {
                _managerUserState.update {
                    it.copy(
                        managerSelectedContent = event.newContentWindow
                    )
                }
            }

            is ManagerUserEvent.OnCancelTransfer -> {
                viewModelScope.launch {
                    val transfer =
                        managerUserUseCases.getTransferById(event.transferId).firstOrNull()

                    if (transfer != null) {
                        managerUserUseCases.changeStatusTransferUseCase(
                            transfer = transfer as Transfer,
                            statusTransfer = StatusTransfer.REJECTED_BY_OPERATOR
                        )

                        managerUserUseCases.changeBalanceBankAccount.invoke(
                            account = transfer.fromBaseBankAccount,
                            newBalance = transfer.fromBaseBankAccount.balance + transfer.amount
                        )

                        managerUserUseCases.changeBalanceBankAccount.invoke(
                            account = transfer.toBaseBankAccount,
                            newBalance = transfer.toBaseBankAccount.balance - transfer.amount
                        )

                        val baseUser = managerUserUseCases.getBaseUserUseCase
                            .invoke(_managerUserState.value.email).first()!!

                        onCreateActionLog(baseUser = baseUser, ActionType.CANCEL_TRANSFER)
                        onLoadAllTransfers()
                        onLoadBankAccounts()
                    }
                }
            }

            is ManagerUserEvent.OnChangeStatusSalaryProject -> {
                viewModelScope.launch {
                    managerUserUseCases.changeStatusSalaryProjectUseCase(
                        event.salaryProject,
                        event.newStatusJobBid
                    )

                    val baseUser = managerUserUseCases.getBaseUserUseCase
                        .invoke(_managerUserState.value.email).first()!!

                    onCreateActionLog(baseUser = baseUser, ActionType.CHANGE_STATUS_SALARY_PROJECT)
                    onLoadSalaryProjects()
                }
            }

            is ManagerUserEvent.OnChangeStatusBankAccount -> {
                viewModelScope.launch {
                    val baseBankAccount =
                        managerUserUseCases.getBaseBankAccountById(event.cardId).firstOrNull()

                    if (baseBankAccount != null) {
                        when (baseBankAccount.statusBankAccount) {
                            StatusBankAccount.FROZEN -> managerUserUseCases
                                .changeStatusBaseBankAccountUseCase.invoke(
                                    baseBankAccount,
                                    StatusBankAccount.BLOCKED
                                )

                            StatusBankAccount.BLOCKED -> managerUserUseCases
                                .changeStatusBaseBankAccountUseCase.invoke(
                                    baseBankAccount,
                                    StatusBankAccount.NORMAL
                                )

                            StatusBankAccount.NORMAL -> managerUserUseCases
                                .changeStatusBaseBankAccountUseCase.invoke(
                                    baseBankAccount,
                                    StatusBankAccount.FROZEN
                                )
                        }

                        val baseUser = managerUserUseCases.getBaseUserUseCase
                            .invoke(_managerUserState.value.email).first()!!

                        onCreateActionLog(baseUser = baseUser, ActionType.CHANGE_STATUS_BANK_ACCOUNT_BY_MANAGER)
                        onLoadBankAccounts()
                    }
                }
            }

            is ManagerUserEvent.OnShowBankAccountDialog -> {
                _managerUserState.update { it.copy(
                    isOpenDialogBankAccount = !it.isOpenDialogBankAccount,
                    idOpenDialogBankAccount = event.cardId
                ) }
            }

            is ManagerUserEvent.OnChangeStatusCreditBankAccount -> {
                viewModelScope.launch {
                    managerUserUseCases.changeStatusCreditBankAccountUseCase.invoke(
                        creditBankAccountId = event.creditBankAccountId,
                        statusCreditBid = event.statusCreditBid
                    )

                    onLoadBankAccounts()
                }
            }
        }
    }
}