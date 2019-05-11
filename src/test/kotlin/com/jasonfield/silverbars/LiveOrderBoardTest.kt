package com.jasonfield.silverbars

import com.jasonfield.silverbars.OrderType.Buy
import com.jasonfield.silverbars.OrderType.Sell
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.math.BigDecimal

class LiveOrderBoardTest {
    private val board = LiveOrderBoard()

    @Test
    fun `can list a registered order`() {
        val sampleOrder = Order("user1", BigDecimal("1.23"), 100, Buy)

        board.register(sampleOrder)

        val orders = board.liveOrders(Buy)

        assertThat(orders).containsExactly(OrderSummary(BigDecimal("1.23"), 100))
    }

    @Test
    fun `can cancel an order`() {
        val sampleOrder = Order("user1", BigDecimal("1.23"), 100, Buy)

        board.register(sampleOrder)
        board.cancel(sampleOrder)

        val orders = board.liveOrders(Buy)

        assertThat(orders).isEmpty()
    }

    @Test
    fun `cancelling an order which does not exist throws an exception`() {
        val unknownOrder = Order("user1", BigDecimal("1.23"), 100, Buy)

        val exception = assertThrows<OrderNotFound> {
            board.cancel(unknownOrder)
        }

        assertThat(exception.message).isEqualTo("Could not find a registered order matching $unknownOrder")
    }

    @Test
    fun `orders for the same price are merged when listing the orders`() {
        val order1 = Order("user1", BigDecimal("1.2"), 100, Buy)
        val order2 = Order("user2", BigDecimal("3.3"), 150, Buy)
        val order3 = Order("user3", BigDecimal("4.21"), 100, Buy)

        board.register(order1)
        board.register(order2)
        board.register(order3)

        val orders = board.liveOrders(Buy)

        assertThat(orders).containsExactly(
            OrderSummary(BigDecimal("5.41"), 100),
            OrderSummary(BigDecimal("3.3"), 150)
        )
    }

    @Test
    fun `buy and sell orders are independent`() {
        val order1 = Order("user1", BigDecimal("1.2"), 100, Buy)
        val order2 = Order("user2", BigDecimal("3.3"), 150, Buy)
        val order3 = Order("user3", BigDecimal("4.21"), 100, Sell)

        board.register(order1)
        board.register(order2)
        board.register(order3)

        val buyOrders = board.liveOrders(Buy)

        assertThat(buyOrders).containsExactly(
            OrderSummary(BigDecimal("1.2"), 100),
            OrderSummary(BigDecimal("3.3"), 150)
        )

        val sellOrders = board.liveOrders(Sell)

        assertThat(sellOrders).containsExactly(
            OrderSummary(BigDecimal("4.21"), 100)
        )
    }

    @Test
    fun `sell orders are returned lowest price first`() {
        board.register(Order("user1", BigDecimal("1"), 100, Sell))
        board.register(Order("user2", BigDecimal("2"), 80, Sell))
        board.register(Order("user3", BigDecimal("3"), 120, Sell))
        board.register(Order("user3", BigDecimal("4"), 60, Sell))

        val orders = board.liveOrders(Sell)

        assertThat(orders).containsExactly(
            OrderSummary(BigDecimal("4"), 60),
            OrderSummary(BigDecimal("2"), 80),
            OrderSummary(BigDecimal("1"), 100),
            OrderSummary(BigDecimal("3"), 120)
        )
    }
}