package io.pismo.account.transaction.domain.entity

import java.math.BigDecimal
import java.util.UUID

data class Account(val accountId : UUID, val documentNumber: String, val availableCreditLimit : BigDecimal = BigDecimal.ZERO)