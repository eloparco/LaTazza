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


### Class BoxPurchase

**Criteria for constructor:**

- Valid beverage
- Sign of boxQuantity

**Predicates for method constructor:**

| Criteria            | Predicate                  |
| ------------------- | -------------------------- |
| Valid beverage  | yes |
|                    | no  |
| Sign of boxQuantity  | >= 0 |
|                    | < 0  |

**Boundaries**:

| Criteria       | Boundary values   |
| -------------- | ----------------- |
| Sign of boxQuantity | MININT, 0, MAXINT |

**Combination of predicates**:

| Valid beverage | Sign of boxQuantity| Valid / Invalid | Description of the test case | JUnit test case                         |
| -------------- | --------------- | ---------------------------- | --------------------------------------- | ---|
| yes     |>= 0    |V               |beverage b = new beverage(1, tea, 10, 10); new boxPurchase(b, 10);        | it.polito.latazza.data.BoxPurchase.tc1 |
|     |< 0            | I               | beverage b = new beverage(1, tea, 10, 10); new boxPurchase(b, -1);  | it.polito.latazza.data.BoxPurchase.tc2 |
| no    |>= 0    |I              |new boxPurchase(null, 10);        | it.polito.latazza.data.BoxPurchase.tc3 |
|     |< 0            | I               | |

### Class LaTazzaAccount

**Criteria for increaseBalance:**

- Sign of amount
- Overflow of balance

**Predicates for method increaseBalance:**

| Criteria            | Predicate                  |
| ------------------- | -------------------------- |
| Sign of amount  | amount >= 0 |
|                    | amount  < 0  |
| Overflow of balance  | balance + amount > MAXINT |
|                    | amount  <= MAXINT  |

**Boundaries**:

| Criteria       | Boundary values   |
| -------------- | ----------------- |
| Sign of amount | MININT, 0, MAXINT |

**Combination of predicates**:

| Sign of amount | Overflow of balance| Valid / Invalid | Description of the test case | JUnit test case                         |
| -------------- | --------------- | ---------------------------- | --------------------------------------- | ---|
| >= 0      |no    |V               | increaseBalance(10)          | it.polito.latazza.data.TestLaTazzaAccount.tc1 |
|     |yes            | I               | increaseBalance(2000000000); increaseBalance(2000000000);        | it.polito.latazza.data.TestLaTazzaAccount.tc2 |
| < 0      |no    |I               | increaseBalance(-10)          | it.polito.latazza.data.TestLaTazzaAccount.tc3 |
|     |yes            | I               |        | |

**Criteria for decreaseBalance:**

- Enough balance
- Sign of amount
- Overflow of balance

**Predicates for method decreaseBalance:**

| Criteria            | Predicate                  |
| ------------------- | -------------------------- |
| Enough balance  | balance >= amount |
|                    | balance >= amount   |
| Sign of amount  | amount >= 0 |
|                    | amount  < 0  |

**Boundaries**:

| Criteria       | Boundary values   |
| -------------- | ----------------- |
| Salue of amount | MININT, 0, MAXINT |

**Combination of predicates**:

| Sign of amount | Enough balance| Valid / Invalid | Description of the test case | JUnit test case                         |
| -------------- | --------------- | ---------------------------- | --------------------------------------- | ---|
| >= 0      |yes    |V               | increaseBalance(10); decreaseBalance(10)          | it.polito.latazza.data.TestLaTazzaAccount.tc4 |
|     |no            | I               | decreaseBalance(1);       | it.polito.latazza.data.TestLaTazzaAccount.tc5 |
| < 0      |yes   |I               | decreaseBalance(-10)          | it.polito.latazza.data.TestLaTazzaAccount.tc3 |
|     |no            | I               |        | |


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

### Class DataImpl

**Criteria for method getBeverageName:**
 - Sign of id
 - Valid beverage (existing)

**Predicates for method getBeverageName:**

| Criteria | Predicate |
| -------- | --------- |
| Sign of id |      >= 0     |
|          |     < 0      |
|    Valid beverage      |     yes      |
|          |    no       |

**Boundaries for method getBeverageName:**

| Criteria | Boundary values |
| -------- | --------------- |
|   Sign of numberOfBoxes       |    MININT, 0, MAXINT             |

