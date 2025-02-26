package com.example.financysystem.presentation.screens.userScreen.viewModel

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.models.bank.bankAccount.StatusBankAccount
import com.example.domain.useCase.UserRoleUseCases.ClientUserUseCases
import com.example.financysystem.presentation.screens.userScreen.event.ClientUserEvent
import com.example.financysystem.presentation.screens.userScreen.event.ClientUserEvent.OnLoadCreditBankAccounts
import com.example.financysystem.presentation.screens.userScreen.event.ClientUserEvent.OnLoadStandardBankAccounts
import com.example.financysystem.presentation.screens.userScreen.state.clientUserState.ClientUserState
import com.example.financysystem.presentation.screens.userScreen.state.clientUserState.TypeBankAccount
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

@RequiresApi(Build.VERSION_CODES.O)
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
                    id = it.id,
                    email = it.email,
                    phone = it.phone,
                    firstName = it.firstName,
                    lastName = it.lastName,
                    surName = it.surName,
                )
                onEvent(ClientUserEvent.OnLoadBanks)
                onEvent(ClientUserEvent.OnLoadStandardBankAccounts(baseUserId = it.id))
                onEvent(ClientUserEvent.OnLoadCreditBankAccounts(baseUserId = it.id))
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

            is ClientUserEvent.OnAddBankAccount -> {
                viewModelScope.launch {
                    val baseUser = clientUserUseCases.getBaseUserUseCase
                        .invoke(email = _clientUserState.value.email).first()!!

                    when (_clientUserState.value.selectedTypeBankAccount) {
                        TypeBankAccount.STANDARD -> {
                            if (_clientUserState.value.standardBankAccounts.size < 5) {
                                clientUserUseCases.insertStandardBankAccountUseCase.invoke(
                                    bank = _clientUserState.value.banks[_clientUserState.value.selectedIndexBank],
                                    baseUser = baseUser,
                                    balance = 0.0
                                )
                            }
                        }


                        TypeBankAccount.CREDIT -> {
                            if (_clientUserState.value.creditBankAccounts.size < 5) {
                                clientUserUseCases.insertCreditBankAccountUseCase.invoke(
                                    bank = _clientUserState.value.banks[_clientUserState.value.selectedIndexBank],
                                    baseUser = baseUser,
                                    balance = _clientUserState.value.sumForCredit.sum,
                                    creditLastDate = LocalDate.now()
                                        .plusMonths(_clientUserState.value.monthCountCredit.months.toLong())
                                        .toString(),
                                    creditTotalSum = _clientUserState.value.sumForCredit.sum,
                                    countMonthsCredit = _clientUserState.value.monthCountCredit.months
                                )
                            }
                        }
                    }

                    onEvent(OnLoadStandardBankAccounts(_clientUserState.value.id)) // После добавления обновляем список
                    onEvent(OnLoadCreditBankAccounts(_clientUserState.value.id))
                }
            }

            is OnLoadCreditBankAccounts -> {
                viewModelScope.launch {
                    val baseUser = clientUserUseCases.getBaseUserUseCase
                        .invoke(email = _clientUserState.value.email).first()!!

                    val creditBankAccounts = clientUserUseCases
                        .getCreditBankAccountByBaseUserUseCase.invoke(baseUser).first()


                    _clientUserState.update { it.copy(
                        creditBankAccounts = creditBankAccounts
                    )
                    }
                }
            }

            is OnLoadStandardBankAccounts -> {
                viewModelScope.launch {
                    val baseUser = clientUserUseCases.getBaseUserUseCase
                        .invoke(email = _clientUserState.value.email).first()!!

                    val standardBankAccounts = clientUserUseCases
                        .getStandardBankAccountsByBaseUserUseCase.invoke(baseUser).first()


                    _clientUserState.update { it.copy(
                        standardBankAccounts = standardBankAccounts
                    )
                    }
                }
            }

            ClientUserEvent.OnLoadBanks -> {
                viewModelScope.launch {
                    val banks = clientUserUseCases.getAllBanksUseCases.invoke().first()

                    _clientUserState.update { it.copy(
                        banks = banks
                    )
                    }
                }
            }

            is ClientUserEvent.onSelectBank -> {
                _clientUserState.update { it.copy(
                    selectedIndexBank = event.indexBank
                )
                }
            }

            is ClientUserEvent.onToggleMenuBank -> {
                _clientUserState.update { it.copy(
                    isOpenMenuBank = !it.isOpenMenuBank
                ) }
            }

            is ClientUserEvent.onSelectTypeBankAccount -> {
                _clientUserState.update { it.copy(
                    selectedTypeBankAccount = event.typeBankAccount
                )
                }
            }

            is ClientUserEvent.OnShowBankAccountDialog -> {
                _clientUserState.update { it.copy(
                    isOpenDialogBankAccount = !it.isOpenDialogBankAccount,
                    idOpenDialogBankAccount = event.cardId
                ) }
            }

            ClientUserEvent.OnOpenAddingDialogBankAccount -> {
                _clientUserState.update { it.copy(
                    isOpenAddingDialogBankAccount = !it.isOpenAddingDialogBankAccount
                ) }
            }

            is ClientUserEvent.OnSelectMonthCountCredit -> {
                _clientUserState.update { it.copy(
                    monthCountCredit = event.monthCountCredit
                ) }
            }

            is ClientUserEvent.OnSelectSumForCredit -> {
                _clientUserState.update { it.copy(
                    sumForCredit = event.sumForCredit
                ) }
            }

            is ClientUserEvent.OnChangeStatusBankAccount -> {
                viewModelScope.launch {
                    val baseBankAccount =
                        clientUserUseCases.getBaseBankAccountById(event.cardId).firstOrNull()

                    if(baseBankAccount != null){
                        when(baseBankAccount.statusBankAccount){
                            StatusBankAccount.FROZEN -> clientUserUseCases
                                .changeStatusBaseBankAccountUseCase.invoke(baseBankAccount, StatusBankAccount.NORMAL)
                            StatusBankAccount.BLOCKED -> {}
                            StatusBankAccount.NORMAL -> clientUserUseCases
                                .changeStatusBaseBankAccountUseCase.invoke(baseBankAccount, StatusBankAccount.FROZEN)
                        }
                        onEvent(ClientUserEvent.OnLoadStandardBankAccounts(baseUserId = _clientUserState.value.id))
                        onEvent(ClientUserEvent.OnLoadCreditBankAccounts(baseUserId = _clientUserState.value.id))
                    }
                }
            }
        }
    }
}