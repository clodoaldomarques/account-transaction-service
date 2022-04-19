package io.pismo.account.transaction.datasources.converters

import io.pismo.account.transaction.datasources.tables.AccountTable
import io.pismo.account.transaction.domain.entity.Account
import java.util.*

fun Account.toTable() : AccountTable {
    return AccountTable(
        accountId = this.accountId,
        documentNumber = this.documentNumber
    )
}

fun AccountTable.toEntity(accountId : UUID = UUID.randomUUID()) : Account {
    return Account(
        accountId = this.accountId,
        documentNumber = this.documentNumber
    )
}