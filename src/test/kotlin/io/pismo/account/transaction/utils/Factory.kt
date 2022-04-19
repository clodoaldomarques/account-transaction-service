package io.pismo.account.transaction.utils

import io.pismo.account.transaction.domain.entity.Account
import io.pismo.account.transaction.domain.entity.OperationType
import io.pismo.account.transaction.domain.entity.Transaction
import java.math.BigDecimal
import java.time.LocalDateTime
import java.util.UUID

fun buildAccount(
    accountId : UUID = UUID.randomUUID(),
    documentNumber : String = "123.456.789-00"
) = Account( accountId, documentNumber)

fun buildTransaction(
    transactionId : UUID = UUID.randomUUID(),
    accountId : UUID = UUID.randomUUID(),
    operation: OperationType = OperationType.COMPRA_A_VISTA,
    amount : BigDecimal = BigDecimal.TEN,
    eventDate : LocalDateTime = LocalDateTime.now()
) = Transaction(transactionId, accountId, operation, amount, eventDate)