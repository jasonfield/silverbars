package com.jasonfield.silverbars

import java.math.BigDecimal

data class Order(
    val userId: String,
    val quantity: BigDecimal,
    val price: Int,
    val orderType: OrderType
)