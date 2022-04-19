package io.pismo.account.transaction.datasources.repositories

import io.pismo.account.transaction.datasources.tables.AccountTable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface AccountRepository : JpaRepository<AccountTable, UUID> {
}