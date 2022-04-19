package io.pismo.account.transaction.transports.api.controllers.models

import java.math.BigDecimal
import java.time.LocalDateTime
import java.util.UUID

data class TransactionResponseModel(
    val transaction_id : UUID,
    val account_id : UUID,
    val operation_type_description : String,
    val amount : BigDecimal,
    val eventDate : LocalDateTime
)