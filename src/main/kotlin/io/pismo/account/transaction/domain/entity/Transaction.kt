package io.pismo.account.transaction.domain.entity

import io.pismo.account.transaction.domain.exceptions.GenericBusinessException
import java.math.BigDecimal
import java.time.LocalDateTime
import java.util.UUID

data class Transaction(val transactionId : UUID,
                       val account : Account,
                       val operation: OperationType,
                       val amount : BigDecimal,
                       val eventDate : LocalDateTime){
    internal fun prepareTransactionToRecord() : Transaction {
        validateAmountLessZero()
        val account = validateAvailableCreditLimit()
        return this.copy(account = account, amount = this.operation.rule.convertAmountValue(this))
    }

    private fun validateAmountLessZero(){
        if(amount <= BigDecimal.ZERO){
            throw GenericBusinessException("Amount cannot be less than zero")
        }
    }

    private fun validateAvailableCreditLimit() : Account {
        val balance = account.availableCreditLimit.add(this.operation.rule.convertAmountValue(this))
        return if (balance < BigDecimal.ZERO){
            throw GenericBusinessException("Available Credit Limit cannot be less than zero")
        } else {
            account.copy(availableCreditLimit = balance)
        }
    }

}