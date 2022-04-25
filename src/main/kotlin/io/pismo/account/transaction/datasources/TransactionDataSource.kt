package io.pismo.account.transaction.datasources

import io.pismo.account.transaction.datasources.converters.toEntity
import io.pismo.account.transaction.datasources.converters.toTable
import io.pismo.account.transaction.datasources.repositories.TransactionRepository
import io.pismo.account.transaction.domain.entity.Transaction
import io.pismo.account.transaction.domain.repositories.AccountPersistence
import io.pismo.account.transaction.domain.repositories.TransactionPersistence
import org.springframework.stereotype.Component

@Component
class TransactionDataSource(private val transactionRepository: TransactionRepository, private val accountPersistence: AccountPersistence) : TransactionPersistence {
    override fun registerTransaction(transaction: Transaction): Transaction {
        return transactionRepository.save(transaction.toTable()).toEntity()
    }
}