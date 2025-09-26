# 🏨 Hotel Reservation Management System (JDBC Project)

This is a simple **Hotel Reservation System** built using **Java + JDBC**.  
It is a console-based project where users can interact with the system through menu-driven options.

---

## ✨ Features
- **Reserve Room** → Add a new reservation for a customer.  
- **Update Reservation** → Modify an existing reservation.  
- **Delete Reservation** → Cancel an existing booking.  
- **Check Reservations List** → View all current reservations stored in the database.  
- **Exit** → Close the program (runs in loop until the user chooses Exit).  

---

## 🛠️ Tech Stack
- **Java** (Core Java)  
- **JDBC (Java Database Connectivity)**  
- **MySQL Database**  

---

## 📂 Project Structure
Reservation-Management-System-JDBC-Project-/
│── src/
│ ├── Main.java
│ ├── DBConnection.java
│ ├── Reservation.java
│ └── ReservationService.java
│── lib/
│ └── mysql-connector.jar
│── README.md
## ⚡ How to Run the Project
1. Clone this repository:
   ```bash
   git clone https://github.com/OptimisticPanda2/Reservation-Management-System-JDBC-Project-.git
Open the project in your IDE (BlueJ / IntelliJ / Eclipse).

Add the MySQL JDBC Connector JAR to your project’s classpath.

Set up your MySQL database:

sql

CREATE DATABASE hotel;
USE hotel;

CREATE TABLE reservations (
    id INT PRIMARY KEY AUTO_INCREMENT,
    customer_name VARCHAR(100),
    room_number INT,
    check_in DATE,
    check_out DATE
);
Update your DBConnection.java with your database username & password.

Run Main.java → The menu will appear, and you can start using the system.
