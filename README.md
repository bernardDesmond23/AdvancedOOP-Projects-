# Project Overview 
## 1. Database prac (Database Management System)
A distributed application that allows a client to interact with a database via a GUI. It uses Java RMI to separate the user interface from the database logic.

GUI : Built with Java Swing (SimpleGUI, SimpleApp). Includes a Student model class.

RMI Layer: Implements a remote interface (DatabaseInterface), the server implementation (DatabaseIntImpl), the server launcher (DatabaseRMIServer), and the client (RMIClient).

Data Layer: Handles JDBC connections and CRUD operations (DBConnection, DBOperations).

Navigate [Project Files](https://github.com/bernardDesmond23/AdvancedOOP-Projects-/tree/main/Database%20prac/src) to see the project folders

## 2. DistributedSystems (RMI & GUI)
A collection of distributed system exercises focusing on Remote Method Invocation.

PETS: A client-side GUI (PetsClientGUI) that interacts with a remote service (for managing pet records or images).

RMI Core: Contains the base DatabaseInterface used for remote communication.

Navigate [Project Files](https://github.com/bernardDesmond23/AdvancedOOP-Projects-/tree/main/DistributedSystems/src) to see the project folders
## Technologies & Concepts Demonstrated
Language: Java (JDK 8+)

Distributed Computing: Java RMI (Remote Method Invocation), Stubs & Skeletons, Client-Server architecture.

Database: JDBC, SQL (Database connectivity and operations).

GUI: Java FX (Event handling, layouts, desktop UI design).

OOP Principles: Interfaces, Inheritance, Polymorphism, Encapsulation, Exception Handling.
