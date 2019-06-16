# Post Mortem Report

Authors:
* Bonelli Lorenzo 267563  
* Ruggeri Franco 265682  
* Rosso Alessandro 265485  
* Loparco Enrico 261072  

Date: 16/06/19

Version: 1.0.0

# Requirements

Our requirement document was different from the official one, but that is normal, considering that the informal description was general, leaving space to multiple interpretations.
In addition to what is written in the official requirement, we decided to use LaTazza to automate the order of capsules (via emails) and to turn a little profit for the manager in charge of using the application (adding a profit percentage to the capsule purchase price).

# Design

We focused on getting a simple design with a simple persistency method (serialization). Our efforts were rewarded, leading to a system with small classes, easy to fix and maintain in the subsequent deadlines.
Our choice paid off, considering that other teams were struggling using external libraries and more complex persistency solutions.

# Code and test cases

We dedicated a lot of time in testing our code. Considering the simplicity of some classes, we decided to follow a bottom-up approach with incremental integration, avoiding to develop stubs. Most of testing was for the facade class, proportionally with its complexity.
Even if the application worked properly at the deadline, some official test cases failed, due to some missing checks (e.g. null pointers). Probably we should have opened some further issues to clarify missing/ambiguous specifications of certain functions.
However, thanks to the modularity of the code, the failed test cases were fixed very quickly.

# Changes

The large amount of tests turned out to be very useful during changes (regression testing).

# Build, tools and environment

Git was indispensable, especially during coding and testing, to work remotely.
Maven permitted us to keep aligned the versions of plugins (e.g. JUnit), external libraries, and so on.

# Other observations
