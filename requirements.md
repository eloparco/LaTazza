# Requirements Document

Authors:  
* Bonelli Lorenzo       267563  
* Ruggeri Franco        265682  
* Rosso Alessandro      265485  
* Loparco Enrico        261072  
  
Date: 10/04/19
  
Version: 1.0.0

# Contents

- [Abstract](#abstract)
- [Stakeholders](#stakeholders)
- [Context Diagram and interfaces](#context-diagram-and-interfaces)
  + [Context Diagram](#context-diagram)
  + [Interfaces](#interfaces) 
- [Stories and personas](#stories-and-personas)
- [Functional and non functional requirements](#functional-and-non-functional-requirements)
  + [Functional Requirements](#functional-requirements)
  + [Non functional requirements](#non-functional-requirements)
- [Use case diagram and use cases](#use-case-diagram-and-use-cases)
  + [Use case diagram](#use-case-diagram)
  + [Use cases](#use-cases)
  + [Relevant scenarios](#relevant-scenarios)
- [Glossary](#glossary)
- [System design](#system-design)


# Abstract

LaTazza is a simple desktop application adopted by a group of office workers to satisfy the necessity of capsules in the workplace.

One employee takes the role of manager, he acts as a seller handling the supply and the resale of capsules while turning a small profit.  
As soon as an order is placed, an email is sent to the capsule supplier who ships the boxes. The order is agreed to be paid in cash upon delivery.

# Stakeholders

| Stakeholder name  	| Description 							    | 
|-----------------------|-------------------------------------------|
| Manager    			| Manage the purchase and sale of capsules	| 
| Employee  			| Buy capsules from manager 			    |
| Visitor        		| Buy capsules from manager      			|
| Capsule Vendor        | Sell capsules to manager 				    |


# Context Diagram and interfaces

## Context Diagram

```plantuml
left to right direction
skinparam packageStyle rectangle

actor Manager as m
actor "Capsule Vendor" as v
actor "Email System" as es

rectangle system {
    (LaTazza) as lt
    m -- lt
    es -- lt
    lt -- v
}
```

## Interfaces

| Actor                         | Logical Interface	            | Physical Interface    |
|-------------------------------|-------------------------------|:---------------------:|
| Manager      			        | GUI			                | Screen, keyboard		|
| Capsule Vendor                | Web service, APIs	            | Internet connection	|
| Email System                  | Web service, POP, IMAP, SMTP  | Internet connection	|

# Stories and personas

## Persona 1
![LaTazza](pictures/persona1.png "")  
*LaTazza client*
## Persona 2
![LaTazza](pictures/persona2.png "")  
*LaTazza manager*
## Persona 3
![LaTazza](pictures/persona3.png "")  
*LaTazza client*

## Story 1
David, LaTazza manager, notices the inventory is running low on capsules.

He sends an order to the supplier for two boxes of coffee and one of lemon tea. 
He doesn't want to waste too much time. He runs LaTazza and easily completes the order.

After two days the order is shipped to the company, he pays the supplier and then confirms the reception on LaTazza.

## Story 2
It's Tuesday 16:47.

Wendy takes a break to have an afternoon tea. She wants to buy 4 capsules for the rest of the week.
She doesn't want to wait long to complete the transaction and she doesn't have coins.

Since her balance is negative, she pays 10€ to add credit on her account and then she completes the purchase.

She proceeds to the hall to drink her tea and chat with other employees.

## Story 3
It's Thursday morning.

Mark is a temporary guest in this company. He was called as a consultant to review and improve the security system.

He is tired because last night he worked on a project until late, he wants to buy coffee at a convenient price.

He asks to buy in cash, he receives a coffee capsule quickly and he goes to the coffee maker right after.


# Functional and non functional requirements

## Functional Requirements
| ID   | Description  |
|:----:| ------------ |
| F1   | Register employee |
| F2   | Sell capsules (for credits or cash) |
| F3   | Buy boxes of capsules |
| F3.1 | Send order |
| F3.2 | Conclude order |
| F4   | Sell credits |
| F5   | Show inventory and cash account |
| F6   | Show pending orders |

## Non Functional Requirements
| ID        | Type (efficiency, reliability, ..)           | Description  |
| ------------- |:-------------:| -------|
|  NFR1     | Domain | Currency is € |
|  NFR2     | Efficiency - speed | Operations completed in less than 0.1 sec |
|  NFR3     | Efficiency - memory | Ram used by the application should be less than 1Mbyte |
|  NFR4     | Reliability | There must be less than 1 crashes each month |
|  NFR5     | Portability | The application can be run on the most common operating systems (Windows, Linux, Mac) |
|  NFR6     | Usability | User should be able to use the application with less than 10 min training |
|  NFR7     | Robustness | Probability of data corruption on failure less than 1% |


# Use case diagram and use cases

## Use case diagram
```plantuml
left to right direction

:Manager: as m
:Capsule Vendor: as ccv

(Register employee) as re
together {
	(Sell capsules) as sca
	(Sell capsules for credits) as sca1
	(Sell capsules for cash) as sca2
}
together {
	(Buy boxes of capsules) as bbc
	(Send order) as so
	(Conclude order) as co
}
(Sell credits) as scr
(Show inventory and\ncash account) as sica
(Show pending orders) as spo

m --> re
m --> sca
m --> bbc
ccv <-- so
m --> scr
m --> sica
m --> spo

sca <|-- sca1
sca <|-- sca2
so <. bbc : include
bbc ..> co : include

note left of sca1 : only for employees,\nvisitors can buy only with cash
```

## Use Cases


# Relevant scenarios
### Registration of a new employee
Precondition: employee works in the company and has not been registered yet  
Post condition: employee has an account

| Scenario ID: SC1 | Corresponds to UC: Register employee |
|:----------------:| ------------------------------------ |
| Step #           | Description                          |
| 1                | Manager clicks on 'Employees' |
| 2                | System shows the form for adding details about employee (ID, name, surname) |
| 3                | Manager compiles the form and clicks 'Register' |
| 4                | System creates a new account for the employee with balance 0 |

### Successful sale of capsules for credits (to an employee)
Precondition: employee has an account and there are enough capsules in inventory of the desired beverage  
Post condition: employee has bought the capsules, balance of the account and inventory are updated (decreased)

| Scenario ID: SC2 | Corresponds to UC: Sell capsules for credits |
|:----------------:| -------------------------------------------- |
| Step #           | Description                                  |
| 1                | Manager clicks on 'Sell Capsules' |
| 2                | Manager checks the checkbox 'Pay With Credits', sets 'Employee', 'Capsule Type' and 'Quantity' and finally clicks 'Sell' |
| 3                | System checks availability of capsules in inventory |
| 4                | System decreases number of capsules in inventory and credits of employee |

### Successful sale of capsules for cash to a visitor
Precondition: visitor has enough cash and there are enough capsules in inventory of the desired beverage  
Post condition: visitor has bought the capsules, cash account and inventory are updated

| Scenario ID: SC3 | Corresponds to UC: Sell capsules for cash |
|:----------------:| ----------------------------------------- |
| Step #           | Description                               |
| 1                | Manager clicks on 'Sell Capsules' |
| 2                | Manager do not check the 'Pay With Credits' checkbox, sets 'Capsule Type' and 'Quantity' and finally clicks 'Sell' |
| 3                | System checks availability of capsules in inventory |
| 4                | System decreases number of capsules in inventory and increases cash account |

### Successful sale of credits to an employee
Precondition: employee has an account and enough cash  
Post condition: balance of the account and cash account are updated (increased)

| Scenario ID: SC4 | Corresponds to UC: Sell credits |
|:----------------:| ------------------------------- |
| Step #           | Description |
| 1                | Manager clicks on 'Employees'  |
| 2                | Manager sets 'Employee' and 'Credits to sell' and clicks 'Sell' |
| 3                | System increases credits of employee and cash account |

### Successful sending of an order
Precondition: there is enough money in the cash account  
Post condition: cash account is decreases and order is stored as pending

| Scenario ID: SC5 | Corresponds to UC: Send order |
|:----------------:| ----------------------------- |
| Step #           | Description                   |
| 1                | Manager clicks on 'Orders' |
| 2                | Manager sets for each beverage the number of boxes he wants to buy and clicks 'Buy' |
| 3                | System checks cash account |
| 4                | System decreases cash account |
| 5                | System stores the order as pending |
| 6                | System notifies the vendor |

### Successful reception of an order
Precondition: there is at least a pending order  
Post condition: list of pending orders and inventory are updated  

| Scenario ID: SC6 | Corresponds to UC: Send order |
|:----------------:| ----------------------------- |
| Step #           | Description                   |
| 1                | Manager clicks on 'Orders' |
| 2                | Manager checks for each received order the corresponding checkbox from the pending order table |
| 3                | Manager clicks on 'Confirm Selected' |
| 4                | System sets selected orders as received (orders disappear from the pending order table) |
| 5                | System increases number of capsules in inventory |


# Glossary

```plantuml
class Manager {
	+cashAccount
}
class Box {
	+barcode
	+type
	+price
}
class Capsule {
	+type
	+sellPrice
}
class Customer {
	+name
}
class Visitor {

}
class Employee {
	+ID
	+balance
}

class Order {
    +date
    +state
}

Customer <|-- Employee
Customer <|-- Visitor
Employee <|-- Manager
Manager "1" -- "*" Order : makes >
Order "1" -- "1..*" Box : contains >
Box "1" -- "50" Capsule : contains > 
Customer "1" -- "*" Capsule : buys >
Manager "1" -- "*" Employee : adds credit >

note "sellPrice includes profit percentage" as N1
note "price is the payment to vendor, without profit percentage" as N2
note left of Order: state: pending or received
Capsule .. N1
N2 .. Box

```


# System Design
```plantuml
class LaTazzaSystem {
}

class Computer {
  +registerEmployee()
  +sellCredits()
  +sellCapsulesWithCredits()
  +sellCapsulesWithCash()
  +showInventory()
  +showCashAccount()
  +showPendingOrders()
  +buyBoxesOfCapsules()
  +confirmOrderReception()
}

class CapsuleVendorGateway {
  +sendOrder()
}

LaTazzaSystem o-- Computer

Computer -- CapsuleVendorGateway
```
