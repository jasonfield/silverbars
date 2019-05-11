package com.jasonfield.silverbars

import com.jasonfield.silverbars.OrderType.Buy
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.math.BigDecimal

internal class LiveOrderBoardTest {
    private val board = LiveOrderBoard()

    @Test
    internal fun `can list a registered order`() {
        val sampleOrder = Order("user1", BigDecimal("1.23"), 100, Buy)

        board.register(sampleOrder)

        val orders = board.liveOrders()

        assertThat(orders).containsExactly(sampleOrder)
    }

    @Test
    internal fun `can cancel an order`() {
        val sampleOrder = Order("user1", BigDecimal("1.23"), 100, Buy)

        board.register(sampleOrder)
        board.cancel(sampleOrder)

        val orders = board.liveOrders()

        assertThat(orders).isEmpty()
    }
}