package io.pismo.account.transaction.domain.repositories

import io.pismo.account.transaction.domain.entity.Transaction

interface TransactionPersistence {
    fun registerTransaction(transaction: Transaction) : Transaction
}