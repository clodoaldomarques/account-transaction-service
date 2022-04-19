package io.pismo.account.transaction.transports.api.controllers.models

import java.math.BigDecimal
import java.util.UUID
import javax.validation.constraints.Min
import javax.validation.constraints.NotNull

data class PostTransactionRequestModel(
    @field:NotNull(message = "Account Id required")
    val account_id : UUID,

    @field:NotNull(message = "Operation Type Id required")
    val operation_type_id : Int,

    @Min(message = "The amount cannot be less than zero", value = 0)
    @field:NotNull(message = "Amount required")
    val amount : BigDecimal
)