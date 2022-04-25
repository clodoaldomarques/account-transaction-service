package io.pismo.account.transaction.datasources.tables

import io.pismo.account.transaction.domain.entity.Account
import io.pismo.account.transaction.domain.entity.OperationType
import java.math.BigDecimal
import java.time.LocalDateTime
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "transaction")
data class TransactionTable (
    @Id
    val transactionId: UUID,

    @ManyToOne
    @JoinColumn(name = "account_id")
    val account : AccountTable,

    @Enumerated(EnumType.STRING)
    val operationType : OperationType,

    @Column(name = "amount", nullable = false)
    val amount : BigDecimal,

    @Column(name = "event_date")
    val eventDate : LocalDateTime

)