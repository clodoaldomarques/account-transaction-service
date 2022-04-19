package io.pismo.account.transaction.transports.api.controllers.openapis

import io.pismo.account.transaction.tranports.api.advice.MessageException
import io.pismo.account.transaction.transports.api.controllers.models.PostTransactionRequestModel
import io.pismo.account.transaction.transports.api.controllers.models.TransactionResponseModel
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity

interface TransactionsOpenApi {
    companion object {
        private const val apiDescription: String = "API Transaction version 1.0.0"
    }

    @Operation(summary = "Register a transaction", description = "", tags = [TransactionsOpenApi.apiDescription])
    @ApiResponses(
        value = [ApiResponse(
            responseCode = "200",
            description = "Success",
            content = [Content(mediaType = MediaType.APPLICATION_JSON_VALUE)]
        ), ApiResponse(
            responseCode = "400",
            description = "Bad Request",
            content = [Content(schema = Schema(implementation = MessageException::class))]
        ), ApiResponse(
            responseCode = "401",
            description = "Unauthorized",
            content = [Content(schema = Schema(implementation = MessageException::class))]
        ), ApiResponse(
            responseCode = "409",
            description = "Conflict",
            content = [Content(schema = Schema(implementation = MessageException::class))]
        ), ApiResponse(
            responseCode = "500",
            description = "Internal Server Error",
            content = [Content(schema = Schema(implementation = MessageException::class))]
        )]
    )
    fun registerTransaction( model: PostTransactionRequestModel): ResponseEntity<TransactionResponseModel>
}