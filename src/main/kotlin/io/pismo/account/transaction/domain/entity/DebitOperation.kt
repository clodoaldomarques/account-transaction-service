package io.pismo.account.transaction.domain.entity

import java.math.BigDecimal

class DebitOperation : OperationRules {
    override fun convertAmountValue(transaction: Transaction): BigDecimal {
        return if (transaction.amount < BigDecimal.ZERO){
            transaction.amount
        } else {
            transaction.amount.multiply(BigDecimal(-1.00))
        }
    }
}