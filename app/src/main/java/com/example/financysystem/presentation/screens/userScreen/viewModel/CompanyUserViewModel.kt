package com.example.financysystem.presentation.screens.userScreen.viewModel

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.models.bank.bankAccount.StatusBankAccount
import com.example.domain.useCase.UserRoleUseCases.CompanyUserUseCases
import com.example.domain.useCase.allUserCases.transferUseCases.other.validateTransfer.ValidateTransfer
import com.example.financysystem.presentation.screens.userScreen.event.CompanyUserEvent
import com.example.financysystem.presentation.screens.userScreen.state.CompanyUserState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject

@HiltViewModel
class CompanyUserViewModel @Inject constructor(
    private val companyUserUseCases: CompanyUserUseCases,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {
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
            onLoadCompany()
            onLoadSalaryProject()
            onLoadCompanyBankAccounts()
        }
    }

    private suspend fun onLoadCompany(){
        val companyUser =
            companyUserUseCases.getCompanyUserByBaseUserUseCase.invoke(_companyUserState.value.id)
                .firstOrNull()!!

        _companyUserState.update {
            it.copy(
                company = companyUser.company
            )
        }
    }

    private suspend fun onLoadSalaryProject(){
        val salaryProjects =
            companyUserUseCases.getSalaryProjectsByCompanyUseCase(_companyUserState.value.company.id)
                .firstOrNull()!!

        _companyUserState.update {
            it.copy(
                salaryProjects = salaryProjects
            )
        }
    }

    private suspend fun onLoadCompanyBankAccounts(){
        val companyBankAccounts = companyUserUseCases
            .getCompanyBankAccountsByCompanyUseCase.invoke(_companyUserState.value.company).firstOrNull()!!

        _companyUserState.update { it.copy(
                companyBankAccounts = companyBankAccounts
        )
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun onEvent(event: CompanyUserEvent) {
        when (event) {
            is CompanyUserEvent.onContentWindowChange -> {
                _companyUserState.update {
                    it.copy(
                        companySelectedContent = event.newContentWindow
                    )
                }
            }

            CompanyUserEvent.OnAddSalaryProject -> {
                viewModelScope.launch {
                    if(_companyUserState.value.sumSalaryProject.toDoubleOrNull() == null){
                        _companyUserState.update { it.copy(
                            errorInputSalaryProject = "Неверный ввод заработной платы"
                        )
                        }
                        return@launch
                    }

                    companyUserUseCases.insertSalaryProjectUseCase.invoke(
                        info = _companyUserState.value.infoSalaryProject,
                        sum = _companyUserState.value.sumSalaryProject.toDouble(),
                        company = _companyUserState.value.company
                    )
                    _companyUserState.update { it.copy(
                        isOpenDialogAddingSalaryProject = false
                    )
                    }
                    onLoadSalaryProject()
                }
            }

            is CompanyUserEvent.OnChangeInfoSalaryProject -> {
                _companyUserState.update { it.copy(
                    infoSalaryProject = event.info
                ) }
            }

            is CompanyUserEvent.OnChangeSumSalaryProject -> {
                _companyUserState.update { it.copy(
                    sumSalaryProject = event.sum
                ) }
            }

            CompanyUserEvent.OnOpenAddingSalaryProject -> {
                _companyUserState.update { it.copy(
                    isOpenDialogAddingSalaryProject = !it.isOpenDialogAddingSalaryProject,
                    errorInputSalaryProject = null
                ) }
            }

            is CompanyUserEvent.OnChangeFromCardId -> {
                _companyUserState.update { it.copy(
                    inputFromCardId = event.cardId,
                ) }
            }

            is CompanyUserEvent.OnChangeStatusBankAccount -> {
                viewModelScope.launch {
                    val baseBankAccount =
                        companyUserUseCases.getBaseBankAccountById(event.cardId).firstOrNull()

                    if(baseBankAccount != null){
                        when(baseBankAccount.statusBankAccount){
                            StatusBankAccount.FROZEN -> companyUserUseCases
                                .changeStatusBaseBankAccountUseCase.invoke(baseBankAccount, StatusBankAccount.NORMAL)
                            StatusBankAccount.BLOCKED -> {}
                            StatusBankAccount.NORMAL -> companyUserUseCases
                                .changeStatusBaseBankAccountUseCase.invoke(baseBankAccount, StatusBankAccount.FROZEN)
                        }
                        onLoadCompanyBankAccounts()
                    }
                }
            }

            is CompanyUserEvent.OnChangeToCardId -> {
                _companyUserState.update { it.copy(
                    inputToCardId = event.cardId,
                ) }
            }

            is CompanyUserEvent.OnChangeTransferSum -> {
                _companyUserState.update { it.copy(
                    inputTransferSum = event.sum,
                ) }
            }

            is CompanyUserEvent.OnShowBankAccountDialog -> {
                _companyUserState.update { it.copy(
                    isOpenDialogBankAccount = !it.isOpenDialogBankAccount,
                    idOpenDialogBankAccount = event.cardId
                ) }
            }

            is CompanyUserEvent.OnShowTransferDialog -> {
                _companyUserState.update { it.copy(
                    isOpenTransferDialog = !it.isOpenTransferDialog,
                    idOpenTransferDialog = event.cardId,
                    errorCreateTransfer = null
                ) }
            }

            CompanyUserEvent.OnCreateTransfer -> {
                viewModelScope.launch {
                    val validateTransfer = companyUserUseCases.validateTransferUseCase.invoke(
                        cardFromId = _companyUserState.value.inputFromCardId,
                        cardToId = _companyUserState.value.inputToCardId,
                        sum = _companyUserState.value.inputTransferSum
                    )

                    _companyUserState.update { it.copy(
                        errorCreateTransfer = when(validateTransfer){
                            ValidateTransfer.INCORRECT_CARD_GETTER -> "Неверные данные карты получателя"
                            ValidateTransfer.INCORRECT_CARD_SENDER -> "Неверные данные карты отправителя"
                            ValidateTransfer.INCORRECT_SUM -> "Неверно указана сумма"
                            ValidateTransfer.FROZEN_BLOCKED_ACCOUNT_SENDER -> "Этот счёт заморожен или заблокрирован"
                            ValidateTransfer.BLOCK_ACCOUNT_GETTER -> "Аккаунт получателя заблокирован"
                            ValidateTransfer.NOT_ACCEPTED_CREDIT_ACCOUNT -> "Кредитный аккаунт не подтверждён"
                            ValidateTransfer.NOT_ENOUGH_SUM -> "Недостаточно средств"
                            ValidateTransfer.OK -> null
                        }
                    )
                    }

                    if(_companyUserState.value.errorCreateTransfer != null) return@launch

                    val fromBankAccount =
                        companyUserUseCases.getBaseBankAccountById(_companyUserState.value.inputFromCardId.toInt()).firstOrNull()
                    val toBankAccount =
                        companyUserUseCases.getBaseBankAccountById(_companyUserState.value.inputToCardId.toInt()).firstOrNull()

                    if(_companyUserState.value.errorCreateTransfer == null){
                        companyUserUseCases.createTransferUseCase.invoke(
                            fromBaseBankAccount = fromBankAccount!!,
                            toBaseBankAccount = toBankAccount!!,
                            amount = _companyUserState.value.inputTransferSum.toDouble(),
                            dateTransfer = LocalDate.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")),
                            timeTransfer = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"))
                        )
                    }

                    onEvent(CompanyUserEvent.OnShowTransferDialog(cardId = _companyUserState.value.inputFromCardId.toInt()))
                    onLoadCompanyBankAccounts()
                }
            }


        }
    }
}