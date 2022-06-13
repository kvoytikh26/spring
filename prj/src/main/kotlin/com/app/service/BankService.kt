package com.app.service

import com.app.model.TransferBalance
import com.app.repository.BalanceRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.math.BigDecimal

@Service
class BankService (
    @Autowired
    val balanceRepository: BalanceRepository
) {
    fun addMoney(transferBalance: TransferBalance): BigDecimal? {
        val currentBalance: BigDecimal? =
            balanceRepository.getBalanceForID(transferBalance.to)

        return if (currentBalance == null) {
            balanceRepository.save(transferBalance.to, transferBalance.amount)
            transferBalance.amount
        } else {
            val updatedBalance = currentBalance.add(transferBalance.amount)
            balanceRepository.save(transferBalance.to, updatedBalance)
            updatedBalance
        }

    }

    fun getBalance(accountId: Long): Any {
        return balanceRepository.getBalanceForID(accountId)
            ?: throw IllegalArgumentException("There is no balance with id=$accountId")
    }

    fun makeTransfer(transferBalance: TransferBalance): String {
        val fromBalance: BigDecimal? = balanceRepository.getBalanceForID(transferBalance.from)
        val toBalance:BigDecimal?  = balanceRepository.getBalanceForID(transferBalance.to)

        if (fromBalance == null || toBalance == null)
            throw IllegalArgumentException("There is no such of that balances")

        if ((transferBalance.amount?.compareTo(fromBalance) ?: 0) > 0) {
            throw IllegalArgumentException("No Enough money")
        }

        val updatedFromBalance = fromBalance.subtract(transferBalance.amount)
        val updatedToBalance = transferBalance.amount?.let { toBalance.plus(it) }

        balanceRepository.save(transferBalance.from, updatedFromBalance)
        balanceRepository.save(transferBalance.to, updatedToBalance)

        return "Successfully";
    }

}