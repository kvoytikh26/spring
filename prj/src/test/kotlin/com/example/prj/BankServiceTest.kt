package com.example.prj

import com.app.model.TransferBalance
import com.app.repository.BalanceRepository
import com.app.service.BankService
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.math.BigDecimal

class BankServiceTest {
    private val balanceRepository: BalanceRepository =  BalanceRepository()
    private val bankService: BankService = BankService(balanceRepository)

    @Test
    fun addBalance() {
        val balance = bankService.getBalance(1L)
        Assertions.assertEquals(balance, BigDecimal.TEN)
    }

    @Test
    fun addMoney() {
        val transferBalance = TransferBalance()
        transferBalance.amount = BigDecimal.ONE
        transferBalance.to = 1L

        val balance = bankService.addMoney(transferBalance)
        Assertions.assertEquals(balance, BigDecimal.valueOf(11))
    }
}