package io.pismo.account.transaction.domain.entity

import java.util.UUID

data class Account(val accountId : UUID, val documentNumber: String)