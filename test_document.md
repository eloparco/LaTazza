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