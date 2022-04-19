package io.pismo.account.transaction.domain.interactors

import io.pismo.account.transaction.domain.entity.Account
import java.util.UUID

interface AccountsUseCase {
    fun create(account: Account) : Account
    fun findById(accountId : UUID) : Account
}