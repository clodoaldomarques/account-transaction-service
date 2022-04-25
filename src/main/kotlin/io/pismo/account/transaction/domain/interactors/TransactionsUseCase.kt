package io.pismo.account.transaction.domain.interactors

import io.pismo.account.transaction.domain.entity.Account
import io.pismo.account.transaction.domain.entity.OperationType
import io.pismo.account.transaction.domain.entity.Transaction
import java.math.BigDecimal
import java.time.LocalDateTime
import java.util.UUID

interface TransactionsUseCase {
    fun registerTransaction(transaction: Transaction): Transaction

    fun buildTransaction(accountId: UUID, operation: Int, amount: BigDecimal): Transaction
}