**Combination of predicates for method getBeverageName:**

| Sign of id | Valid beverage | Valid / Invalid | Description of the test case | JUnit test case |
|-------|-------|-------|-------|-------|
| >= 0 | yes | V | beverageId = data.createBeverage("Coffee", 20, 500);<br />data.getBeverageName(beverageId); | it.polito.latazza.data.<br />TestDataImpl.testGetBeverageName1 |
|  | no | I | DataInterface data = new DataImpl();<br />data.reset();<br />data.getBeverageName(10); | it.polito.latazza.data.<br />TestDataImpl.testGetBeverageName2 |
| < 0 | yes | / ||  |
|  | no | I | DataInterface data = new DataImpl();<br />data.reset();<br />data.getBeverageName(-10);| it.polito.latazza.data.<br />TestDataImpl.testGetBeverageName3 |

**Criteria for method getBeverageCapsulesPerBox:**
 - Sign of id
 - Valid beverage (existing)

**Predicates for method getBeverageCapsulesPerBox:**

| Criteria | Predicate |
| -------- | --------- |
| Sign of id |      >= 0     |
|          |     < 0      |
|    Valid beverage      |     yes      |
|          |    no       |

**Boundaries for method getBeverageCapsulesPerBox:**

| Criteria | Boundary values |
| -------- | --------------- |
|   Sign of numberOfBoxes       |    MININT, 0, MAXINT             |

**Combination of predicates for method getBeverageCapsulesPerBox:**

| Sign of id | Valid beverage | Valid / Invalid | Description of the test case | JUnit test case |
|-------|-------|-------|-------|-------|
| >= 0 | yes | V | beverageId = data.createBeverage("Coffee", 20, 500);<br />data.getBeverageCapsulesPerBox(beverageId); | it.polito.latazza.data.<br />TestDataImpl.testGetBeverageCapsulesPerBox1 |
|  | no | I | DataInterface data = new DataImpl();<br />data.reset();<br />data.getBeverageCapsulesPerBox(10); | it.polito.latazza.data.<br />TestDataImpl.testGetBeverageCapsulesPerBox2 |
| < 0 | yes | / ||  |
|  | no | I | DataInterface data = new DataImpl();<br />data.reset();<br />data.getBeverageCapsulesPerBox(-10);| it.polito.latazza.data.<br />TestDataImpl.testGetBeverageCapsulesPerBox3 |

**Criteria for method getBeverageCapsules:**
 - Sign of id
 - Valid beverage (existing)
 - With history (capsules already ordered)
**Predicates for method getBeverageCapsules:**

| Criteria | Predicate |
| -------- | --------- |
| Sign of id |      >= 0     |
|          |     < 0      |
|    Valid beverage      |     yes      |
|          |    no       |
| With history | yes|
|               | no |

**Boundaries for method getBeverageCapsules:**

| Criteria | Boundary values |
| -------- | --------------- |
|   Sign of numberOfBoxes       |    MININT, 0, MAXINT             |

**Combination of predicates for method getBeverageCapsules:**

| Sign of id | Valid beverage | With history | Valid / Invalid | Description of the test case | JUnit test case |
|-------|-------|-------|-------|-------|-------|
| >= 0 | yes | no | V | beverageId = beverageId = data.createBeverage("Coffee", 20, 500);<br />data.getBeverageCapsules(beverageId); | it.polito.latazza.data.<br />TestDataImpl.testGetBeverageCapsules1 |
|  |  | yes | V | beverageId = data.createBeverage("Coffee", 20, 500);<br />data.buyBoxes(beverageId, 2);<br />data.getBeverageCapsules(beverageId); | it.polito.latazza.data.<br />TestDataImpl.testGetBeverageCapsules2 |
|  | no | no | I | DataInterface data = new DataImpl();<br />data.reset();<br />data.getBeverageCapsules(10); | it.polito.latazza.data.<br />TestDataImpl.testGetBeverageCapsules3 |
|  |  | yes | / |  |  |
| < 0 | yes | no | / |  |  |
|  |  | yes | / |  |  |
|  | no | no | I | DataInterface data = new DataImpl();<br />data.reset();<br />data.getBeverageCapsules(-10); | it.polito.latazza.data.<br />TestDataImpl.testGetBeverageCapsules4 |
|  |  | yes | / |  |  |

