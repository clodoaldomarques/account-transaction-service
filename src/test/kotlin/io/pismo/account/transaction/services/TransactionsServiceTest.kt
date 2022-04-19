package io.pismo.account.transaction.services

import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.slot
import io.mockk.verify
import io.pismo.account.transaction.domain.entity.OperationType
import io.pismo.account.transaction.domain.entity.Transaction
import io.pismo.account.transaction.domain.exceptions.GenericBusinessException
import io.pismo.account.transaction.domain.repositories.TransactionPersistence
import io.pismo.account.transaction.utils.buildTransaction
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import java.math.BigDecimal

@ExtendWith(MockKExtension::class)
class TransactionsServiceTest{
    @MockK
    private lateinit var  transactionPersistence: TransactionPersistence
    @InjectMockKs
    private lateinit var transactionsService: TransactionsService

    private var transactionSlot = slot<Transaction>()

    @Test
    fun `should register a buy cash transaction`(){
        val fakeAmount = BigDecimal.TEN
        val fakeTransaction = buildTransaction(operation = OperationType.COMPRA_A_VISTA, amount = fakeAmount)
        every { transactionPersistence.registerTransaction(capture(transactionSlot)) } returns fakeTransaction
        val savedTransaction = transactionsService.registerTransaction(fakeTransaction)
        verify (exactly = 1) { transactionPersistence.registerTransaction(transactionSlot.captured) }
        assertEquals(fakeTransaction, savedTransaction)
        assertEquals(fakeAmount.multiply(BigDecimal(-1.00)), transactionSlot.captured.amount)
    }

    @Test
    fun `should register a buy in installments transaction`(){
        val fakeAmount = BigDecimal.TEN
        val fakeTransaction = buildTransaction(operation = OperationType.COMPRA_PARCELADA, amount = fakeAmount)
        every { transactionPersistence.registerTransaction(capture(transactionSlot)) } returns fakeTransaction
        val savedTransaction = transactionsService.registerTransaction(fakeTransaction)
        verify (exactly = 1) { transactionPersistence.registerTransaction(transactionSlot.captured) }
        assertEquals(fakeTransaction, savedTransaction)
        assertEquals(fakeAmount.multiply(BigDecimal(-1.00)), transactionSlot.captured.amount)
    }

    @Test
    fun `should register a withdraw transaction`(){
        val fakeAmount = BigDecimal.TEN
        val fakeTransaction = buildTransaction(operation = OperationType.SAQUE, amount = fakeAmount)
        every { transactionPersistence.registerTransaction(capture(transactionSlot)) } returns fakeTransaction
        val savedTransaction = transactionsService.registerTransaction(fakeTransaction)
        verify (exactly = 1) { transactionPersistence.registerTransaction(transactionSlot.captured) }
        assertEquals(fakeTransaction, savedTransaction)
        assertEquals(fakeAmount.multiply(BigDecimal(-1.00)), transactionSlot.captured.amount)
    }

    @Test
    fun `should register a payment transaction`(){
        val fakeAmount = BigDecimal.TEN
        val fakeTransaction = buildTransaction(operation = OperationType.PAGAMENTO, amount = fakeAmount)
        every { transactionPersistence.registerTransaction(capture(transactionSlot)) } returns fakeTransaction
        val savedTransaction = transactionsService.registerTransaction(fakeTransaction)
        verify (exactly = 1) { transactionPersistence.registerTransaction(transactionSlot.captured) }
        assertEquals(fakeTransaction, savedTransaction)
        assertEquals(fakeAmount, transactionSlot.captured.amount)
    }

    @Test
    fun `should throw error when a negative payment transaction`(){
        val fakeAmount = BigDecimal(-100.00)
        val fakeTransaction = buildTransaction(operation = OperationType.PAGAMENTO, amount = fakeAmount)
        every { transactionPersistence.registerTransaction(any()) } returns fakeTransaction
        val error = assertThrows<GenericBusinessException>{
            transactionsService.registerTransaction(fakeTransaction)
        }
        assertEquals("Value cannot be less than zero", error.message)
    }

}