# Quantity Measurement App  
## UC3 – Generic Quantity Class (Applying DRY Principle)

---

##  Overview

UC3 refactors the Quantity Measurement App to remove code duplication introduced in earlier use cases (UC1 and UC2).

Previously, separate classes such as Feet and Inches were used to represent different units of length. While functional, this design violated the DRY (Don’t Repeat Yourself) principle because these classes contained nearly identical logic for construction, validation, and equality comparison.

UC3 introduces a single generic quantity class backed by a unit enum, preserving all existing functionality while significantly improving maintainability and scalability.

---

## UC3 Objectives

- Eliminate duplication caused by unit-specific classes
- Introduce a unified representation for length measurements
- Enable cross-unit equality (e.g., 1 foot == 12 inches)
- Preserve all behavior from UC1 and UC2
- Prepare the codebase for easy future extensions

---

##  Supported Functionality

- ✔ Feet ↔ Feet equality  
- ✔ Inches ↔ Inches equality  
- ✔ Safe handling of:
  - same reference
  - null comparison
  - different object comparison  

---

## Changes Introduced in UC3
❌ Removed

- Feet class
- Inches class

✅ Added

QuantityLength – generic class representing any length quantity
LengthUnit – enum defining supported units and conversion factors

---

##  Design Improvements Introduced in UC3

### 1️ Generic Quantity Model

QuantityLength encapsulates:

- numeric value
- unit type
- conversion logic
- equality comparison

Each object represents value + unit together, ensuring correctness and type safety.

---

### 2️ Enum-Based Unit Handling

The LengthUnit enum:

- defines supported units (FEET, INCH)
- stores conversion factors
- converts values to a common base unit

Enums eliminate magic strings and prevent invalid units at compile time.

---

### 3️ Common Base Unit Conversion

All comparisons are performed by:

- converting values to a common base unit (inch)
- comparing the converted values

This enables accurate and consistent cross-unit equality.

---

##  Supported Functionality

- ✔ Feet ↔ Feet equality
- ✔ Inches ↔ Inches equality
- ✔ Feet ↔ Inches equality
- ✔ Same-reference equality
- ✔ Null-safe comparison
- ✔ Type-safe equality checks

---

##  Application Flow

- User inputs two numeric values and their respective units
- Unit input is converted into a LengthUnit enum
- QuantityLength objects are created
- Values are converted internally to the base unit
- Equality is evaluated using value-based comparison
- Result is displayed to the user

---

##  Testing Strategy

Test cases from UC1 and UC2 are conceptually preserved and adapted to the generic model.

Tests validate:

- same-unit equality
- cross-unit equality
- inequality for different values
- null handling
- same-reference equality
- different-class comparison

This confirms that refactoring did not break existing behavior.

---

##  Forward Compatibility

UC3 prepares the codebase for:

- adding new units with minimal changes
- implementing quantity arithmetic
- extending comparison logic
- scaling the system cleanly

Adding a new unit requires only a new enum constant, with no changes to equality logic.

---
