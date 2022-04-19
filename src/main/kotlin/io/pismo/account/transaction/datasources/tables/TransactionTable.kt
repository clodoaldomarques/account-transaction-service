package io.pismo.account.transaction.datasources.tables

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

    @Column(name = "account_id", nullable = false)
    val accountId : UUID,

    @Enumerated(EnumType.STRING)
    val operationType : OperationType,

    @Column(name = "amount", nullable = false)
    val amount : BigDecimal,

    @Column(name = "event_date")
    val eventDate : LocalDateTime

)