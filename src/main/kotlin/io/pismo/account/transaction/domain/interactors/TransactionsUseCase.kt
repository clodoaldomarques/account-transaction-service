package io.pismo.account.transaction.domain.interactors

import io.pismo.account.transaction.domain.entity.Transaction

interface TransactionsUseCase {
    fun registerTransaction(transaction: Transaction) : Transaction
}