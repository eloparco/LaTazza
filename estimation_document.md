# Project Estimation  template

Authors:
* Bonelli Lorenzo 267563  
* Ruggeri Franco 265682  
* Rosso Alessandro 265485  
* Loparco Enrico 261072  

Date: 02/06/19

Version: 1.0.0


# Contents

- [Data from your LaTazza project](#data-from-your-latazza-project)
- [Estimate by product decomposition](#estimate-by-product-decomposition)
- [Estimate by activity decomposition ](#estimate-by-activity-decomposition)


# Data from your LaTazza project

###

| | |
| ----------- | ------------------------------- | 
|         Total person hours  worked by your  team, considering period March 5 to May 26, considering ALL activities (req, des, code, test,..)    | 120 ph  |             
|Total Java LoC delivered on May 26 (only code, without Exceptions, no Junit code) | 650 LOC |
| Total number of Java classes delivered on May 26 (only code, no Junit code, no Exception classes)| 8 classes |
| Productivity P = Total LOC / Total person hours | 650/120 = 5.4 LOC/ph |
|Average size of Java class A = Total LOC / number of classes | 650/8 = 81.25 LOC/class |


# Estimate by product decomposition

### 

|             | Estimate                        |             
| ----------- | ------------------------------- |  
| Estimated n classes NC (no Exception classes)  |                6 (data) + 9 (GUI) = 15 classes           |             
| Estimated LOC per class  (Here use Average A computed above )      |            81.25 LOC/class                | 
| Estimated LOC (= NC * A) | 1250 LOC |
| Estimated effort  (person days) (Here use productivity P)  | 1250/(5.4\*8)= 29 person days                                    |      
| Estimated calendar time (calendar weeks) (Assume team of 4 people, 8 hours per day, 5 days per week ) |       29*8/(4\*8\*5) = 1.45 weeks           |               


# Estimate by activity decomposition

### 

|         Activity name    | Estimated effort    |             
| ----------- | ------------------------------- | 
| Requirements | 30 ph |
| Design | 45 ph |
| Coding | 40 ph |
| Unit testing | 35 ph |
| Integration testing | 45 ph |
| Acceptance testing | 35 ph |

### Gantt chart

![Gantt chart](figures/gantt_chart.png)

