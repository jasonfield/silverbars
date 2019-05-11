package com.jasonfield.silverbars

import java.math.BigDecimal.ZERO

class LiveOrderBoard {
    private val orders = mutableListOf<Order>()

    fun liveOrders(orderType: OrderType): List<OrderSummary> {
        return orders
            .filter { it.orderType == orderType }
            .groupBy { it.price }
            .map {
                val summedQuantity = it.value.fold(ZERO) { acc, order -> acc.plus(order.quantity) }
                OrderSummary(summedQuantity, it.key)
            }
    }

    fun register(order: Order) {
        orders.add(order)
    }

    fun cancel(order: Order) {
        if (!orders.contains(order)) {
            throw OrderNotFound(order)
        }

        orders.remove(order)
    }
}