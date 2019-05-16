# Unit Testing Documentation template

Authors:
Date:
Version:

# Contents

- [Black Box Unit Tests](#black-box-unit-tests)

- [White Box Unit Tests](#white-box-unit-tests)

# Black Box Unit Tests

### Class Beverage

**Criteria for method increaseAvailableCapsules:**
 - Sign of numberOfBoxes
 - Overflow of availableCapsules

**Predicates for method increaseAvailableCapsules:**

| Criteria | Predicate |
| -------- | --------- |
| Sign of numberOfBoxes |      >= 0     |
|          |     < 0      |
|    Overflow of availableCapsules      |     availableCapsules + numberOfBoxes * capsulesPerBox > MAXINT      |
|          |    availableCapsules + numberOfBoxes * capsulesPerBox <= MAXINT        |

**Boundaries for method increaseAvailableCapsules:**

| Criteria | Boundary values |
| -------- | --------------- |
|   Sign of numberOfBoxes       |    MININT, 0, MAXINT             |
|     Overflow of availableCapsules      |     availableCapsules + numberOfBoxes * capsulesPerBox = MAXINT      |   

**Combination of predicates for method increaseAvailableCapsules:**

| Sign of numberOfBoxes | Overflow of availableCapsules | Valid / Invalid | Description of the test case | JUnit test case |
|-------|-------|-------|-------|-------|
| >= 0 | no | V | Beverage b = new Beverage(1, "Coffee", 500, 20); b.increaseAvailableCapsules(2); | it.polito.latazza.data.TestBeverage.tc1 |
|  | yes | I | Beverage b = new Beverage(1, "Coffee", 500, 20); b.increaseAvailableCapsules(10); b.increaseAvailableCapsules((Integer.MAX_VALUE - 100) / 20);| it.polito.latazza.data.TestBeverage.tc2 |
| < 0 | no | I |Beverage b = new Beverage(1, "Coffee", 500, 20); b.increaseAvailableCapsules(-10);| it.polito.latazza.data.TestBeverage.tc3 |
|  | yes | / ||  |

**Criteria for method decreaseAvailableCapsules:**
 - Sign of numberOfCapsules
 - Enough availableCapsules
 - Overflow of availableCapsules

**Predicates for method decreaseAvailableCapsules:**

| Criteria | Predicate |
| -------- | --------- |
| Sign of numberOfCapsules |      >= 0     |
|          |     < 0      |
| Enough availableCapsules | availableCapsules >= numberOfCapsules |
|   | availableCapsules < numberOfCapsules |
|    Overflow of availableCapsules      |     availableCapsules - numberOfCapsules > MAXINT      |
|          |    availableCapsules - numberOfCapsules <= MAXINT        |

**Boundaries for method decreaseAvailableCapsules:**:

| Criteria | Boundary values |
| -------- | --------------- |
|   Sign of numberOfCapsules   |    MININT, 0, MAXINT             |
|     Overflow of availableCapsules      |     availableCapsules + numberOfBoxes * capsulesPerBox = MAXINT      |   

**Combination of predicates for method decreaseAvailableCapsules:**:

| Sign of numberOfCapsules | Enough availableCapsules | Overflow of availableCapsules | Valid / Invalid | Description of the test case | JUnit test case |
|-------|-------|-------|-------|-------|-------|
| >= 0 | yes | no | V | Beverage b = new Beverage(1, "Coffee", 500, 20); b.increaseAvailableCapsules(2); b.decreaseAvailableCapsules(10);| it.polito.latazza.data.TestBeverage.tc4 |
| |  | yes | / || |
| | no | no | I |Beverage b = new Beverage(1, "Coffee", 500, 20); b.increaseAvailableCapsules(2); b.decreaseAvailableCapsules(50);| it.polito.latazza.data.TestBeverage.tc5 |
| | | yes | / || |
| < 0 | yes | no | I | Beverage b = new Beverage(1, "Coffee", 500, 20); b.increaseAvailableCapsules(2); b.decreaseAvailableCapsules(-5);| it.polito.latazza.data.TestBeverage.tc6 |
| | | yes | I | Beverage b = new Beverage(1, "Coffee", 500, 20); b.increaseAvailableCapsules(2); b.decreaseAvailableCapsules(- (Integer.MAX_VALUE - 20));| it.polito.latazza.data.TestBeverage.tc7 |
| | no | no | / |
| | | yes | / |

### Class Recharge

**Criteria for constructor:**
 - Sign of amount
 - Valid employee

**Predicates for constructor:**

| Criteria | Predicate |
| -------- | --------- |
| Sign of amount |      >= 0     |
|          |     < 0      |
|    Valid employee      |    not null      |
|          | null       |

**Boundaries for constructor:**

| Criteria | Boundary values |
| -------- | --------------- |
|   Sign of amount  |    MININT, 0, MAXINT   |

**Combination of predicates for constructor:**

| Sign of amount | Valid employee | Valid / Invalid | Description of the test case | JUnit test case |
|-------|-------|-------|-------|-------|
| >= 0 | yes | V |Recharge(emp, 1000)|it.polito.latazza.data.TestRecharge.tc1|
|  | no | I |Recharge(null, 1000)|it.polito.latazza.data.TestRecharge.tc2|
| < 0 | yes | V |Recharge(emp, -1000)|it.polito.latazza.data.TestRecharge.tc3|
|  | no | I |Recharge(null, -1000)|it.polito.latazza.data.TestRecharge.tc4|

# White Box Unit Tests

### Test cases definition

| Unit name | JUnit test case                              |
| --------- | -------------------------------------------- |
| Beverage | it.polito.latazza.data.<br />Beverage.tcConstructor |
| Beverage | it.polito.latazza.data.<br />Beverage.tcGetters |
| Beverage | it.polito.latazza.data.<br />Beverage.tcSetters |
| Recharge | it.polito.latazza.data.<br />Recharge.tcToStringFormat |
