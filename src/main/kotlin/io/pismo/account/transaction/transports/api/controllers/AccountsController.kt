package io.pismo.account.transaction.transports.api.controllers

import io.pismo.account.transaction.domain.interactors.AccountsUseCase
import io.pismo.account.transaction.transports.api.controllers.models.AccountResponseModel
import io.pismo.account.transaction.transports.api.controllers.models.PostAccountRequestModel
import io.pismo.account.transaction.transports.api.controllers.openapis.AccountsOpenApi
import io.pismo.account.transaction.transports.api.converters.toEntity
import io.pismo.account.transaction.transports.api.converters.toModel
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
@RequestMapping("/accounts")
class AccountsController(val accountService: AccountsUseCase) : AccountsOpenApi {

    @GetMapping("/{accountId}")
    override fun findById(@PathVariable accountId: UUID): ResponseEntity<AccountResponseModel> {
        return ResponseEntity.ok(accountService.findById(accountId).toModel())
    }

    @PostMapping
    override fun insert(@RequestBody model: PostAccountRequestModel): ResponseEntity<AccountResponseModel> {
        return ResponseEntity.ok(accountService.create(model.toEntity()).toModel())
    }
}