package com.jasonfield.silverbars

enum class OrderType {
    Buy,
    Sell;

    fun sort(summaries: List<OrderSummary>): List<OrderSummary> {
        return when (this) {
            Buy -> summaries.sortedByDescending { it.price }
            Sell -> summaries.sortedBy { it.price }
        }
    }
}