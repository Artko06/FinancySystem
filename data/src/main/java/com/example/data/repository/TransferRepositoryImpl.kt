package com.example.data.repository

import com.example.data.local.dao.BankAccountDao
import com.example.data.local.dao.BankDao
import com.example.data.local.dao.TransferDao
import com.example.data.local.dao.UserDao
import com.example.data.local.entity.bank.bankAccount.toDomain
import com.example.data.local.entity.bank.toDomain
import com.example.data.local.entity.transfer.toDomain
import com.example.data.local.entity.transfer.toEntity
import com.example.data.local.entity.user.toDomain
import com.example.domain.models.transfer.ITransfer
import com.example.domain.models.transfer.StatusTransfer
import com.example.domain.models.transfer.Transfer
import com.example.domain.repository.TransferRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.flowOf

class TransferRepositoryImpl(
    private val transferDao: TransferDao,
    private val bankAccountDao: BankAccountDao,
    private val userDao: UserDao,
    private val bankDao: BankDao
) : TransferRepository
{
    @OptIn(ExperimentalCoroutinesApi::class)
    override fun getAllTransfers(): Flow<List<ITransfer>> {
        return transferDao.getAllTransfers()
            .flatMapMerge { transfersEntity ->
                val transformedFlows = transfersEntity.map { transferEntity ->
                    combine(
                        bankAccountDao.getBaseBankAccountById(transferEntity.fromBaseBankAccountId),
                        bankAccountDao.getBaseBankAccountById(transferEntity.toBaseBankAccountId)
                    ) { fromBaseBankAccountEntity, toBaseBankAccountEntity ->
                        if (fromBaseBankAccountEntity != null && toBaseBankAccountEntity != null) {
                            combine(
                                userDao.getBaseUserById(fromBaseBankAccountEntity.baseUserId),
                                userDao.getBaseUserById(toBaseBankAccountEntity.baseUserId),
                                bankDao.getBankById(fromBaseBankAccountEntity.bankId),
                                bankDao.getBankById(toBaseBankAccountEntity.bankId)
                            ) { fromBaseUser, toBaseUser, fromBank, toBank ->
                                if (fromBaseUser != null && toBaseUser != null && fromBank != null && toBank != null) {
                                    transferEntity.toDomain(
                                        fromBaseBankAccount = fromBaseBankAccountEntity.toDomain(
                                            baseUser = fromBaseUser.toDomain(),
                                            bank = fromBank.toDomain()
                                        ),
                                        toBaseBankAccount = toBaseBankAccountEntity.toDomain(
                                            baseUser = toBaseUser.toDomain(),
                                            bank = toBank.toDomain()
                                        )
                                    )
                                } else {
                                    null
                                }
                            }
                        } else {
                            flowOf(null)
                        }
                    }.flatMapMerge { it }
                }
                combine(transformedFlows) { it.filterNotNull() }
            }
    }

    override suspend fun changeStatusTransfer(
        transfer: Transfer,
        statusTransfer: StatusTransfer
    ) {
        transferDao.changeTransferStatus(
            transferId = transfer.id,
            newStatus = statusTransfer.toString()
        )
    }

    override suspend fun insertTransfer(transfer: ITransfer) {
        when(transfer){
            is Transfer -> {
                transferDao.insertTransfer(transfer = transfer.toEntity(
                    fromBaseBankAccountId = transfer.fromBaseBankAccount.id,
                    toBaseBankAccountId = transfer.toBaseBankAccount.id
                    )
                )
            }
            else -> {
                throw IllegalArgumentException("Unsupported bank account type: ${transfer::class.java}")
            }
        }
    }

    override suspend fun deleteTransfer(transfer: ITransfer) {
        when(transfer){
            is Transfer -> {
                transferDao.deleteTransfer(transfer = transfer.toEntity(
                    fromBaseBankAccountId = transfer.fromBaseBankAccount.id,
                    toBaseBankAccountId = transfer.toBaseBankAccount.id
                )
                )
            }
            else -> {
                throw IllegalArgumentException("Unsupported bank account type: ${transfer::class.java}")
            }
        }
    }
}