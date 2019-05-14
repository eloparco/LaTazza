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
	abstract class Transaction
	class Recharge
	class BoxPurchase
	class Consumption
}

DataInterface <|-- DataImpl
Transaction <|-- Recharge
Transaction <|-- Consumption
Transaction <|-- BoxPurchase

DataImpl "1" --> "*" Transaction
DataImpl "1" -> "1" LaTazzaAccount
DataImpl "1" --> "*" Employee
DataImpl "1" --> "*" Beverage
Employee "0..1" <-- "*" Consumption
Consumption "*" --> "1" Beverage
Employee "1" <-- "*" Recharge
BoxPurchase "*" --> "1" Beverage

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
DataImpl : -laTazzaAccount : LaTazzaAccount
DataImpl : -nextEmployeeId : int
DataImpl : -nextBeverageId : int
DataImpl : -<u>PATHNAME : String
DataImpl : -<u>FILENAME_TRANSACTIONS : String
DataImpl : -<u>FILENAME_EMPLOYEES : String
DataImpl : -<u>FILENAME_BEVERAGES : String
DataImpl : -<u>FILENAME_LA_TAZZA_ACCOUNT : String
DataImpl : +DataImpl()
DataImpl : -loadObject(filename:String) : Object
DataImpl : -loadObjects(filename:String) : Collection<?>
DataImpl : -storeObject(object:Object, filename:String, append:boolean) : void
DataImpl : -storeObjects(collection:Collection<?> , filename:String) : void

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
Beverage : +increaseAvailableCapsules(numberOfBoxes:int) : void
Beverage : +decreaseAvailableCapsules(numberOfCapsules:int) : void
Beverage : +toString() : String

LaTazzaAccount : -balance : int
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
Transaction : +Transaction()
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
Consumption : +getEmployee() : Employee
Consumption : +toString() : String

note right of DataImpl : Facade
note "Implements Serializable" as N1
Transaction . N1 
N1 . Employee
N1 .. Beverage
N1 .. LaTazzaAccount
```

A design pattern is used: *Facade*. The *DataImpl* class provides an easier usage of the package to the client, hiding the details of its composition.  

*Class visibility*: even if only all the classes except for *DataImpl* could have package visibility, for testability it is better that they are public.  

*Data persistency*: the *DataImpl* class includes the serialization/deserialization of *transactions*, *employees*, *beverages* and *laTazzaAccount*. After a creation the new object is appended to the relative file, while after an update the file is overwritten completely. At boot time (i.e. in the constructor of *DataImpl*), data are loaded if the files are present. This ensures also an easy portability from a PC to another one (NFR4).


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

## Scenario 1 - Colleague uses one capsule of type T
```plantuml
autonumber

actor Administrator as a order 1
participant ":JButton" as jb order 2
participant ":ActionListener" as al order 4
participant ":DataInterface" as di order 5
participant ":Employee" as e order 6
participant ":Beverage" as b order 7
participant ":Consumption" as c order 8
participant ":JOptionPane" as jo order 3

a -> jb: clickSell()
jb -> al: actionPerformed(clickSellEvent)
note left of di: returns a value >= 0
al -> di: sellCapsules(employeeId, beverageId, 1, true)
di -> e: decreaseBalance(1)
di -> b: decreaseAvailableCapsules(1)
di -> c: new Consumption(employee, beverage, 1, true)
jo  <- al: showMessageDialog(JPanel.this, "Ok")
```

## Scenario 2 - Colleague uses one capsule of type T, account negative
```plantuml
autonumber

actor Administrator as a order 1
participant ":JButton" as jb order 2
participant ":ActionListener" as al order 4
participant ":DataInterface" as di order 5
participant ":Employee" as e order 6
participant ":Beverage" as b order 7
participant ":Consumption" as c order 8
participant ":JOptionPane" as jo order 3

a -> jb: clickSell()
jb -> al: actionPerformed(clickSellEvent)
note left of di: returns a negative value
al -> di: sellCapsules(employeeId, beverageId, 1, true)
di -> e: decreaseBalance(1)
di -> b: decreaseAvailableCapsules(1)
di -> c: new Consumption(employee, beverage, 1, true)
jo  <- al: showMessageDialog(JPanel.this, "Warning")
```