package io.pismo.account.transaction.domain.entity

import java.math.BigDecimal

interface OperationRules {
    fun convertAmountValue(transaction: Transaction) : BigDecimal
}