package io.pismo.account.transaction.datasources.tables

import java.util.UUID
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "account")
data class AccountTable(
    @Id
    val accountId : UUID,

    @Column(name = "documentNumber", nullable = false)
    val documentNumber: String
)