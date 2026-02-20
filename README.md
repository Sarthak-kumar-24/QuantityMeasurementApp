# Quantity Measurement App  
## UC2 – Feet & Inches Measurement Equality

---

##  Overview

UC2 extends the Quantity Measurement App by adding support for **Inches measurement equality**, while preserving all functionality introduced in **UC1 (Feet equality)**.

This use case focuses on:
- supporting multiple units independently
- avoiding code duplication
- maintaining strict type safety
- preparing the codebase for future unit conversions

At this stage, the application supports equality checks **within the same unit only**.

---

##  What UC2 Adds (On Top of UC1)

### From UC1 (Already Present)
- Feet ↔ Feet equality
- Value-based comparison
- EqualityService for comparison logic
- Clean separation of concerns

### Added in UC2
- Inches ↔ Inches equality
- Common abstraction for measurements
- DRY-compliant equality implementation
- Reusable, extensible design

---

##  Supported Functionality

- ✔ Feet ↔ Feet equality  
- ✔ Inches ↔ Inches equality  
- ✔ Safe handling of:
  - same reference
  - null comparison
  - different object comparison  

---

##  Not Supported (By Design)

- Feet ↔ Inches comparison  
- Unit conversion  
- Arithmetic operations  

These features are intentionally deferred to later use cases.

---

##  Design Improvements Introduced in UC2

### 1️ Abstraction

A common abstract base class (`Measurement`) is introduced to represent shared behavior across all measurement types.

This abstraction:
- encapsulates the numeric value
- centralizes equality logic
- enforces immutability

---

### 2️ DRY (Don’t Repeat Yourself)

In UC1, equality logic existed only for Feet.  
In UC2, instead of duplicating the same logic for Inches:

- Equality logic is implemented **once** in the abstract class
- Concrete unit classes inherit this behavior

This eliminates redundancy and simplifies future maintenance.

---

### 3️ Type Safety

Equality checks use strict class comparison:
- Feet objects are equal only to Feet
- Inches objects are equal only to Inches

This prevents accidental cross-unit equality.

---

##  Equality Flow

1. User inputs numeric values
2. Measurement objects are created (`Feet` or `Inches`)
3. EqualityService delegates comparison
4. Equality logic performs:
   - reference check
   - null check
   - type check
   - value comparison using `Double.compare()`

---

##  Testing Strategy

Unit tests cover:
- same-value equality
- different-value inequality
- null comparison
- same-reference equality
- type safety (different class comparison)

Feet and Inches are tested independently.

---

##  Forward Compatibility

The abstraction introduced in UC2 prepares the system for:

- UC3: Generic length comparison
- UC4+: Unit conversion (Feet ↔ Inches ↔ Yard)
- Quantity arithmetic

No breaking changes to UC1 or UC2 logic are expected in future use cases.

---

##  Summary

UC2 builds upon UC1 by:
- adding Inches equality
- removing duplication
- introducing abstraction
- enforcing strict type safety

This use case represents a **structural improvement**, not just a functional addition, and lays a strong foundation for future enhancements.

---
