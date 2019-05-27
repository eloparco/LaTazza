# Acceptance Testing Documentation template

Authors:
* Bonelli Lorenzo 267563  
* Ruggeri Franco 265682  
* Rosso Alessandro 265485  
* Loparco Enrico 261072  

Date: 26/05/19

Version: 1.0.0


# Contents

- [Scenarios](#scenarios)
- [Coverage of scenarios](#coverage-of-scenarios)
- [Coverage of non-functional requirements](#coverage-of-non-functional-requirements)


# Scenarios

| Scenario ID: SC1 | Corresponds to UC1                             |
| ---------------- | ---------------------------------------------- |
| Description      | Colleague uses one capsule of type T           |
| Precondition     | account of C has enough money to buy capsule T |
| Postcondition    | account of C updated, count of T updated       |
| Step#            | Step description                               |
| 1                | Administrator selects capsule type T           |
| 2                | Administrator selects colleague C              |
| 3                | Deduce one for quantity of capsule T           |
| 4                | Deduce price of T from account of C            |

| Scenario ID: SC2 | Corresponds to UC1                                     |
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

| Scenario ID: SC3	| Corresponds to UC2 				|
| --------------------	| --------------------------------------------- |
| Description     	| Visitor uses one capsule of type T 		|
| Precondition     	| Quantity of capsule type T greater than 0 	|
| Postcondition    	| Balance updated, count of T updated		|
| Step#            	| Step description      			|
| 1             	| Administrator selects capsule type T 		|
| 2                	| Administrator selects Visitor 		|
| 3                	| Reduce one for quantity of capsule T		|
| 4                	| Add price of T to balance 			|

| Scenario ID: SC4 | Corresponds to UC4 |
| ---------------- | ------------------ |
| Description      | Administrator wants to check all the transaction of a specific employee in a specific time span |
| Precondition     | The employee is registered into the system |
| Postcondition    | The report is shown with all the transaction made by the employee during the selected time span |
| Step#            | Step description                |
| 1                 | Administrator selects the employee |
| 2                 | Administrator inputs a valid start and end date |
| 3                 | The report is printed to the screen |

| Scenario ID: SC5 | Corresponds to UC5 |
| ---------------- | ------------------ |
| Description      | Administrator wants to check all the transactions that occured in a specific time span |
| Precondition     |  |
| Postcondition    | The report is shown with all the transaction made during the selected time span |
| Step#            | Step description                |
| 1                 | Administrator inputs a valid start and end date |
| 2                 | The report is printed to the screen |

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

| Scenario ID: SC7 | Corresponds to UC4                                   |
| ---------------- | ------------------------------------------------------ |
| Description      | Add a new beverage of type T and order one box |
| Precondition     | Shared balance sufficient     |
| Postcondition    | Beverage registered, shared account decreased, capsules amount added |
| Step#            | Step description                                       |
| 1                | Administrator enters name, capsules and price of the beverage type T                |
| 2                | Administrator selects to buy one box of  T                  |
| 3                | Deduce price of T from shared balance              |
| 4                | Add capsules of T to avaliable capsules                  |


# Coverage of Scenarios

| Scenario ID | Functional Requirements covered | API Test(s) | GUI Test(s) |
| ----------- | ------------------------------- | ----------- | ----------- |
| 1           | FR1                             | it.polito.latazza.data.TestDataImpl.testSellCapsules1			| scenario_1.txt	|
| 2           | FR1                             | it.polito.latazza.data.TestDataImpl.testScenario2            		| scenario_2.txt	|
| 3	      | FR2                             | it.polito.latazza.data.TestDataImpl.testSellCapsulesToVisitor1        | scenario_3.txt	|
| 4           | FR5                             | it.polito.latazza.data.TestDataImpl.testGetEmployeeReport1            | scenario_4.txt	|
| 5           | FR6                             | it.polito.latazza.data.TestDataImpl.testGetReport1            	| scenario_5.txt	|
| 6	      | FR8+FR3				| it.polito.latazza.data.TestDataImpl.testScenario6 			| scenario_6.txt	|
| 7	      | FR7+FR4				| it.polito.latazza.data.TestDataImpl.testScenario7 			| scenario_7.txt	|


# Coverage of Non Functional Requirements

| Non Functional Requirement	| Test name	|
| ----------------------------	| ------------- |
| NFR2 (Performance)      		| it.polito.latazza.data.TestDataImpl.testPerformance |
