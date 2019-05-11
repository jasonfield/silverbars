package com.jasonfield.silverbars

import com.jasonfield.silverbars.OrderType.Sell
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.math.BigDecimal

class LiveOrderBoardAcceptanceTest {
    private val board = LiveOrderBoard()

    @Test
    fun `example from the exercise script`() {
        board.register(Order("user1", BigDecimal("3.5"), 306, Sell))
        board.register(Order("user2", BigDecimal("1.2"), 310, Sell))
        board.register(Order("user3", BigDecimal("1.5"), 307, Sell))
        board.register(Order("user3", BigDecimal("2.0"), 306, Sell))

        val orders = board.liveOrders(Sell)

        assertThat(orders).containsExactly(
            OrderSummary(BigDecimal("5.5"), 306),
            OrderSummary(BigDecimal("1.5"), 307),
            OrderSummary(BigDecimal("1.2"), 310)
        )
    }
}