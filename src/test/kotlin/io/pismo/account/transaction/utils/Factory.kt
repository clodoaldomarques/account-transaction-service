package io.pismo.account.transaction.utils

import io.pismo.account.transaction.domain.entity.Account
import io.pismo.account.transaction.domain.entity.OperationType
import io.pismo.account.transaction.domain.entity.Transaction
import java.math.BigDecimal
import java.time.LocalDateTime
import java.util.UUID

fun buildAccount(
    accountId : UUID = UUID.randomUUID(),
    documentNumber : String = "123.456.789-00",
    availableCreditLimit : BigDecimal = BigDecimal(100.00)
) = Account( accountId, documentNumber, availableCreditLimit)

fun buildTransaction(
    transactionId : UUID = UUID.randomUUID(),
    account : Account = buildAccount(),
    operation: OperationType = OperationType.COMPRA_A_VISTA,
    amount : BigDecimal = BigDecimal.TEN,
    eventDate : LocalDateTime = LocalDateTime.now()
) = Transaction(transactionId, account, operation, amount, eventDate)