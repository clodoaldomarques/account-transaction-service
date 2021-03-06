package io.pismo.account.transaction.transports.api.controllers.models

import java.math.BigDecimal
import javax.validation.constraints.NotBlank

data class PostAccountRequestModel(
    @field:NotBlank(message = "Document Number required")
    val document_number: String,
    val availableCreditLimit: BigDecimal
)