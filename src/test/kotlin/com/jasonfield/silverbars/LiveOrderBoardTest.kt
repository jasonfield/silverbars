package com.jasonfield.silverbars

import com.jasonfield.silverbars.OrderType.Buy
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.math.BigDecimal

internal class LiveOrderBoardTest {
    private val board = LiveOrderBoard()

    @Test
    internal fun `can list a registered order`() {
        val sampleOrder = Order("user1", BigDecimal("1.23"), 100, Buy)

        board.register(sampleOrder)

        val orders = board.liveOrders()

        assertThat(orders).containsExactly(OrderSummary(BigDecimal("1.23"), 100))
    }

    @Test
    internal fun `can cancel an order`() {
        val sampleOrder = Order("user1", BigDecimal("1.23"), 100, Buy)

        board.register(sampleOrder)
        board.cancel(sampleOrder)

        val orders = board.liveOrders()

        assertThat(orders).isEmpty()
    }

    @Test
    internal fun `cancelling an order which does not exist throws an exception`() {
        val unknownOrder = Order("user1", BigDecimal("1.23"), 100, Buy)

        val exception = assertThrows<OrderNotFound> {
            board.cancel(unknownOrder)
        }

        assertThat(exception.message).isEqualTo("Could not find a registered order matching $unknownOrder")
    }
}