# Parking Lot System
### W2023 - ECSE 321 Introduction to Software Engineering

## Project Overview
In a team of 6, we are developing an application that will assist in the operation of a parking lot business. Following the software engineering process, the team will adhere to the four key activities in software engineering as part of their working structure: Specification, development, validation and evolution. 

The application will improve the overall experience for customers, employees and managers. Its main features are listed as follows:
1. Customer Accounts: Customers with accounts will be able to create a profile in order to reserve monthly parking spots and request additional services offered by the parking lot business.
2. Employee Accounts: Employees will each own an account for management purposes, specifically allowing them to assign parking spots to monthly customers.
3. Manager Account: The manager's account will provide additional functionality, above that of an employee, where they can decide on opening hours, employee schedules, etc.
4. Tickets: The system will also allow the general public to pay for a parking spot, without the need of an account.

## Meet the Team
| Team Member       | GitHub                                          | Role | Major                      | Year |
| ----------------- | ----------------------------------------------- | ---- | -------------------- | ---- |
| Faiza Chowdhury   |[faizachy](https://github.com/faizachy)          | Software Developer | Computer Engineering       | U2 |
| Annie Gouchee     |[anniegouchee](https://github.com/anniegouchee)  | Software Developer | Software Engineering       | U1 |
| Lin Wei Li        |[ElkCl0ner](https://github.com/ElkCl0ner)        | Project Lead <br /> Software Developer | Software Engineering       | U2 |
| Estefania Vazquez |[estefaniavazquez](https://github.com/estefaniavazquez) | Software Developer | Software Engineering Co-op | U2 |
| Qin Xuan Xu       |[qinxuanx](https://github.com/qinxuanx)          | Software Developer | Computer Engineering       | U2 |
| Edwin You Zhou    |[edwin-zhou](https://github.com/edwin-zhou)      | Software Developer | Computer Engineering       | U3 |

## Application Installation
### Prerequisites
* PostgreSQL version 15.2
  - username: postgres
  - password: 123
* Java 17
* Node.js v10.21.0 (or higher)
* Npm 6.14.4 (or higher)

### Setup Database
1. Open command line
2. Access postgres: `psql -U postgres` and enter password `123`
3. Create database: `create database parkinglotdb;`
4. Quit: `\q`

### Build and Start Application Bakcend on Local Machine
1. Build grade: `./gradlew build`
2. Run application:
  - via Gradle: `./gradlew bootRun`
  - via an IDE: run the application from `/ParkingLot-Backend/src/main/java/ca/mcgill/ecse321/parkinglotbackend/ParkingLotBackendApplication.java`
 3. Application should be started on `localhost:8080`

### Build and Start Application Frontend on Local Machine
0. Backend should be started first
1. Open a command prompt and run `cd ParkingLot-Frontend`
2. Start the node.js server with `npm SOMETHING`
3. The frontend server should be started on `localhost:5173`

## Sprint Summary

### Contributions
| Team Member       | Sprint 1  | Sprint 2  | Sprint 3 |
| ----------------- | ----------- | ------ | -- |
| Faiza Chowdhury   |   26     | 27 | |
| Annie Gouchee     |   26   | 28 | |
| Lin Wei Li        |    32   | 32 | |
| Estefania Vazquez | 28     | 29 | |
| Qin Xuan Xu       |  27   | 22 | |
| Edwin You Zhou    | 30   | 24 | |

### Sprint 1
[Sprint 1: Overview](https://github.com/McGill-ECSE321-W23/project-group-04/wiki/Overview)

### Sprint 2
[Sprint 2: Overview](https://github.com/McGill-ECSE321-W23/project-group-04/wiki/Sprint-2#overview)

### Sprint 3
[Sprint 3: Overview](https://github.com/McGill-ECSE321-W23/project-group-04/wiki/Sprint-3#overview)

