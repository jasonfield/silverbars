# Silver Bars Submission

## How to build and run the tests

The solution is build using Gradle 5.4.1 and includes the Gradle wrapper files so it should be possible to run the 
following, assuming that there are no issues with corporate proxies:

    gradlew test
    
## Assumptions

 * I have assumed from the test script that order quantities are in integer amounts of kilograms
 * I made a decision to represent the order quantities as BigDecimals to avoid potential rounding issues with using e.g. 
    floats or doubles. 