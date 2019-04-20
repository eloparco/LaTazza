# Design Document Template

Authors:
* Bonelli Lorenzo 267563  
* Ruggeri Franco 265682  
* Rosso Alessandro 265485  
* Loparco Enrico 261072  

Date: 23/04/19

Version: 1.0.0

# Contents

- [Package diagram](#package-diagram)
- [Class diagram](#class-diagram)
- [Verification traceability matrix](#verification-traceability-matrix)
- [Verification sequence diagrams](#verification-sequence-diagrams)


# Package diagram

```plantuml
folder latazza as l
folder latazza.data as ld
folder latazza.exceptions as le
folder latazza.gui as lg

l ~~> ld
l ~> lg
ld ~> le
lg ~~> ld

note right of lg : view + controller
note bottom of ld : model
```

An architectural pattern is used: *MV* (particular case of *MVC*). View and model are implemented in the *latazza.gui* and *latazza.data* packages, respectively, and controller is embedded in the view, as common in Java (chosen programming language).

*Remark*: the *MV* pattern can be also seen as a 2-tier architecture (*layered* architectural pattern), composed of presentation (+ application logic) and data layers.


# Class diagram

## latazza
```plantuml
class LaTazza {
	+main(args:String[]) : void
}
```

## latazza.data
```plantuml
interface DataInterface
class DataImpl
class Beverage
class LaTazzaAccount	
class Employee
together {
	class Transaction
	class Recharge
	class BoxPurchase
	class Consumption
}

DataInterface <|-- DataImpl
Transaction <|-- Recharge
Transaction <|-- Consumption
Transaction <|-- BoxPurchase

DataImpl "1" -- "*" Transaction
DataImpl "1" -- "1" LaTazzaAccount
DataImpl "1" -- "*" Employee
DataImpl "1" -- "*" Beverage
Consumption "*" -- "0, 1" Employee
Consumption "*" -- "1" Beverage
Recharge "*" -- "1" Employee
BoxPurchase "*" -- "1" Beverage

DataInterface : +sellCapsules(employeeId:Integer, beverageId:Integer, numberOfCapsules:Integer, fromAccount:Boolean) : Integer
DataInterface : +sellCapsulesToVisitor(beverageId:Integer, numberOfCapsules:Integer) : void
DataInterface : +rechargeAccount(id:Integer, amountInCents:Integer) : Integer
DataInterface : +buyBoxes(beverageId:Integer, boxQuantity:Integer) : void
DataInterface : +getEmployeeReport(employeeId:Integer, startDate:Date, endDate:Date) : List<String>
DataInterface : +getReport(startDate:Date, endDate:Date) : List<String>
DataInterface : +createBeverage(name:String, capsulesPerBox:Integer, boxPrice:Integer) : Integer
DataInterface : +updateBeverage(id:Integer, name:String, capsulesPerBox:Integer, boxPrice:Integer) : void
DataInterface : +getBeverageName(id:Integer) : String
DataInterface : +getBeverageCapsulesPerBox(id:Integer) : Integer
DataInterface : +getBeverageBoxPrice(id:Integer) : Integer
DataInterface : +getBeveragesId() : List<Integer>
DataInterface : +getBeverages() : Map<Integer, String>
DataInterface : +getBeverageCapsules(id:Integer) : Integer
DataInterface : +createEmployee(String name:String, surname:String) : Integer
DataInterface : +updateEmployee(id:Integer, name:String, surname:String) : void
DataInterface : +getEmployeeName(id:Integer) : String
DataInterface : +getEmployeeSurname(id:Integer) : String
DataInterface : +getEmployeeBalance(id:Integer) : Integer
DataInterface : +getEmployeesId() : List<Integer>
DataInterface : +getEmployees() : Map<Integer,String>
DataInterface : +getBalance() : Integer
DataInterface : +reset() : void

DataImpl : -transactions : List<Transaction>
DataImpl : -employees : Map<Integer, Employee>
DataImpl : -beverages : Map<Integer, Beverage> 
DataImpl : -laTazzaAccount : Account

Beverage : -id : int
Beverage : -name : String
Beverage : -boxPrice : int
Beverage : -capsulesPerBox : int
Beverage : -availableCapsules : int
Beverage : +Beverage(beverageId:int, name:String, boxPrice:int, capsulePerBox:int)
Beverage : +getId() : int
Beverage : +getName() : String
Beverage : +setName(name:String) : void
Beverage : +getBoxPrice() : int
Beverage : +setBoxPrice(boxPrice:int) : void
Beverage : +getCapsulesPerBox() : int
Beverage : +setCapsulesPerBox(capsulesPerBox:int) : void
Beverage : +getAvailableCapsules() : int
Beverage : +getCapsulesPrice() : int
Beverage : +increaseAvailableQuantity(numberOfBoxes:int) : void
Beverage : +decreaseAvailableQuantity(numberOfCapsules:int) : void
Beverage : +toString() : String

LaTazzaAccount : -balance : int
LaTazzaAccount : +Account(balance:int)
LaTazzaAccount : +getBalance() : int
LaTazzaAccount : +increaseBalance(amount:int) : void
LaTazzaAccount : +decreaseBalance(amount:int) : void

Employee : -id : int
Employee : -name : String
Employee : -surname : String
Employee : -balance : int
Employee : +Employee(id:int, name:String, surname:String, balance:int)
Employee : +getId() : int
Employee : +getName() : String
Employee : +setName(name:String) : void
Employee : +getSurname() : String
Employee : +setSurname(surname:String) : void
Employee : +getBalance() : int
Employee : +increaseBalance(amount:int) : void
Employee : +decreaseBalance(amount:int) : void
Employee : +toString() : String

Transaction : -date : Date
Transaction : +getDate() : Date
Transaction : +toString() : String

Recharge : -employee : Employee
Recharge : -amount : int
Recharge : +Recharge(employee:Employee, amount:int)
Recharge : +getEmployee() : Employee
Recharge : +toString() : String

BoxPurchase : -beverage : Beverage
BoxPurchase : -boxQuantity : int
BoxPurchase : +BoxPurchase(beverage:Beverage, boxQuantity:int)
BoxPurchase : +toString() : String

Consumption : -employee : Employee
Consumption : -beverage : Beverage
Consumption : -numberOfCapsules : int
Consumption : -fromAccount : boolean
Consumption : +Consumption(beverage:Beverage, numberOfCapsules:int)
Consumption : +Consumption(employee:Employee, beverage:Beverage, numberOfCapsules:int, fromAccount:boolean)
Consumption : -getEmployee() : Employee
Consumption : +toString() : String

note right of DataImpl : Facade
```

A design pattern is used: *Facade*. The *DataImpl* class provides an easier usage of the package to the client, hiding the details of its composition. It is of course the unique public class in the package; all the others have package visibility.


## latazza.exceptions
```plantuml
class Exception
class BeverageException
class DateException
class EmployeeException
class NotEnoughBalance
class NotEnoughCapsules

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