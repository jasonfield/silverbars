package com.jasonfield.silverbars

class OrderNotFound(unknownOrder: Order) : RuntimeException("Could not find a registered order matching $unknownOrder")
