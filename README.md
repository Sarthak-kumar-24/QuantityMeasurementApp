# Quantity Measurement App  
## UC1 – Feet Measurement Equality

---

##  Overview

UC1 is the **foundational use case** of the Quantity Measurement App.

The goal of this use case is to introduce **value-based equality comparison** for measurements expressed in **Feet**, using clean object-oriented design principles.

This use case establishes the base structure upon which all future units and comparisons will be built.

---

##  UC1 Objective

- Compare two numerical values measured in **Feet**
- Determine equality based on numeric value
- Handle common edge cases safely
- Keep the design simple, clean, and extensible

---

##  Supported Functionality

- ✔ Feet ↔ Feet equality comparison  
- ✔ Value-based equality (not reference-based)  
- ✔ Safe handling of:
  - same object reference
  - `null` comparison
  - different object comparison  

---

##  Out of Scope (By Design)

The following are intentionally **not part of UC1**:

- Other units (Inches, Yards, etc.)
- Unit conversion
- Arithmetic operations
- Cross-unit comparison

These capabilities are introduced in later use cases.

---

##  Design Overview

### 1️ Feet as a Value Object

The `Feet` class represents a measurement in feet and is designed as:
- **Immutable**
- **Value-based**
- **Type-safe**

Equality is determined purely by the numeric value stored inside the object.

---

### 2️ Equality Service

Equality logic is delegated to a dedicated service class.

This ensures:
- separation of concerns
- reusable comparison logic
- a clean `main` method

---

### 3️ Application Flow

1. User provides two numeric values in feet  
2. `Feet` objects are created  
3. Equality is checked via the `EqualityService`  
4. A user-friendly result is displayed  

---

##  Equality Logic

The equality comparison follows these steps:

1. Check if both references point to the same object  
2. Check for `null`  
3. Ensure both objects are of the same class  
4. Compare numeric values using `Double.compare()`  

This approach avoids floating-point precision issues and ensures correctness.

---

##  Testing Strategy

Unit tests validate:
- equality for same numeric values  
- inequality for different values  
- comparison with `null`  
- same-reference equality  
- type safety  

Each test focuses on **one behavior only**, following good testing practices.

---

##  Forward Compatibility

UC1 lays the groundwork for future enhancements, including:

- UC2: Additional units (Inches)
- UC3: Generic measurement abstraction
- UC4+: Unit conversion and arithmetic

No changes to UC1 logic are required to support future use cases.

---

##  Summary

UC1 provides a clean and minimal implementation of **Feet measurement equality**, establishing:

- correct value-based comparison
- clean object-oriented design
- a stable foundation for future growth

This use case serves as the **starting point** for the Quantity Measurement App.

---
