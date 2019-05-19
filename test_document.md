# Unit Testing Documentation template

Authors:
* Bonelli Lorenzo 267563  
* Ruggeri Franco 265682  
* Rosso Alessandro 265485  
* Loparco Enrico 261072  

Date: 19/05/19

Version: 1.0.0

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

**Predicates for method decreaseAvailableCapsules:**

| Criteria | Predicate |
| -------- | --------- |
| Sign of numberOfCapsules |      >= 0     |
|          |     < 0      |
| Enough availableCapsules | availableCapsules >= numberOfCapsules |
|   | availableCapsules < numberOfCapsules |

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
| yes     |>= 0    |V               |Beverage b = new Beverage(1, "tea", 10, 10); new BoxPurchase(b, 10); | it.polito.latazza.data.BoxPurchase.tc1 |
|     |< 0            | I               | Beverage b = new Beverage(1, "tea", 10, 10); new BoxPurchase(b, -1);  | it.polito.latazza.data.BoxPurchase.tc2 |
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
| < 0      |no    |I               | increaseBalance(-10)          | it.polito.latazza.data.TestLaTazzaAccount.tc6 |
|     |yes            | I               |        | |

**Criteria for decreaseBalance:**

- Enough balance
- Sign of amount

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
| Value of amount | MININT, 0, MAXINT |

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

**Criteria for method getEmployeeName:**
 - Valid employee

**Predicates for method getEmployeeName:**

| Criteria | Predicate |
| -------- | --------- |
| Valid employee |      yes     |
|          |     no      |

**Combination of predicates for method getEmployeeName:**

| Valid employee | Valid / Invalid | Description of the test case | JUnit test case |
|-------|-------|-------|-------|
|yes| V | int id = CreateEmployee("Mario", "Rossi"); getEmployeeName(id);| it.polito.latazza.data.TestDataImpl.testGetEmployeeName1
|no| I | getEmployeeName(10);| it.polito.latazza.data.TestDataImpl.testGetEmployeeName2

**Criteria for method getEmployeeSurname:**
 - Valid employee

**Predicates for method getEmployeeSurname:**

| Criteria | Predicate |
| -------- | --------- |
| Valid employee |      yes     |
|          |     no      |

**Combination of predicates for method getEmployeeSurname:**

| Valid employee | Valid / Invalid | Description of the test case | JUnit test case |
|-------|-------|-------|-------|
|yes| V | int id = CreateEmployee("Mario", "Rossi"); getEmployeeSurname(id);| it.polito.latazza.data.TestDataImpl.testGetEmployeeSurname1
|no| I | getEmployeeName(10);| it.polito.latazza.data.TestDataImpl.testGetEmployeeSurname2

**Criteria for method getReport:**
 - Valid dates
- Order of dates

**Predicates for method getReport:**

| Criteria | Predicate |
| -------- | --------- |
| Valid dates |      yes     |
|          |     no      |
|Order of dates | StartDate <= EndDate| 
| | EndDate > StartDate|

**Combination of predicates for method getReport:**

| Valid dates | Order of dates| Valid / Invalid | Description of the test case | JUnit test case |
|-------|-------|-------|-------|
|yes |StartDate <= EndDate | V | Date d = new Date(); data.getReport(d, new Date()); int id = data.createEmployee("Mario", "Rossi"); data.rechargeAccount(id, 100);  data.getReport(d, new Date()); etc. | it.polito.latazza.data.TestDataImpl.testGetReport1
| |StartDate > EndDate | I | data.getReport( new Date(new Date().getTime() + (1000 * 60 * 60 * 48)), new Date());| it.polito.latazza.data.TestDataImpl.testGetReport2
| no |StartDate <= EndDate | I | data.getReport( null, new Date());| it.polito.latazza.data.TestDataImpl.testGetReport3
| |StartDate > EndDate | I | | 


**Criteria for method getEmployeeReport:**
 - Valid dates
 - Order of dates

**Predicates for method getEmployeeReport:**

| Criteria | Predicate |
| -------- | --------- |
| Valid employee |      yes     |
|          |     no      |
| Valid dates |      yes     |
|          |     no      |
|Order of dates | StartDate < EndDate| 
| | EndDate > StartDate|

