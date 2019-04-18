# Design Document Template

Authors:

Date:

Version:

# Contents

- [Package diagram](#package-diagram)
- [Class diagram](#class-diagram)
- [Verification traceability matrix](#verification-traceability-matrix)
- [Verification sequence diagrams](#verification-sequence-diagrams)

# Instructions

The design document has to comply with:
1. [Official Requirement Document](../Official\ Requirements\ Document.md)
2. [DataInterface.java](../src/main/java/it/polito/latazza/data/DataInterface.java)

UML diagrams **MUST** be written using plantuml notation.

# Package diagram

```plantuml
folder latazza as l
folder latazza.data as ld
folder latazza.exceptions as le
folder latazza.gui as lg

l ~~> ld
l ~~> lg
ld ~~> le
lg ~~> ld
```

\<define UML package diagram >

\<explain rationales for choices> 

\<mention architectural patterns used, if any>


# Class diagram

## it.polito.latazza
```plantuml
class LaTazza {
    + main(String[]) : void
}
```

## it.polito.latazza.data
```plantuml
class Beverage {
- beverageId : Integer
- name : String
- boxPrice : Integer
- capsulesPerBox : Integer
- availableQuantity : Integer
+ getId() : Integer
+ getName() : String
+ getCapsulesPerBox() : Integer
+ getCapsulesPrice() : Integer
+ getBoxPrice() : Integer
+ getQuantity() : Integer
+ setId(Integer id) : void
+ setName(String name) : void
+ setBoxPrice(Integer price) : void
+ setCapsulesPerBox(Integer capsulePerBox) : void
+ increaseAvailableQuantity(Integer amount) : void
+ decreaseAvailableQuantity(Integer amount) : void
+ toString() : String
}

class Employee {
- employeeId : Integer
- name : String
- surname : String
- balance : Integer
+ getId() : Integer
+ getName() : String
+ getSurname() : String
+ getBalance() : Integer
+ setId(Integer id) : void
+ setName(String name) : void
+ setSurname(String surname) : void
+ increaseBalance(Integer amount) : void
+ decreaseBalance(Integer amount) : void
+ toString() : String
}

class Transaction {
- date : Date
- amount : Integer
+ getDate() : Date
+ getAmount() : Integer
+ setDate(Date date) : void
+ setAmount(Integer amount) : Integer
}

class Consumption {
- numberOfCapsules : Integer
- fromAccount : Boolean
- employee : Employee
- beverage : Beverage
+ getNumberOfCapsules() : Integer
+ getFromAccount() : Boolean
+ getEmployee() : Employee
+ getBeverage() : Beverage
+ setEmployee(Employee employee) : void
+ setBeverage(Beverage beverage) : void
+ setNumberOfCapsules(Integer qnt) : void
+ setFromAccount(Boolean fromAccount) : void
+ toString() : String
}

class BoxPurchase {
- boxQuantity : Integer
- beverage : Beverage
+ getBeverage() : Beverage
+ setBeverage(Beverage beverage) : void
+ getBoxQuantity() : Integer
+ setBoxQuantity(Integer qnt) : void
+ toString() : String
}

class Recharge {
- employee : Employee
+ getEmployee() : Employee
+ setEmployee(Employee employee) : void
+ toString() : String
}

class LaTazzaAccount {
- balance : Integer
+ increaseBalance(Integer amount) : void
+ decreaseBalance(Integer amount) : void
+ getBalance() : Integer
}

class DataImpl {
- transactions : Map<Date, List<Transaction>>
- employees : Map<Integer, Employee>
- beverages : Map<Integer, Beverage> 
- laTazzaAccount : LaTazzaAccount
+ sellCapsules()
+ sellCapsulesToVisitor()
+ rechargeAccount()
+ buyBoxes()
+ getEmployeeReport()
+ getReport()
+ createBeverage()
+ updateBeverage()
+ getBeverageName()
+ getBeverageCapsulesPerBox()
+ getBeverageBoxPrice()
+ getBeveragesId()
+ getBeverages()
+ getBeverageCapsules()
+ createEmployee()
+ updateEmployee()
+ getEmployeeName()
+ getEmployeeSurname()
+ getEmployeeBalance()
+ getEmployeesId()
+ getEmployees()
+ getBalance()
}

DataImpl "1" --> "*" Transaction
DataImpl "1" --> "1" LaTazzaAccount
DataImpl "1" --> "*" Employee
DataImpl "1" --> "*" Beverage


Transaction <|-- Recharge
Transaction <|-- Consumption
Transaction <|-- BoxPurchase

Consumption "*" --> "0, 1" Employee
Consumption "*" --> "1" Beverage

Recharge "*" --> "1" Employee

BoxPurchase "*" --> "1" Beverage
```

## it.polito.latazza.exceptions
```plantuml
class Exception {
    
}
class BeverageException {
    
}
class DateException {
    
}
class EmployeeException {
    
}
class NotEnoughBalance {
    
}
class NotEnoughCapsules {
    
}
Exception <|-- BeverageException
Exception <|-- DateException
Exception <|-- EmployeeException
Exception <|-- NotEnoughBalance
Exception <|-- NotEnoughCapsules
```

\<for each package define class diagram with classes defined in the package>

\<mention design patterns used, if any>


# Verification traceability matrix

\<for each functional requirement from the requirement document, list which classes concur to implement it>


|  | Class x | Class y  | .. |
| ------------- |:-------------:| -----:| -----:|
| Functional requirement x  |  |  | |
| Functional requirement y  |  |  | |
| .. |  |  | |

# Verification sequence diagrams 
\<select key scenarios from the requirement document. For each of them define a sequence diagram showing that the scenario can be implemented by the classes and methods in the design>

```plantuml
": Class X" -> ": Class Y": "1: message1()"
": Class X" -> ": Class Y": "2: message2()"
```