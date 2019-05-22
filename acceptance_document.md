# Acceptance Testing Documentation template

Authors:

Date:

Version:

# Contents

- [Scenarios](#scenarios)

- [Coverage of scenarios](#scenario-coverage)
- [Coverage of non-functional requirements](#nfr-coverage)



# Scenarios

```
<Define here additional scenarios for the application. The two original scenarios in the Requirements Document are reported here.>
```

| Scenario ID: SC1 | Corresponds to UC1                             |
| ---------------- | ---------------------------------------------- |
| Description      | Colleague uses one capsule of type 1           |
| Precondition     | account of C has enough money to buy capsule T |
| Postcondition    | account of C updated, count of T updated       |
| Step#            | Step description                               |
| 1                | Administrator selects capsule type T           |
| 2                | Administrator selects colleague C              |
| 3                | Deduce one for quantity of capsule T           |
| 4                | Deduce price of T from account of C            |

| Scenario ID: SC2 | Corresponds to UC2                                     |
| ---------------- | ------------------------------------------------------ |
| Description      | Colleague uses one capsule of type T, account negative |
| Precondition     | account of C has not enough money to buy capsule T     |
| Postcondition    | account of C updated, count of T updated               |
| Step#            | Step description                                       |
| 1                | Administrator selects capsule type T                   |
| 2                | Administrator selects colleague C                      |
| 3                | Deduce one for quantity of capsule T                   |
| 4                | Deduce price of T from account of C                    |
| 5                | Account of C is negative, issue warning                |

| Scenario ID: SC3 | Corresponds to UC3 |
| ---------------- | ------------------ |
| Description      | ...                |
| Precondition     | ...                |
| Postcondition    | ...                |
| Step#            | ...                |
| 1                | ...                |
|                  |                    |

| Scenario ID: SC4 | Corresponds to UC4 |
| ---------------- | ------------------ |
| Description      | A new employee wants to be registered in the system and wants to recharge his account so that he could then buy capsules when he'll need them                |
| Precondition     | ...                |
| Postcondition    | The new employee is registered in the system and his account is updated with the recharged amount                |
| Step#            | ...                |
| 1                 | Administrator inputs employee Name and Surname                   |
| 2                 | The new employee is registered in the system with an account balance of 0  |
| 3                 | Administrator selects the employee  |
| 4                 | Administrator inserts the amount A to recharge  |
| 5                 | Increase of A the employee account balance |

| Scenario ID: SC6 | Corresponds to UC3                                   |
| ---------------- | ------------------------------------------------------ |
| Description      | Colleague wants to be registered in the system and wants to recharge his account so that he could then buy capsules when he will need them |
| Precondition     | Colleague works in the company     |
| Postcondition    | Account of C created and updated with the recharged amount               |
| Step#            | Step description                                       |
| 1                | Administrator enters name and surname of the colleague                   |
| 2                | The colleague is registered in the system with an account balance of 0                      |
| 3                | Administrator selects the employee                  |
| 4                | Administrator enters the amount A to recharge                   |
| 5                | Increase of A in the colleague account balance                |

# Coverage of Scenarios

```
<Report in the following table the coverage of the scenarios listed above. Report at least an API test (defined on the functions of DataImpl only) and a GUI test (created with EyeAutomate) for each of the scenarios. For each scenario, report the functional requirements it covers.
In the API Tests column, report the name of the method of the API Test JUnit class you created. In the GUI Test column, report the name of the .txt file with the test case you created.>
```

### 

| Scenario ID | Functional Requirements covered | API Test(s) | GUI Test(s) |
| ----------- | ------------------------------- | ----------- | ----------- |
| 1           | FR1                             | it.polito.latazza.data.TestDataImpl.testSellCapsules1			|             	|
| 2           | FR1                             | it.polito.latazza.data.TestDataImpl.testScenario2            		|             	|
| 3	      | FR2                             | it.polito.latazza.data.TestDataImpl.testSellCapsulesToVisitor1        |             	|
| 4           | FR5                             | it.polito.latazza.data.TestDataImpl.testGetEmployeeReport1            |             	|
| 5           | FR6                             | it.polito.latazza.data.TestDataImpl.testGetReport1            	|             	|
| 6	      | FR8+FR3				| it.polito.latazza.data.TestDataImpl.testScenario6 			|		|
| 7	      | FR7+FR4				| it.polito.latazza.data.TestDataImpl.testScenario7 			| 		|


# Coverage of Non Functional Requirements

```
<Report in the following table the coverage of the Non Functional Requirements of the application - only those that can be tested with automated testing frameworks.>
```

### 

| Non Functional Requirement | Test name |
| -------------------------- | --------- |
| Performance                |           |