**Combination of predicates for method getEmployeeReport:**

|Valid employee | Valid dates | Order of dates| Valid / Invalid | Description of the test case | JUnit test case |
|-------|-------|-------|-------|
| yes |yes |StartDate < EndDate | V | Date d = new Date(); int id = data.createEmployee("Mario" ,"Rossi"); data.rechargeAccount(id, 10000); data.getEmployeeReport(id, d, new Date()); etc.|  it.polito.latazza.data.TestDataImpl.testGetEmployeeReport1
|| |StartDate > EndDate | I | data.getEmployeeReport(data.createEmployee("Mario" ,"Rossi"), new Date(new Date().getTime() + (1000 * 60 * 60 * 48)), new Date()); | it.polito.latazza.data.TestDataImpl.testGetEmployeeReport2
|| no |StartDate < EndDate | I | data.getEmployeeReport(data.createEmployee("Mario" ,"Rossi"), new Date(), null);| it.polito.latazza.data.TestDataImpl.testGetEmployeeReport3
|| |StartDate > EndDate | I | | 
| no |yes |StartDate < EndDate | V | data.getEmployeeReport(null, new Date(), new Date()); | it.polito.latazza.data.TestDataImpl.testGetEmployeeReport4
|| |StartDate > EndDate | I | ;| 
|| no |StartDate < EndDate | I |  | 
|| |StartDate > EndDate | I | | 

# White Box Unit Tests

### Test cases definition

| Unit name | JUnit test case                              |
| --------- | -------------------------------------------- |
| Beverage | it.polito.latazza.data.<br />TestBeverage.tcConstructor |
| Beverage | it.polito.latazza.data.<br />TestBeverage.tcGetters |
| Beverage | it.polito.latazza.data.<br />TestBeverage.tcSetters |
| BoxPurchase | it.polito.latazza.data.<br />TestBoxPurchase.tcToStringFormat |
| LaTazzaAccount | it.polito.latazza.data.<br />TestLaTazzaAccount.tcGetBalance |
| Recharge | it.polito.latazza.data.<br />TestRecharge.tcToStringFormat |
| DataImpl | it.polito.latazza.data.<br />TestDataImpl.tcGetEmployeesId |
| DataImpl | it.polito.latazza.data.<br />TestDataImpl.tcGetEmployees |

### Code coverage report

### Loop coverage analysis  

| Unit name | Loop rows | Number of iterations | JUnit test case                              |
| --------- | --------- | -------------------- | -------------------------------------------- |
| DataImpl  |  282    	| 0                    | it.polito.latazza.data.<br />TestDataImpl.testGetBeveragesId2 |
|           |           | 1                    | it.polito.latazza.data.<br />TestDataImpl.testGetBeveragesId1 |
|           |           | 2+                   | it.polito.latazza.data.<br />TestDataImpl.testGetBeveragesId3 |
| DataImpl  |  287e      | 0                   | it.polito.latazza.data.<br />TestDataImpl.testGetBeverages2 |
|           |           | 1                    | it.polito.latazza.data.<br />TestDataImpl.testGetBeverages1 |
|           |           | 2+                   | it.polito.latazza.data.<br />TestDataImpl.testGetBeverages3 |
| DataImpl  |  216      | 0                    | it.polito.latazza.data.<br />TestDataImpl.testGetReport1 |
|           |           | 1                    | it.polito.latazza.data.<br />TestDataImpl.testGetReport1 |
|           |           | 2+                   | it.polito.latazza.data.<br />TestDataImpl.testGetReport1 |
| DataImpl  |  358      | 0                    | it.polito.latazza.data.<br />tcGetEmployeesId |
|           |           | 1                    | it.polito.latazza.data.<br />tcGetEmployeesId |
|           |           | 2+                   | it.polito.latazza.data.<br />tcGetEmployeesId |
| DataImpl  |  363      | 0                    | it.polito.latazza.data.<br />tcGetEmployees |
|           |           | 1                    | it.polito.latazza.data.<br />tcGetEmployees |
|           |           | 2+                   | it.polito.latazza.data.<br />tcGetEmployees |

TODO fix loop rows when code is final

