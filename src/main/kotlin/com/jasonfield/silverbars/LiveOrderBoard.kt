package com.jasonfield.silverbars

class LiveOrderBoard {
    private val orders = mutableListOf<Order>()

    fun liveOrders(): List<Order> {
        return orders
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