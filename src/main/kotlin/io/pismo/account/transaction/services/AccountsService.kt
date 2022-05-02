package io.pismo.account.transaction.services

import io.pismo.account.transaction.domain.entity.Account
import io.pismo.account.transaction.domain.exceptions.ResourceNotFoundException
import io.pismo.account.transaction.domain.interactors.AccountsUseCase
import io.pismo.account.transaction.domain.repositories.AccountPersistence
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.util.*

@Service
class AccountsService(private val accountPersistence: AccountPersistence) : AccountsUseCase {

    companion object {
        private val logger = LoggerFactory.getLogger(TransactionsService::class.java)
    }

    override fun create(account: Account): Account {
        logger.info("Criando a conta do cliente")
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