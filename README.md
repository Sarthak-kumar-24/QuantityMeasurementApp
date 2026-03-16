# UC16: Database Integration with JDBC for Quantity Measurement Persistence  
### Controller, Service, Repository and Database Integration

---

## Description

UC16 enhances the **Quantity Measurement Application** by introducing **persistent database storage using JDBC (Java Database Connectivity)**.

Building upon the **layered architecture introduced in UC15**, this use case replaces the in-memory cache repository with a **database-backed repository implementation** called `QuantityMeasurementDatabaseRepository`.

The application can now **store and retrieve quantity measurement operation history from a relational database**, enabling long-term persistence, audit tracking, and historical analysis.

UC16 also introduces **professional project structure with Maven**, proper package organization, and secure SQL execution using **parameterized queries** to prevent SQL injection.

The architecture still supports switching between **in-memory cache storage and database storage** using repository abstraction and dependency injection.

---

## Limitations in UC15

Before UC16:

1. Operation history was stored only in **in-memory cache**.
2. Data was **lost when the application stopped or crashed**.
3. The system could not support **multiple application instances accessing shared data**.
4. There was **no schema validation or relational constraints**.
5. Historical data could not be **queried or analyzed using SQL**.
6. No structured **data persistence mechanism** existed.

---

## Goals of UC16

- Introduce **database persistence using JDBC**
- Replace **in-memory repository with database repository**
- Store operation history in a **relational database**
- Implement **parameterized SQL queries for security**
- Support **schema-based validation and relational integrity**
- Enable **querying, reporting, and historical analysis**
- Improve **scalability and concurrent data access**

---

## Layered Architecture

System Flow:

Application Layer  
      ↓  
Controller Layer  
      ↓  
Service Layer  
      ↓  
Domain Logic (Quantity Engine)  
      ↓  
Repository Layer (JDBC Database)

The **Repository Layer now communicates with a relational database using JDBC**, while the rest of the architecture remains unchanged.

Each layer maintains a **single responsibility**, ensuring modular and maintainable code.

---

## Database Repository

UC16 introduces a **JDBC-based repository implementation**:

QuantityMeasurementDatabaseRepository

Responsibilities include:

- Storing operation results in the database  
- Retrieving stored operation history  
- Executing parameterized SQL queries  
- Managing database connections  
- Ensuring secure and efficient data access

Each stored database record includes:

- operand1 value  
- operand1 unit  
- operand2 value  
- operand2 unit  
- operation type  
- result value  
- result unit  
- error flag  
- timestamp  

This enables **complete tracking of all measurement operations performed by the application**.

---

## Database Schema

UC16 introduces a relational database table to store measurement operations.

Example table structure:

QuantityMeasurementHistory

Columns:

- id (Primary Key)  
- operand1_value  
- operand1_unit  
- operand2_value  
- operand2_unit  
- operation  
- result_value  
- result_unit  
- error_flag  
- created_at  

The schema ensures **data integrity, validation, and structured storage**.

---

## JDBC Features Used

UC16 implements several JDBC best practices:

- **PreparedStatement** for parameterized SQL queries  
- **Connection management using JDBC drivers**  
- **Transaction handling for consistent writes**  
- **Database schema initialization scripts**  
- **Secure SQL execution to prevent SQL injection**

These practices make the application **secure, reliable, and production-ready**.

---

## Example Flow

Example operation:

1 ft + 12 in

Execution flow:

MainApp  
→ Controller  
→ Service  
→ Quantity Engine  
→ Repository (JDBC database insert)  
→ Result returned

Database Entry Stored:

operand1 = 1 FEET  
operand2 = 12 INCH  
operation = ADD  
result = 2 FEET  

Output:

Quantity(2.0, FEET)

---

## Conclusion

UC16 upgrades the **Quantity Measurement Application** by integrating **JDBC-based database persistence** into the layered architecture introduced in UC15.

This enhancement enables **reliable long-term storage of measurement operations**, improves **scalability**, and supports **data analysis and reporting**.

The system is now better prepared for **enterprise-level features**, including advanced analytics, distributed systems, and integration with external services.
