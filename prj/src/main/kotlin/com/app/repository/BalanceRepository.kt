package com.app.repository

import org.springframework.stereotype.Repository
import java.math.BigDecimal

@Repository
class BalanceRepository {
    private var storage = mutableMapOf<Long, BigDecimal?>(1L to BigDecimal.TEN)

    fun getBalanceForID(accountId: Long): BigDecimal? {
        return storage[accountId]
    }

    fun save(id: Long, amount: BigDecimal?) {
        storage.put(id, amount)
    }
}