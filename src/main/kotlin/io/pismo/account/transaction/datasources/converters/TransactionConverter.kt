package io.pismo.account.transaction.datasources.converters

import io.pismo.account.transaction.datasources.tables.TransactionTable
import io.pismo.account.transaction.domain.entity.OperationType
import io.pismo.account.transaction.domain.entity.Transaction
import io.pismo.account.transaction.domain.exceptions.GenericBusinessException
import io.pismo.account.transaction.transports.api.controllers.models.PostTransactionRequestModel
import io.pismo.account.transaction.transports.api.controllers.models.TransactionResponseModel
import java.time.LocalDateTime
import java.util.*

fun Transaction.toTable() : TransactionTable {
    return TransactionTable(
        transactionId = this.transactionId,
        accountId = this.accountId,
        operationType = this.operation,
        amount = this.amount,
        eventDate = this.eventDate)
}

fun TransactionTable.toEntity() : Transaction {
    return Transaction(
        transactionId = this.transactionId,
        accountId = this.accountId,
        operation = this.operationType,
        amount = this.amount,
        eventDate = this.eventDate
    )
}