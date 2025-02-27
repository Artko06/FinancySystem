package com.example.financysystem.presentation.screens.userScreen.viewModel

import android.util.Log
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
        }
    }

    private suspend fun onLoadCompany(){
        val companyUser =
            companyUserUseCases.getCompanyUserByBaseUserUseCase.invoke(_companyUserState.value.id)
                .firstOrNull()!!
        Log.d("CompanyUserEvent","OnLoadCompany")
        _companyUserState.update {
            it.copy(
                company = companyUser.company
            )
        }
    }

    private suspend fun onLoadSalaryProject(){
        Log.d("CompanyUserEvent","OnLoadSalaryProjects")
        val salaryProjects =
            companyUserUseCases.getSalaryProjectsByCompanyUseCase(_companyUserState.value.company.id)
                .firstOrNull()!!

        _companyUserState.update {
            it.copy(
                salaryProjects = salaryProjects
            )
        }
    }

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
        }
    }
}