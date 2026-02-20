# Quantity Measurement App  
## UC4 – Extended Unit Support (Applying Scalability & DRY Principle)

---

## Overview

UC4 extends the generic quantity design introduced in UC3 by adding support for **additional length units** without changing existing business logic.

After refactoring the system into a generic quantity model in UC3, UC4 demonstrates the **scalability of the design** by introducing new units such as **Yards** and **Centimeters**.  
This use case validates that the system is **open for extension and closed for modification**, in line with SOLID principles.

All previously supported behavior from UC1, UC2, and UC3 remains intact.

---

## UC4 Objectives

- Extend support for additional length units
- Avoid creating new unit-specific classes
- Modify only configuration (enum), not core logic
- Preserve all existing functionality
- Demonstrate scalability of the generic design

---

## Changes Introduced in UC4

### ❌ Removed

- No classes were removed in UC4  

### ✅ Updated

- `LengthUnit` enum extended to support:
  - Yards
  - Centimeters

No changes were required in:
- `QuantityLength`
- `EqualityService`
- Application logic

---

## Design Improvements Introduced in UC4

### 1. Scalable Enum-Based Unit Extension

The `LengthUnit` enum is extended to include additional units:

- FEET
- INCHES
- YARDS
- CENTIMETERS

Each unit defines its conversion factor relative to a **common base unit (inches)**.

This ensures new units can be added **without modifying existing logic**.

---

### 2. Preservation of Generic Quantity Model

The `QuantityLength` class remains unchanged.

It continues to encapsulate:

- numeric value
- unit type
- conversion logic
- equality comparison

This confirms that UC3’s abstraction was correctly designed.

---

### 3. Common Base Unit Conversion

All comparisons are performed by:

- converting values to a common base unit (inches)
- comparing the converted values

This guarantees accurate equality comparison across **multiple units**.

---

## Supported Functionality

✔ Feet ↔ Feet equality  
✔ Inches ↔ Inches equality  
✔ Yards ↔ Yards equality  
✔ Centimeters ↔ Centimeters equality  
✔ Feet ↔ Inches equality  
✔ Yards ↔ Feet equality  
✔ Yards ↔ Inches equality  
✔ Centimeters ↔ Inches equality  
✔ Same-reference equality  
✔ Null-safe comparison  
✔ Type-safe equality checks  

---

## Application Flow

1. User inputs two numeric values and their respective units  
2. Unit input is mapped to a `LengthUnit` enum  
3. `QuantityLength` objects are created  
4. Values are converted internally to the base unit  
5. Equality is evaluated using value-based comparison  
6. Result is displayed to the user  

---

## Testing Strategy

Existing test cases from UC1, UC2, and UC3 are preserved and extended.

Additional tests validate:

- same-unit equality for new units
- cross-unit equality between all supported units
- transitive property across multiple units
- null handling
- same-reference equality
- different-class comparison

This confirms that extending unit support did **not introduce regressions**.

---

## Forward Compatibility

UC4 further prepares the codebase for:

- adding more length units (meters, kilometers, miles)
- implementing arithmetic operations
- extending the system to other dimensions (weight, volume)

Adding a new unit requires **only a new enum constant**, with **no changes to quantity or equality logic**.

---
