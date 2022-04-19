package io.pismo.account.transaction.transports.api.controllers.models

import java.util.UUID

data class AccountResponseModel(val account_id : UUID, val document_number : String)