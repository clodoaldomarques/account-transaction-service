package io.pismo.account.transaction.datasources.converters

import io.pismo.account.transaction.datasources.tables.AccountTable
import io.pismo.account.transaction.domain.entity.Account
import java.util.*

fun Account.toTable() : AccountTable {
    return AccountTable(
        accountId = this.accountId,
        documentNumber = this.documentNumber,
        availableCreditLimit = this.availableCreditLimit
    )
}

fun AccountTable.toEntity() : Account {
    return Account(
        accountId = this.accountId,
        documentNumber = this.documentNumber,
        availableCreditLimit = this.availableCreditLimit
    )
}