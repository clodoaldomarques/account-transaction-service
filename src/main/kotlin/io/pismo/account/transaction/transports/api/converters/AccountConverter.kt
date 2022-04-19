package io.pismo.account.transaction.transports.api.converters

import io.pismo.account.transaction.domain.entity.Account
import io.pismo.account.transaction.transports.api.controllers.models.AccountResponseModel
import io.pismo.account.transaction.transports.api.controllers.models.PostAccountRequestModel
import java.util.UUID

fun Account.toModel() : AccountResponseModel{
    return AccountResponseModel(this.accountId, this.documentNumber)
}

fun PostAccountRequestModel.toEntity(accountId : UUID = UUID.randomUUID()) : Account{
    return Account(accountId, this.document_number)
}