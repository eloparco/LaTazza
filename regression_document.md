# GUI Test cases documentation template

Authors:
* Bonelli Lorenzo 267563  
* Ruggeri Franco 265682  
* Rosso Alessandro 265485  
* Loparco Enrico 261072  

Date: 16/06/19

Version: 1.0.0

# Contents

- [Regression Report for Visual Test Cases](#fragility)



# Regression Report for Visual Test Cases

```
<Report here the changes that you had to perform on the Visual test cases, indicating for each one:
- if the Visual test case was still working after the modification (non-fragile test case)
- if the Visual test case was not working after the modifications in the GUI (fragile test case)
- the number of screen captures that you had to adapt to the modification, and the percentage of changed captures (computed as the ratio: changed screen captures / total screen captures, for each test case)>
```

| Scenario | Fragile / Non-Fragile | Number of changed screen captures | Percentage of changed screen captures |
| -------- | --------------------- | --------------------------------- | ------------------------------------- |
|     1  \*   |            Fragile           |               25                    |           100%                            |
|     2     |            Fragile           |                5                   |      5 / 26 * 100 = 19.23%                                 |
|     3    |                       |                                   |                                       |
|     4     |                       |                                   |                                       |
|     5     |                       |                                   |                                       |
|     6     |           Non-Fragile            |                 0                  |                      0%                 |
|     7  \*     |          Fragile             |                  19                 |                 100%                      |

*\* These test were previously implemented on a linux machine which was not available for regression tests, they had to be remade from scratch.*
