# Silver Bars Submission

Thanks for taking the time to review my submission. I have developed it in the way I would normally approach writing 
code - TDD, and generally trying to keep things simple and succinct. I used Kotlin as it is what I have mostly been
writing in recently. If you would prefer a pure Java implementation then please let me know.

## How to build and run the tests

The solution is build using Gradle 5.4.1 and includes the Gradle wrapper files so it should be possible to run the 
following, assuming that there are no issues with corporate proxies:

    gradlew test
    
## Assumptions

 * Order prices are in integer amounts of pounds sterling and that we do not need to worry about supporting multiple currencies .
 * It's OK for the same user to submit an order for the same price and quantity, and that it should be treated as a separate order.
 * The incoming order data has already been validated so we don't need to worry about e.g. negative prices and quantities

## Design choices

 * Order quantities are represented as BigDecimals to avoid potential rounding issues with using e.g. floats or doubles.
 * I put the buy/sell sort ordering with the OrderType enum to ensure that adding any new order types will require the 
    developer to make a decision on how it will be sorted.