package io.pismo.account.transaction.datasources.converters

import io.pismo.account.transaction.datasources.tables.TransactionTable
import io.pismo.account.transaction.domain.entity.Account
import io.pismo.account.transaction.domain.entity.OperationType
import io.pismo.account.transaction.domain.entity.Transaction
import io.pismo.account.transaction.domain.exceptions.GenericBusinessException
import io.pismo.account.transaction.transports.api.controllers.models.PostTransactionRequestModel
import io.pismo.account.transaction.transports.api.controllers.models.TransactionResponseModel
import java.math.BigDecimal
import java.time.LocalDateTime
import java.util.*

fun Transaction.toTable() : TransactionTable {
    return TransactionTable(
        transactionId = this.transactionId,
        account = this.account.toTable(),
        operationType = this.operation,
        amount = this.amount,
        eventDate = this.eventDate)
}

fun TransactionTable.toEntity() : Transaction {
    return Transaction(
        transactionId = this.transactionId,
        account = this.account.toEntity(),
        operation = this.operationType,
        amount = this.amount,
        eventDate = this.eventDate
    )
}