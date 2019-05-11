package com.jasonfield.silverbars

import java.math.BigDecimal

data class OrderSummary(
    val quantity: BigDecimal,
    val price: Int
)