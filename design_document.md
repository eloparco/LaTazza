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

The classes necessary for the implementation of the requirements has been added to 'it.polito.latazza.data' package.  
The architectural pattern used is the MVC pattern, to connect the data to the GUI through a controller. It can also be seen as a three-layer architecture, composed of data (local files), presentation (GUI) and application logic.

# Class diagram

## it.polito.latazza
```plantuml
class LaTazza {
    + main(String[] args) : void
}
```

## it.polito.latazza.data
```plantuml
class Beverage {
- beverageId : int
- name : String
- boxPrice : int
- capsulesPerBox : int
- availableCapsules : int
+ Beverage(int, String, int, int)
+ getId() : int
+ setId(int id) : void
+ getBoxPrice() : int
+ setBoxPrice(int boxPrice) : void
+ getCapsulesPerBox() : int
+ setCapsulesPerBox(int capsulesPerBox) : void
+ getAvailableQuantity() : int
+ increaseAvailableQuantity(int numberOfBoxes) : void
+ decreaseAvailableQuantity(int numberOfCapsules) : void
+ getName() : String
+ setName(String name) : void
+ getCapsulesPrice() : int
+ toString() : String
}

class Employee {
- employeeId : int
- name : String
- surname : String
- balance : int
+ Employee(int id, String name, String surname, int balance) : Employee(int
+ getId() : int
+ setId(int id) : void
+ getName() : String
+ setName(String name) : void
+ getSurname() : String
+ setSurname(String surname) : void
+ getBalance() : int
+ setBalance(int balance) : void
+ increaseBalance(int amount) : void
+ decreaseBalance(int amount) : void
+ hashCode() : int
+ equals(Object obj) : boolean
+ toString() : String

}

class Transaction {
- date : Date
+ Transaction(Date date)
+ getDate() : Date
+ setDate(Date date) : void
+ getDateFormatted() : String
}

class Consumption {
- numberOfCapsules : int
- fromAccount : boolean
- employee : Employee
- beverage : Beverage
+ Consumption(Employee employee, Beverage beverage, boolean fromAccount, int numberOfCapsules, Date date)
+ getEmployee() : Employee
+ setEmployee(Employee employee) : void
+ getBeverage() : Beverage
+ setBeverage(Beverage beverage) : void
+ isFromAccount() : boolean
+ setFromAccount(boolean fromAccount) : void
+ getNumberOfCapsules() : int
+ setNumberOfCapsules(int numberOfCapsules) : void
+ toString() : String
}

class BoxPurchase {
- boxQuantity : int
- beverage : Beverage
+ BoxPurchase(Beverage beverage, int boxQuantity, Date date)
+ getBeverage() : Beverage
+ setBeverage(Beverage beverage) : void
+ getboxQuantity() : int
+ setboxQuantity(int boxQuantity) : void
+ toString() : String
}

class Recharge {
- employee : Employee
- amount : int
+ Recharge(Employee employee, int amount, Date date)
+ getEmployee() : Employee
+ setEmployee(Employee employee) : void
+ getAmount() : int
+ setAmount(int amount) : void
+ toString() : String
}

class LaTazzaAccount {
- balance : int
+ getBalance() : int
+ setBalance(int balance) : void
+ increaseBalance(int amount) : void
+ decreaseBalance(int amount) : void
}

interface DataInterface {
+ sellCapsules(Integer employeeId, Integer beverageId, Integer numberOfCapsules, Boolean fromAccount) : Integer
+ sellCapsulesToVisitor(Integer beverageId, Integer numberOfCapsules) : void
+ rechargeAccount(Integer id, Integer amountInCents) : Integer
+ buyBoxes(Integer beverageId, Integer boxQuantity) : void
+ getEmployeeReport(Integer employeeId, Date startDate, Date endDate) : List<String>
+ getReport(Date startDate, Date endDate) : List<String>
+ createBeverage(String name, Integer capsulesPerBox, Integer boxPrice) : Integer
+ updateBeverage(Integer id, String name, Integer capsulesPerBox, Integer boxPrice) : void
+ getBeverageName(Integer id) : String
+ getBeverageCapsulesPerBox(Integer id) : Integer
+ getBeverageBoxPrice(Integer id) : Integer
+ getBeveragesId() : List<Integer>
+ String> getBeverages() : Map<Integer,
+ getBeverageCapsules(Integer id) : Integer
+ createEmployee(String name, String surname) : Integer
+ updateEmployee(Integer id, String name, String surname) : void
+ getEmployeeName(Integer id) : String
+ getEmployeeSurname(Integer id) : String
+ getEmployeeBalance(Integer id) : Integer
+ getEmployeesId() : List<Integer>
+ String> getEmployees() : Map<Integer,
+ getBalance() : Integer
+ reset() : void
}

class DataImpl {
- transactions : List<Transaction>
- employees : Map<Integer, Employee>
- beverages : Map<Integer, Beverage> 
- laTazzaAccount : LaTazzaAccount
}

DataInterface <|-- DataImpl
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
Facade pattern: provide an interface to use the subsystem. The 'DataImpl' class acts as a wrapper, decoupling the application code that uses the system from the details of the system itself (implemented in 'Beverage', 'Employee', 'Transaction', etc.).

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

# Verification traceability matrix

|  | DataImpl | LaTazzaAccount  | Employee | Beverage | Transaction | Consumption | BoxPurchase | Recharge
| -----:| -----:| -----:| -----:| -----:| -----:| -----:| -----:| -----:| 
| FR1  |<p align="center"> ***X*** | <p align="center"> ***X***|<p align="center"> ***X*** |<p align="center"> ***X***| <p align="center"> ***X***| <p align="center"> ***X***| | |
| FR2  |<p align="center"> ***X*** |<p align="center"> ***X*** | |<p align="center"> ***X*** |<p align="center"> ***X*** |<p align="center"> ***X*** | | |
| FR3  |<p align="center"> ***X*** |<p align="center"> ***X*** |<p align="center"> ***X*** | | <p align="center"> ***X***| | |<p align="center"> ***X***
| FR4  |<p align="center"> ***X*** | <p align="center"> ***X***| | <p align="center"> ***X***| <p align="center"> ***X***| |<p align="center"> ***X*** | |
| FR5  |<p align="center"> ***X*** | |<p align="center"> ***X*** |<p align="center"> ***X*** |<p align="center"> ***X***| <p align="center"> ***X***| |<p align="center"> ***X*** |
| FR6  | <p align="center"> ***X***| | | <p align="center"> ***X***|<p align="center"> ***X*** | <p align="center"> ***X***| |<p align="center"> ***X*** |
| FR7  |<p align="center"> ***X*** | | |<p align="center"> ***X*** | | | | |
| FR8  |<p align="center"> ***X*** | <p align="center"> ***X***|<p align="center"> ***X*** | | | | | |

# Verification sequence diagrams 
\<select key scenarios from the requirement document. For each of them define a sequence diagram showing that the scenario can be implemented by the classes and methods in the design>

```plantuml
": Class X" -> ": Class Y": "1: message1()"
": Class X" -> ": Class Y": "2: message2()"
```