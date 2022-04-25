package io.pismo.account.transaction.services

import io.pismo.account.transaction.domain.entity.OperationType
import io.pismo.account.transaction.domain.entity.Transaction
import io.pismo.account.transaction.domain.exceptions.GenericBusinessException
import io.pismo.account.transaction.domain.exceptions.ResourceNotFoundException
import io.pismo.account.transaction.domain.interactors.TransactionsUseCase
import io.pismo.account.transaction.domain.repositories.AccountPersistence
import io.pismo.account.transaction.domain.repositories.TransactionPersistence
import io.pismo.account.transaction.transports.api.converters.toEntity
import org.springframework.stereotype.Service
import java.math.BigDecimal
import java.time.LocalDateTime
import java.util.*
import javax.transaction.Transactional

@Service
class TransactionsService(private val transactionPersistence: TransactionPersistence, private val accountPersistence: AccountPersistence) : TransactionsUseCase {
    @Transactional(rollbackOn = [Exception::class])
    override fun registerTransaction(transaction: Transaction): Transaction {
        val preparedTransaction = transaction.prepareTransactionToRecord()
        accountPersistence.update(preparedTransaction.account)
        return transactionPersistence.registerTransaction(preparedTransaction)
    }

    override fun buildTransaction(accountId: UUID, operation: Int, amount: BigDecimal): Transaction {
        val optionalAccount = accountPersistence.findById(accountId)
        if (optionalAccount.isEmpty){
            throw ResourceNotFoundException("Account not found")
        }
        return OperationType.getById(operation)?.let {
            Transaction(
                transactionId = UUID.randomUUID(),
                account = optionalAccount.get(),
                operation = it,
                amount = amount,
                eventDate = LocalDateTime.now()
            )
        } ?: throw GenericBusinessException("Invalid Operation Type")

    }
}