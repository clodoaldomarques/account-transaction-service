package io.pismo.account.transaction.transports.api.controllers

import io.pismo.account.transaction.domain.interactors.TransactionsUseCase
import io.pismo.account.transaction.transports.api.controllers.models.PostTransactionRequestModel
import io.pismo.account.transaction.transports.api.controllers.models.TransactionResponseModel
import io.pismo.account.transaction.transports.api.controllers.openapis.TransactionsOpenApi
import io.pismo.account.transaction.transports.api.converters.toEntity
import io.pismo.account.transaction.transports.api.converters.toModel
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/transations")
class TransactionsController(private val transactionsUseCase: TransactionsUseCase) : TransactionsOpenApi {
    @PostMapping
    override fun registerTransaction(@RequestBody model: PostTransactionRequestModel): ResponseEntity<TransactionResponseModel> {
        val newTransaction = transactionsUseCase.buildTransaction(model.account_id, model.operation_type_id, model.amount)
        return ResponseEntity.ok(transactionsUseCase.registerTransaction(newTransaction).toModel())
    }
}