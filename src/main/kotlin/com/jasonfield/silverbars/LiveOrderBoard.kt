package com.jasonfield.silverbars

import java.math.BigDecimal.ZERO

class LiveOrderBoard {
    private val orders = mutableListOf<Order>()

    fun liveOrders(orderType: OrderType): List<OrderSummary> {
        val orderSummary = orders
            .filter { it.orderType == orderType }
            .groupBy { it.price }
            .map { OrderSummary(sumOrderQuantities(it), it.key) }

        return orderType.sort(orderSummary)
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

    private fun sumOrderQuantities(entry: Map.Entry<Int, List<Order>>) =
        entry.value.fold(ZERO) { acc, order -> acc.plus(order.quantity) }
}
