package io.pismo.account.transaction.datasources.repositories

import io.pismo.account.transaction.datasources.tables.TransactionTable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface TransactionRepository : JpaRepository<TransactionTable, UUID> {
}