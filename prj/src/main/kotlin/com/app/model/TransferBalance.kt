package com.app.model

import org.springframework.stereotype.Component
import java.math.BigDecimal

@Component
class TransferBalance {
    var from: Long = 0
    var to: Long = 0
    var amount: BigDecimal? = null
}