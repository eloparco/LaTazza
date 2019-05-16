### **Class *Recharge* - *constructor***

**Criteria for *constructor*:**
 - Sign of amount
 - Valid employee

**Predicates for method *increaseAvailableCapsules*:**

| Criteria | Predicate |
| -------- | --------- |
| Sign of amount |      >= 0     |
|          |     < 0      |
|    Valid employee      |    not null      |
|          | null       |

**Boundaries**:

| Criteria | Boundary values |
| -------- | --------------- |
|   Sign of amount  |    MININT, 0, MAXINT   |

**Combination of predicates**:

| Sign of amount | Valid employee | Valid / Invalid | Description of the test case | JUnit test case |
|-------|-------|-------|-------|-------|
| >= 0 | yes | V |Recharge(emp, 1000)|it.polito.latazza.data.TestRecharge.tc1|
|  | no | I |Recharge(null, 1000)|it.polito.latazza.data.TestRecharge.tc2|
| < 0 | yes | V |Recharge(emp, -1000)|it.polito.latazza.data.TestRecharge.tc3|
|  | no | I |Recharge(null, -1000)|it.polito.latazza.data.TestRecharge.tc4|

### **Class *Beverage* - method *increaseAvailableCapsules***

**Criteria for method *increaseAvailableCapsules*:**
 - Sign of numberOfBoxes
 - Overflow of availableCapsules

**Predicates for method *increaseAvailableCapsules*:**

| Criteria | Predicate |
| -------- | --------- |
| Sign of numberOfBoxes |      >= 0     |
|          |     < 0      |
|    Overflow of availableCapsules      |     availableCapsules + numberOfBoxes * capsulesPerBox > MAXINT      |
|          |    availableCapsules + numberOfBoxes * capsulesPerBox <= MAXINT        |

**Boundaries**:

| Criteria | Boundary values |
| -------- | --------------- |
|   Sign of numberOfBoxes       |    MININT, 0, MAXINT             |
|     Overflow of availableCapsules      |     availableCapsules + numberOfBoxes * capsulesPerBox = MAXINT      |   

**Combination of predicates**:

| Sign of numberOfBoxes | Overflow of availableCapsules | Valid / Invalid | Description of the test case | JUnit test case |
|-------|-------|-------|-------|-------|
| >= 0 | no | V || it.polito.latazza.data.TestBeverage.tc1 |
|  | yes | I || it.polito.latazza.data.TestBeverage.tc2 |
| < 0 | no | I || it.polito.latazza.data.TestBeverage.tc3 |
|  | yes | / ||  |

### **Class *Beverage* - method *decreaseAvailableCapsules***

**Criteria for method *decreaseAvailableCapsules*:**
 - Sign of numberOfCapsules
 - Enough availableCapsules
 - Overflow of availableCapsules

**Predicates for method *decreaseAvailableCapsules*:**

| Criteria | Predicate |
| -------- | --------- |
| Sign of numberOfCapsules |      >= 0     |
|          |     < 0      |
| Enough availableCapsules | availableCapsules >= numberOfCapsules |
|   | availableCapsules < numberOfCapsules |
|    Overflow of availableCapsules      |     availableCapsules - numberOfCapsules > MAXINT      |
|          |    availableCapsules - numberOfCapsules <= MAXINT        |

**Boundaries**:

| Criteria | Boundary values |
| -------- | --------------- |
|   Sign of numberOfCapsules   |    MININT, 0, MAXINT             |
|     Overflow of availableCapsules      |     availableCapsules + numberOfBoxes * capsulesPerBox = MAXINT      |   

**Combination of predicates**:

| Sign of numberOfCapsules | Enough availableCapsules | Overflow of availableCapsules | Valid / Invalid | Description of the test case | JUnit test case |
|-------|-------|-------|-------|-------|-------|
| >= 0 | yes | no | V || it.polito.latazza.data.TestBeverage.tc4 |
| |  | yes | / || |
| | no | no | I || it.polito.latazza.data.TestBeverage.tc5 |
| | | yes | / || |
| < 0 | yes | no | I || it.polito.latazza.data.TestBeverage.tc6 |
| | | yes | I | | it.polito.latazza.data.TestBeverage.tc7 |
| | no | no | / |
| | | yes | / |