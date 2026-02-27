# Quantity Measurement App  
## UC4 – Extended Unit Support (Scalable Generic Design)

---

## 📌 Overview

UC4 extends the Quantity Measurement App by building on the **generic, DRY-based design introduced in UC3**.

While UC3 focused on eliminating duplication by replacing unit-specific classes (Feet, Inches) with a single generic quantity model, UC4 validates that design by **adding new units without modifying existing logic**.

In this use case, **Yards** and **Centimeters** are introduced as additional length units.  
All changes are confined to the `LengthUnit` enum, proving that the system is scalable, maintainable, and correctly abstracted.

---

##  UC4 Objectives

- Extend length unit support to **Yards** and **Centimeters**
- Preserve all functionality from UC1, UC2, and UC3
- Support accurate **cross-unit equality** across multiple units
- Avoid code duplication while adding new units
- Demonstrate scalability of the generic quantity design

---

##  Supported Units

| Unit | Symbol | Conversion to Base Unit (Inches) |
|----|----|----|
| Inches | in | 1.0 |
| Feet | ft | 12.0 |
| Yards | yd | 36.0 |
| Centimeters | cm | 0.393701 |

> **Base Unit:** Inches  
All unit values are internally converted to inches before comparison.

---

##  Design Approach

UC4 follows a **composition-based design**, where each measurement is represented by:

- a numeric value
- a unit type (enum)
- centralized conversion logic

No inheritance hierarchy is used for units.  
Instead, unit behavior is driven by the `LengthUnit` enum.

---

##  Core Components

### 1️ QuantityLength (Generic Quantity Class)

`QuantityLength` represents a length measurement using:

- numeric value
- unit (LengthUnit enum)
- base-unit conversion
- value-based equality

Key characteristics:

- Immutable
- Unit-agnostic
- Null-safe
- Cross-unit comparable

Equality is determined by converting both values to the base unit and comparing them.

---

### 2️ LengthUnit (Enum-Based Unit Model)

The `LengthUnit` enum:

- defines all supported length units
- stores conversion factors relative to inches
- centralizes unit validation and parsing
- enables easy extension by adding new constants

Adding a new unit requires **only one enum entry**.

---

##  Equality Logic

Two quantities are considered equal if:

- they are of the same type (`QuantityLength`)
- their values, when converted to the base unit, are numerically equal

This supports:

- same-unit equality  
- cross-unit equality  
- symmetric and transitive comparisons  

Examples:

- `1 yard == 3 feet`
- `1 yard == 36 inches`
- `1 cm == 0.393701 inches`

---

##  Supported Comparisons

✔ Inches ↔ Inches  
✔ Feet ↔ Feet  
✔ Yards ↔ Yards  
✔ Centimeters ↔ Centimeters  

✔ Feet ↔ Inches  
✔ Yards ↔ Feet  
✔ Yards ↔ Inches  
✔ Centimeters ↔ Inches  
✔ Centimeters ↔ Feet  
✔ Centimeters ↔ Yards  

✔ Same-reference equality  
✔ Null-safe comparison  
✔ Type-safe equality checks  

---

##  Application Flow

1. User inputs two numeric values and their units  
2. Unit strings are parsed into `LengthUnit` enum values  
3. `QuantityLength` objects are created  
4. Values are converted internally to inches  
5. Equality is evaluated using value-based comparison  
6. Result is displayed to the user  

---

##  Testing Strategy

UC4 preserves and extends all tests from UC1–UC3.

Tests validate:

- same-unit equality
- cross-unit equality
- multi-step unit conversions
- inequality for different values
- null handling
- same-reference equality
- backward compatibility

All previous test cases continue to pass without modification.

---

##  Design Benefits Demonstrated in UC4

- **Scalability:** new units added without changing core logic
- **DRY Principle:** no duplicate conversion or comparison code
- **Enum Extensibility:** units managed centrally and safely
- **Backward Compatibility:** existing behavior remains unaffected
- **Maintainability:** clean, predictable design
