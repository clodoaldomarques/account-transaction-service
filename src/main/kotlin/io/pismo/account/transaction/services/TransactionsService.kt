package io.pismo.account.transaction.services

import io.pismo.account.transaction.domain.entity.Transaction
import io.pismo.account.transaction.domain.interactors.TransactionsUseCase
import io.pismo.account.transaction.domain.repositories.TransactionPersistence
import org.springframework.stereotype.Service
import javax.transaction.Transactional

@Service
class TransactionsService(private val transactionPersistence: TransactionPersistence) : TransactionsUseCase {
    @Transactional(rollbackOn = [Exception::class])
    override fun registerTransaction(transaction: Transaction): Transaction {
        return transactionPersistence.registerTransaction(transaction.prepareTransactionToRecord())
    }
}