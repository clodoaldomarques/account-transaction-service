package io.pismo.account.transaction.services

import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.slot
import io.mockk.verify
import io.pismo.account.transaction.domain.entity.Account
import io.pismo.account.transaction.domain.exceptions.ResourceNotFoundException
import io.pismo.account.transaction.domain.repositories.AccountPersistence
import io.pismo.account.transaction.utils.buildAccount
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import java.util.*
import kotlin.NoSuchElementException

@ExtendWith(MockKExtension::class)
class AccountsServiceTest{
    @MockK
    private lateinit var  accountPersistence: AccountPersistence
    @InjectMockKs
    private lateinit var accountsService: AccountsService

    private var accountSlot = slot<Account>()

    @Test
    fun `should return a Account by id`() {
        val fakeAccountId = UUID.randomUUID()
        val fakeAccount = buildAccount(accountId = fakeAccountId)
        every { accountPersistence.findById(fakeAccountId) } returns Optional.of(fakeAccount)
        val savedAccount = accountsService.findById(fakeAccountId)
        assertEquals(fakeAccount, savedAccount)
        verify (exactly = 1) { accountPersistence.findById(fakeAccountId) }
    }

    @Test
    fun `should create a Account`(){
        val fakeAccountId = UUID.randomUUID()
        val fakeAccount = buildAccount(accountId = fakeAccountId)
        every { accountPersistence.create(fakeAccount) } returns fakeAccount
        val savedAccount = accountsService.create(fakeAccount)
        assertEquals(fakeAccount, savedAccount)
        verify (exactly = 1) { accountPersistence.create(fakeAccount) }
    }

    @Test
    fun `should throw error not found account when find by accountId`() {
        val fakeAccountId = UUID.randomUUID()
        every { accountPersistence.findById(fakeAccountId) } returns Optional.empty()
        val error = assertThrows<ResourceNotFoundException>{
            accountsService.findById(fakeAccountId)
        }
        assertEquals("Account not found", error.message)
    }

}