**Criteria for method getBeveragesId:**
 - With history (at least one beverage exists)

**Predicates for method getBeveragesId:**

| Criteria | Predicate |
| -------- | --------- |
| With history |      yes     |
|          |     no      |

**Combination of predicates for method getBeveragesId:**

| With history | Valid / Invalid | Description of the test case | JUnit test case |
|-------|-------|-------|-------|
| yes | V | id1 = data.createBeverage("Coffee", 20, 500);<br />id2 = data.createBeverage("Tea", 25, 400);<br />beverages = data.getBeveragesId() | it.polito.latazza.data.<br />TestDataImpl.testGetBeveragesId1 |
| no | V | data.reset();<br />data.getBeveragesId(); | it.polito.latazza.data.<br />TestDataImpl.testGetBeveragesId2 |

**Criteria for method getBeverages:**
 - With history (at least one beverage exists)

**Predicates for method getBeverages:**

| Criteria | Predicate |
| -------- | --------- |
| With history |      yes     |
|          |     no      |

**Combination of predicates for method getBeverages:**

| With history | Valid / Invalid | Description of the test case | JUnit test case |
|-------|-------|-------|-------|
| yes | V | id1 = data.createBeverage("Coffee", 20, 500);<br />id2 = data.createBeverage("Tea", 25, 400);<br />beverages = data.getBeverages() | it.polito.latazza.data.<br />TestDataImpl.testGetBeverages1 |
| no | V | data.reset();<br />data.getBeverages(); | it.polito.latazza.data.<br />TestDataImpl.testGetBeverages2 |

**Criteria for method getEmployeeBalance:**
 - Sign of id
 - Valid exmployee (existing)
 - With history (employee account already recharged)
 - 
**Predicates for method getEmployeeBalance:**

| Criteria | Predicate |
| -------- | --------- |
| Sign of id |      >= 0     |
|          |     < 0      |
|    Valid employee      |     yes      |
|          |    no       |
|    With history      |     yes      |
|          |    no       |

**Boundaries for method getEmployeeBalance:**

| Criteria | Boundary values |
| -------- | --------------- |
|   Sign of numberOfBoxes       |    MININT, 0, MAXINT             |

**Combination of predicates for method getEmployeeBalance:**

| Sign of id | Valid employee | With history | Valid / Invalid | Description of the test case | JUnit test case |
|-------|-------|-------|-------|-------|-------|
| >= 0 | yes | no | V |employeeId = data.createEmployee("Mario", "Rossi");<br />data.getEmployeeBalance(employeeId); | it.polito.latazza.data.<br />TestDataImpl.testGetEmployeeBalance1 |
|  |  | yes | V | employeeId = data.createEmployee("Mario", "Rossi");<br />data.rechargeAccount(employeeId, 500);<br />data.getEmployeeBalance(employeeId); | it.polito.latazza.data.<br />TestDataImpl.testGetEmployeeBalance2 |
|  | no | no | I | DataInterface data = new DataImpl();<br />data.reset();<br />data.getEmployeeBalance(10); | it.polito.latazza.data.<br />TestDataImpl.testGetEmployeeBalance3 |
|  |  | yes | / |  |  |
| < 0 | yes | no | / ||  |
|  |  | yes | / ||  |
|  | no | no | I | DataInterface data = new DataImpl();<br />data.reset();<br />data.getEmployeeBalance(-10);| it.polito.latazza.data.<br />TestDataImpl.testGetEmployeeBalance4 |
|  |  | yes | / ||  |

# White Box Unit Tests

### Test cases definition

| Unit name | JUnit test case                              |
| --------- | -------------------------------------------- |
| Beverage | it.polito.latazza.data.<br />Beverage.tcConstructor |
| Beverage | it.polito.latazza.data.<br />Beverage.tcGetters |
| Beverage | it.polito.latazza.data.<br />Beverage.tcSetters |
| BoxPurchase | it.polito.latazza.data.<br />BoxPurchase.tcToStringFormat |
| LaTazzaAccount | it.polito.latazza.data.<br />LaTazzaAccount.tcGetBalance |
| Recharge | it.polito.latazza.data.<br />Recharge.tcToStringFormat |
