package io.pismo.account.transaction.domain.entity

import java.math.BigDecimal
import java.time.LocalDateTime
import java.util.UUID

data class Transaction(val transactionId : UUID,
                       val accountId : UUID,
                       val operation: OperationType,
                       val amount : BigDecimal,
                       val eventDate : LocalDateTime){
    internal fun prepareTransactionToRecord() : Transaction {
        return this.copy(amount = this.operation.rule.convertAmountValue(this))
    }
}