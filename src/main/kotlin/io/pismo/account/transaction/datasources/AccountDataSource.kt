package io.pismo.account.transaction.datasources

import io.pismo.account.transaction.datasources.converters.toEntity
import io.pismo.account.transaction.datasources.converters.toTable
import io.pismo.account.transaction.datasources.repositories.AccountRepository
import io.pismo.account.transaction.domain.entity.Account
import io.pismo.account.transaction.domain.repositories.AccountPersistence
import org.springframework.stereotype.Component
import org.springframework.stereotype.Repository
import java.util.*

@Component
class AccountDataSource (private val accountRepository: AccountRepository) : AccountPersistence {
    override fun create(account: Account): Account {
        return accountRepository.save(account.toTable()).toEntity()
    }

    override fun findById(accountId: UUID): Optional<Account> {
        val optional = accountRepository.findById(accountId)
        return if (optional.isPresent) {
            Optional.of(optional.get().toEntity())
        } else {
            Optional.empty()
        }
    }

    override fun update(account: Account): Account {
        return accountRepository.save(account.toTable()).toEntity()
    }
}