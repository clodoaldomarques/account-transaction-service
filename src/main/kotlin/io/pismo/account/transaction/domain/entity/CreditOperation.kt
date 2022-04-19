package io.pismo.account.transaction.domain.entity

import io.pismo.account.transaction.domain.exceptions.GenericBusinessException
import java.math.BigDecimal

@Suppress("UNREACHABLE_CODE")
class CreditOperation : OperationRules {
    override fun convertAmountValue(transaction: Transaction): BigDecimal {
        return if (transaction.amount < BigDecimal.ZERO){
            throw GenericBusinessException("Value cannot be less than zero")
        } else {
            return transaction.amount
        }
    }
}