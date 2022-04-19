package io.pismo.account.transaction.transports.api.converters

import io.pismo.account.transaction.domain.entity.OperationType
import io.pismo.account.transaction.domain.entity.Transaction
import io.pismo.account.transaction.domain.exceptions.GenericBusinessException
import io.pismo.account.transaction.transports.api.controllers.models.PostTransactionRequestModel
import io.pismo.account.transaction.transports.api.controllers.models.TransactionResponseModel
import java.time.LocalDateTime
import java.util.*

fun Transaction.toModel() : TransactionResponseModel{
    return TransactionResponseModel(
        transaction_id = this.transactionId,
        account_id = this.accountId,
        operation_type_description = this.operation.description,
        amount = this.amount,
        eventDate = this.eventDate)
}

fun PostTransactionRequestModel.toEntity() : Transaction{
    return OperationType.getById(this.operation_type_id)?.let {
        Transaction(
            transactionId = UUID.randomUUID(),
            accountId = this.account_id,
            operation = it,
            amount = this.amount,
            eventDate = LocalDateTime.now()
        )
    } ?: throw GenericBusinessException("Invalid Operation Type")
}