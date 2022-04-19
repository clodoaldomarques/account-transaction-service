package io.pismo.account.transaction.domain.repositories

import io.pismo.account.transaction.domain.entity.Account
import java.util.Optional
import java.util.UUID

interface AccountPersistence {
    fun create(account: Account) : Account
    fun findById(accountId : UUID) : Optional<Account>
}