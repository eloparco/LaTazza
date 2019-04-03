# Requirements Document

Authors: 

Date:

Version:

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

LaTazza is a simple desktop application adopted by a group of office workers to satisfy the necessity of coffee capsules in the workplace.

One employee takes the role of manager, he acts as a seller handling the supply and the resale of capsules while turning a small profit.

# Stakeholders

| Stakeholder name  	| Description 							    | 
|-----------------------|-------------------------------------------|
| Manager    			| Manage the purchase and sale of capsules	| 
| Employee  			| Buy capsules from manager 			    |
| Visitor        		| Buy capsules from manager      			|
| Coffee Capsule Vendor | Sell capsules to manager 				    |

# Context Diagram and interfaces

## Context Diagram

```plantuml
left to right direction
skinparam packageStyle rectangle

actor Manager as m
actor "Coffe Capsule Vendor" as v

rectangle system {
    (LaTazza) as lt
    m -- lt
    lt -- v
}
```

## Interfaces

| Actor                         | Logical Interface	| Physical Interface    |
|-------------------------------|:-----------------:|:---------------------:|
| Manager      			        | GUI			    | Screen, keyboard		|
| Coffee Capsule Vendor         | Web service, APIs	| Internet connection	|

The banking/credit card system is not listed in the actors because, even if the application makes the orders automatically once the manager choose to buy new boxes of capsules from the GUI, the payment to the vendor is handled separately (by the manager, not the application). 

# Stories and personas

## Persona 1
![LaTazza](persona1.png "")
*LaTazza client*
## Persona 2
![LaTazza](persona2.png "")
*LaTazza manager*

# Functional and non functional requirements

## Functional Requirements

## Non Functional Requirements

# Use case diagram and use cases

## Use case diagram

## Use Cases

# Relevant scenarios

# Glossary

# System Design
