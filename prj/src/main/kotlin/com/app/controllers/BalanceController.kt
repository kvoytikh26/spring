package com.app.controllers

import com.app.model.TransferBalance
import com.app.service.BankService
import lombok.extern.slf4j.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import java.math.BigDecimal

@RestController
@RequestMapping("/balance")
class BalanceController (
    @Autowired
    val bankService: BankService
) {

    @GetMapping("/{accountId}")
    fun getBalance(@PathVariable accountId: Long): Any = bankService.getBalance(accountId)

    @PostMapping("/{accountId}")
    fun addMoney (@RequestBody transferBalance: TransferBalance): BigDecimal? {
        return bankService.addMoney(transferBalance)
    }

    @PostMapping("/transfer")
    fun transfer(@RequestBody transferBalance: TransferBalance): String {
        return bankService.makeTransfer(transferBalance)
    }

    @ExceptionHandler(IllegalArgumentException::class)
    fun handle(e: IllegalArgumentException) : String? {
        return e.message
    }
}