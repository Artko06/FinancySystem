package com.example.domain.useCase.allUserCases.transferUseCases.other.validateTransfer

enum class ValidateTransfer {
    INCORRECT_CARD_GETTER,
    INCORRECT_CARD_SENDER,
    INCORRECT_SUM,
    FROZEN_BLOCKED_ACCOUNT_SENDER,
    BLOCK_ACCOUNT_GETTER,
    NOT_ACCEPTED_CREDIT_ACCOUNT,
    NOT_ENOUGH_SUM,
    OK
}