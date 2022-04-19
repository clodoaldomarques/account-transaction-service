package io.pismo.account.transaction.services

import io.pismo.account.transaction.domain.entity.Account
import io.pismo.account.transaction.domain.exceptions.ResourceNotFoundException
import io.pismo.account.transaction.domain.interactors.AccountsUseCase
import io.pismo.account.transaction.domain.repositories.AccountPersistence
import org.springframework.stereotype.Service
import java.util.*

@Service
class AccountsService(private val accountPersistence: AccountPersistence) : AccountsUseCase {
    override fun create(account: Account): Account {
        return accountPersistence.create(account)
    }

    override fun findById(accountId: UUID): Account {
        val optional = accountPersistence.findById(accountId)
        return if (optional.isPresent){
            optional.get()
        } else {
            throw ResourceNotFoundException("Account not found")
        }
    }
}