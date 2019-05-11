package com.jasonfield.silverbars

class LiveOrderBoard {
    private val orders = mutableListOf<Order>()

    fun liveOrders(): List<Order> {
        return emptyList()
    }

    fun register(order: Order) {
        orders.add(order)
    }
}