# UC15: Layered Architecture with Caching and Exception Handling  
### Controller, Service, Repository and DTO Integration

---

## Description

UC15 enhances the **Quantity Measurement Application** by introducing a **layered architecture** that separates responsibilities across different parts of the system.

The application is refactored into the following layers:

- Controller Layer
- Service Layer
- Repository Layer
- Domain Layer
- DTO Layer

UC15 also introduces **in-memory caching** for storing operation history and **custom exception handling** for better error management.

The main objective is to improve **code organization, maintainability, and scalability**.

---

## Limitations in UC14

Before UC15:

1. The console application directly interacted with business logic.
2. There was **no architectural separation** between layers.
3. Operation results were **not stored anywhere**.
4. Error handling was not centralized.

---

## Goals of UC15

- Introduce **layered architecture**
- Separate **Controller, Service, and Repository**
- Implement **in-memory caching for operations**
- Use **DTO objects for data transfer between layers**
- Implement **custom exception handling**
- Improve **testability and maintainability**

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
Repository Layer (Cache Storage)

Each layer performs a **specific responsibility**, ensuring a clean and modular design.

---

## Repository Cache

UC15 introduces a **repository layer** that stores operation history in memory.

Cache structure:

List<QuantityMeasurementEntity>

Each stored entry contains:

- operand1  
- operand2  
- operation  
- result  
- error flag  

The cache acts as a **temporary runtime log** and is cleared when the application stops.

---

## DTO Usage

UC15 introduces **QuantityDTO** to transfer data between layers safely.

DTO contains:

- value  
- unit  

Example:

QuantityDTO<U extends IMeasurable>

DTOs prevent direct exposure of internal domain classes.

---

## Exception Handling

UC15 introduces a custom exception:

QuantityMeasurementException

The service layer catches internal exceptions (such as validation or arithmetic errors) and converts them into this domain exception for **consistent error handling**.

---

## Example Flow

Example operation:

1 ft + 12 in

Execution flow:

MainApp  
→ Controller  
→ Service  
→ Quantity Engine  
→ Repository (cache save)  
→ Result returned

Output:

Quantity(2.0, FEET)

---

## Conclusion

UC15 restructures the application into a **clean layered architecture** with caching and exception handling.

This improves **modularity, maintainability, and scalability**, preparing the system for future enhancements such as persistent storage or REST APIs.
